package org.geepawhill.contentment.actors;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.connector.arrow.*;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Arrow extends GenericActor
{
	final String nickname;
	
	private GroupSource from;
	private GroupSource to;

	private Curve mainStep;
	private Curve fromTopStep;
	private Curve fromBottomStep;
	private Curve toTopStep;
	private Curve toBottomStep;

	private ArrowPoints points;

	private ArrayList<Curve> steps;

	private Bezier chosenMain;
	private Bezier chosenFromTop;
	private Bezier chosenFromBottom;
	private Bezier chosenToTop;
	private Bezier chosenToBottom;

	private boolean pointAtFrom;

	private boolean pointAtTo;

	private Format format;
	

	public Arrow(ScriptWorld world, Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo, Format format)
	{
		this(world,from.groupSource(),pointAtFrom,to.groupSource(),pointAtTo,format);
	}
	
	public Arrow(ScriptWorld world)
	{
		this(world,GroupSource.NONE,false,GroupSource.NONE,false,Format.DEFAULT);
	}
	
	public Arrow(ScriptWorld world, GroupSource from, boolean pointAtFrom, GroupSource to, boolean pointAtTo, Format format)
	{
		super(world);
		this.from = from;
		this.pointAtFrom = pointAtFrom;
		this.to = to;
		this.pointAtTo = pointAtTo;
		this.format = format;
		this.nickname = Names.make(getClass());
	}
	
	public Arrow from(String actorName,boolean withHead)
	{
		return from(world.actor(actorName).groupSource(),withHead);
	}
	
	public Arrow from(GroupSource from,boolean withHead)
	{
		this.from = from;
		this.pointAtFrom=withHead;
		return this;
	}
	
	public Arrow to(String actorName, boolean withHead)
	{
		return to(world.actor(actorName).groupSource(),withHead);
	}

	
	public Arrow to(GroupSource to, boolean withHead)
	{
		this.to = to;
		this.pointAtTo = withHead;
		return this;
	}
	
	public Arrow format(Format format)
	{
		this.format = format;
		return this;
	}

	public String nickname()
	{
		return nickname;
	}

	private Bezier getMainBezier()
	{
		if (chosenMain == null)
		{
			ArrowComputer computer = new NodeArrowComputer(from, to);
			points = computer.compute();
			chosenMain = chooseBezier(points.main);
			chosenFromTop = chooseBezier(points.fromTop);
			chosenToTop = chooseBezier(points.toTop);
			chosenFromBottom = chooseBezier(points.fromBottom);
			chosenToBottom = chooseBezier(points.toBottom);
		}
		return chosenMain;
	}

	private Bezier getFromTop()
	{
		return chosenFromTop;
	}

	private Bezier getFromBottom()
	{
		return chosenFromBottom;
	}

	private Bezier getToTop()
	{
		return chosenToTop;
	}

	private Bezier getToBottom()
	{
		return chosenToBottom;
	}

	public Bezier chooseBezier(PointPair points)
	{
		double variance = points.distance()*.1;
		Bezier chosen = new Bezier(
				points.from,
				world.jiggle(points.along(world.nextDouble()), 1d, variance),
				world.jiggle(points.along(world.nextDouble()), 1d, variance),
				points.to);
		return chosen;
	}

	@Override
	public Arrow draw(double ms)
	{
		steps = new ArrayList<>();
		chosenMain = null;
		mainStep = new Curve(groupSource(), this::getMainBezier, format);
		if (pointAtFrom)
		{
			fromTopStep = new Curve(groupSource(), this::getFromTop, format);
			steps.add(fromTopStep);
			fromBottomStep = new Curve(groupSource(), this::getFromBottom, format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new Curve(groupSource(), this::getToTop, format);
			steps.add(toTopStep);
			toBottomStep = new Curve(groupSource(), this::getToBottom, format);
			steps.add(toBottomStep);
		}
		Timed sequence = new Timed(ms);
		sequence.add(Timing.weighted(.9d), mainStep);
		for (Curve step : steps)
		{
			sequence.add(Timing.weighted(.1d), step);
		}
		world.add(sequence);
		return this;
	}

	public Arrow assume()
	{
		format(world.assumptions().format());
		return this;
	}

}

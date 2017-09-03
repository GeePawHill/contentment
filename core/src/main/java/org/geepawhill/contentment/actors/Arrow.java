package org.geepawhill.contentment.actors;

import java.util.ArrayList;
import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.connector.arrow.ArrowComputer;
import org.geepawhill.contentment.connector.arrow.ArrowPoints;
import org.geepawhill.contentment.connector.arrow.NodeArrowComputer;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Arrow extends GenericActor
{
	final String nickname;
	
	private GroupSource from;
	private GroupSource to;

	private BezierAtom mainStep;
	private BezierAtom fromTopStep;
	private BezierAtom fromBottomStep;
	private BezierAtom toTopStep;
	private BezierAtom toBottomStep;

	private ArrowPoints points;

	private ArrayList<BezierAtom> steps;

	private Random random;

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
		this.random = new Random();
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
		Bezier chosen = new Bezier(points.from, points.along(random.nextDouble()).jiggle(random, 1d, 10),
				points.along(random.nextDouble()).jiggle(random, 1d, 10), points.to);
		return chosen;
	}

	@Override
	public Arrow draw(double ms)
	{
		steps = new ArrayList<>();
		chosenMain = null;
		mainStep = new BezierAtom(groupSource(), this::getMainBezier, format);
		if (pointAtFrom)
		{
			fromTopStep = new BezierAtom(groupSource(), this::getFromTop, format);
			steps.add(fromTopStep);
			fromBottomStep = new BezierAtom(groupSource(), this::getFromBottom, format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new BezierAtom(groupSource(), this::getToTop, format);
			steps.add(toTopStep);
			toBottomStep = new BezierAtom(groupSource(), this::getToBottom, format);
			steps.add(toBottomStep);
		}
		Timed sequence = new Timed(ms);
		sequence.add(Timing.weighted(.9d), mainStep);
		for (BezierAtom step : steps)
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

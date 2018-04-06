package org.geepawhill.contentment.actors;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Mark;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Connector extends GenericActor
{
	final String nickname;
	
	private GroupSource from;
	private GroupSource to;

	private Mark mainStep;
	private Mark fromTopStep;
	private Mark fromBottomStep;
	private Mark toTopStep;
	private Mark toBottomStep;

	private ArrowPoints points;

	private ArrayList<Mark> steps;

	private Bezier chosenMain;
	private Bezier chosenFromTop;
	private Bezier chosenFromBottom;
	private Bezier chosenToTop;
	private Bezier chosenToBottom;

	private boolean pointAtFrom;

	private boolean pointAtTo;

	private Format format;
	

	public Connector(ScriptWorld world, Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo, Format format)
	{
		this(world,from.groupSource(),pointAtFrom,to.groupSource(),pointAtTo,format);
	}
	
	public Connector(ScriptWorld world)
	{
		this(world,GroupSource.NONE,false,GroupSource.NONE,false,Format.DEFAULT);
	}
	
	public Connector(ScriptWorld world, GroupSource from, boolean pointAtFrom, GroupSource to, boolean pointAtTo, Format format)
	{
		super(world);
		this.from = from;
		this.pointAtFrom = pointAtFrom;
		this.to = to;
		this.pointAtTo = pointAtTo;
		this.format = format;
		this.nickname = Names.make(getClass());
	}
	
	public Connector from(String actorName,boolean withHead)
	{
		return from(world.actor(actorName).groupSource(),withHead);
	}
	
	public Connector from(GroupSource from,boolean withHead)
	{
		this.from = from;
		this.pointAtFrom=withHead;
		return this;
	}
	
	public Connector to(String actorName, boolean withHead)
	{
		return to(world.actor(actorName).groupSource(),withHead);
	}
	
	public Connector to(GroupSource to, boolean withHead)
	{
		this.to = to;
		this.pointAtTo = withHead;
		return this;
	}
	
	public Connector format(Format format)
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
			points = compute(from,to);
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
	public Connector draw(double ms)
	{
		steps = new ArrayList<>();
		chosenMain = null;
		mainStep = new Mark(groupSource(), this::getMainBezier, format);
		if (pointAtFrom)
		{
			fromTopStep = new Mark(groupSource(), this::getFromTop, format);
			steps.add(fromTopStep);
			fromBottomStep = new Mark(groupSource(), this::getFromBottom, format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new Mark(groupSource(), this::getToTop, format);
			steps.add(toTopStep);
			toBottomStep = new Mark(groupSource(), this::getToBottom, format);
			steps.add(toBottomStep);
		}
		Timed sequence = new Timed(ms);
		sequence.add(Timing.weighted(.9d), mainStep);
		for (Mark step : steps)
		{
			sequence.add(Timing.weighted(.1d), step);
		}
		world.add(sequence);
		return this;
	}

	public Connector assume()
	{
		format(world.assumptions().format());
		return this;
	}

	public ArrowPoints compute(GroupSource fromNode,GroupSource toNode)
	{
		double d = 14;
		double h = 10;
		Point fromCenter = new PointPair(fromNode.get()).center();
		Point toCenter = new PointPair(toNode.get()).center();
		PointPair startLine = new PointPair(fromCenter,toCenter);
		PointPair fromGrown = new PointPair(fromNode.get()).grow(2d);
		PointPair toGrown = new PointPair(toNode.get()).grow(2d);
		Point from = fromGrown.quadIntersects(startLine);
		Point to = toGrown.quadIntersects(startLine);
	
		PointPair main = new PointPair(from, to);
		double xDistance = from.xDistance(to);
		double yDistance = from.yDistance(to);
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		double xm = distance - d;
		double ym = h;
		double xn = xm;
		double yn = -h;
		double sin = yDistance / distance;
		double cos = xDistance / distance;

		double x = xm * cos - ym * sin + from.x;
		ym = xm * sin + ym * cos + from.y;
		xm = x;
		PointPair toTop = new PointPair(to, new Point(xm, ym));

		x = xn * cos - yn * sin + from.x;
		yn = xn * sin + yn * cos + from.y;
		xn = x;
		PointPair toBottom = new PointPair(to, new Point(xn, yn));
		
		xDistance = to.xDistance(from);
		yDistance = to.yDistance(from);
		distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		xm = distance - d;
		ym = h;
		xn = xm;
		yn = -h;
		sin = yDistance / distance;
		cos = xDistance / distance;
	
		x = xm * cos - ym * sin + to.x;
		ym = xm * sin + ym * cos + to.y;
		xm = x;
		PointPair fromTop = new PointPair(from, new Point(xm, ym));

		x = xn * cos - yn * sin + to.x;
		yn = xn * sin + yn * cos + to.y;
		xn = x;
		PointPair fromBottom = new PointPair(from, new Point(xn, yn));

		return new ArrowPoints(main, toTop, toBottom, fromTop, fromBottom);

	}
}

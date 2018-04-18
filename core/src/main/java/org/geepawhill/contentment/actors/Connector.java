package org.geepawhill.contentment.actors;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class Connector implements Actor
{
	final String nickname;

	private GroupSource fromGroup;
	private GroupSource toGroup;

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

	private boolean arrowheadAtFrom;

	private boolean arrowHeadAtTo;

	private Format format;

	private Point fromPoint;

	private Point toPoint;

	protected final ScriptWorld world;

	protected final Entrance entrance;

	protected final Group group;
	
	private final ConnectorPoints connectorPoints;

	public Connector(ScriptWorld world, Group destination)
	{
		this.world = world;
		this.group = new Group();
		this.entrance = new Entrance(group);
		this.connectorPoints = new ConnectorPoints(world);
		this.arrowheadAtFrom = false;
		this.arrowHeadAtTo = false;
		this.format = Format.DEFAULT;
		this.mainStep = new Mark(entrance(), this::getMainBezier);
		this.fromTopStep = new Mark(entrance(), this::getFromTop);
		this.fromBottomStep = new Mark(entrance(), this::getFromBottom);
		this.toTopStep = new Mark(entrance(), this::getToTop);
		this.toBottomStep = new Mark(entrance(), this::getToBottom);
		this.nickname = Names.make(getClass());
	}

	public Connector from(Point target, boolean withHead)
	{
		connectorPoints.from(target, withHead);
		arrowheadAtFrom = withHead;
		return this;
	}
	
	public Connector from(String actorName, boolean withHead)
	{
		return from(world.actor(actorName).entrance(), withHead);
	}

	public Connector from(GroupSource from, boolean withHead)
	{
		connectorPoints.from(from, withHead);
		this.arrowheadAtFrom = withHead;
		return this;
	}

	public Connector to(Point target, boolean withHead)
	{
		connectorPoints.to(target,withHead);
		this.arrowHeadAtTo = withHead;
		return this;
	}

	public Connector to(String actorName, boolean withHead)
	{
		return to(world.actor(actorName).entrance(), withHead);
	}

	public Connector to(GroupSource to, boolean withHead)
	{
		connectorPoints.to(to,withHead);
		this.arrowHeadAtTo = withHead;
		return this;
	}

	public void format(Format format)
	{
		this.format = format;
		this.mainStep.format(format);
		this.fromTopStep.format(format);
		this.fromBottomStep.format(format);
		this.toTopStep.format(format);
		this.toBottomStep.format(format);
		
	}

	private Bezier getMainBezier()
	{
		if (chosenMain == null)
		{
			points = compute();
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
		double variance = points.distance() * .1;
		Bezier chosen = new Bezier(points.from, world.jiggle(points.along(world.nextDouble()), 1d, variance),
				world.jiggle(points.along(world.nextDouble()), 1d, variance), points.to);
		return chosen;
	}

	@Override
	public Connector draw(double ms)
	{
		steps = new ArrayList<>();
		chosenMain = null;
		if (arrowheadAtFrom)
		{
			steps.add(fromTopStep);
			steps.add(fromBottomStep);
		}
		if (arrowHeadAtTo)
		{
			steps.add(toTopStep);
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

	public ArrowPoints compute()
	{
		PointPair main = connectorPoints.computeMainLine();
		return makeArrowPoints(main);
	}
	
	public static class ConnectorPoints
	{
		
		private GroupSource fromGroup;
		private Point fromPoint;
		private GroupSource toGroup;
		private Point toPoint;
		private ScriptWorld world;
		
		public ConnectorPoints(ScriptWorld world)
		{
			this.world = world;
			this.fromGroup = GroupSource.NONE;
			this.fromPoint = new Point(0, 0);
			this.toGroup = GroupSource.NONE;
			this.toPoint = new Point(0, 0);
		}
		
		public PointPair computeMainLine()
		{
			PointPair startLine = guessStartLine();
			PointPair main = new PointPair(adjustFromIfGroup(startLine), adjustToIfGroup(startLine));
			return main;
		}

		public void from(Point target, boolean withHead)
		{
			fromGroup = GroupSource.NONE;
			fromPoint = target;
		}
		
		public void from(String actorName, boolean withHead)
		{
			from(world.actor(actorName).entrance(), withHead);
		}

		public void from(GroupSource from, boolean withHead)
		{
			this.fromGroup = from;
		}

		public void to(Point target, boolean withHead)
		{
			this.toGroup = GroupSource.NONE;
			this.toPoint = target;
		}
		
		public void to(GroupSource to, boolean withHead)
		{
			this.toGroup = to;
		}
		
		private Point adjustToIfGroup(PointPair startLine)
		{
			if (toGroup == GroupSource.NONE) return startLine.to;
			PointPair toGrown = new PointPair(toGroup.group());
			return toGrown.quadIntersects(startLine);
		}

		private Point adjustFromIfGroup(PointPair startLine)
		{
			if (fromGroup == GroupSource.NONE) return startLine.from;
			PointPair fromGrown = new PointPair(fromGroup.group());
			return fromGrown.quadIntersects(startLine);
		}

		private PointPair guessStartLine()
		{
			Point fromCenter = fromGroup == GroupSource.NONE ? fromPoint : new PointPair(fromGroup.group()).center();
			Point toCenter = toGroup == GroupSource.NONE ? toPoint : new PointPair(toGroup.group()).center();
			PointPair startLine = new PointPair(fromCenter, toCenter);
			return startLine;
		}
		
	}

	private ArrowPoints makeArrowPoints(PointPair target)
	{
		final double pointStandOffFromTarget = 4d;
		PointPair main = new PointPair(target.standoffFrom(pointStandOffFromTarget), target.standoffTo(pointStandOffFromTarget));

		final double arrowStandoffFromEnd = 14d;
		Point toOffset = main.standoffTo(arrowStandoffFromEnd);
		PointPair toTop = rotateWing(-40d, main.to, toOffset);
		PointPair toBottom = rotateWing(40d, main.to, toOffset);

		Point fromOffset = main.standoffFrom(arrowStandoffFromEnd);
		PointPair fromTop = rotateWing(-40d, main.from, fromOffset);
		PointPair fromBottom = rotateWing(40d, main.from, fromOffset);

		return new ArrowPoints(main, toTop, toBottom, fromTop, fromBottom);
	}

	private PointPair rotateWing(double angle, Point pivot, Point target)
	{
		Rotate rotate = new Rotate(angle, pivot.x, pivot.y);
		Point top = new Point(rotate.transform(target.x, target.y));
		return new PointPair(top, pivot);
	}

	@Override
	public void at(Position position)
	{
	}

	public Entrance entrance()
	{
		return entrance;
	}

	public Group group()
	{
		return group;
	}
}

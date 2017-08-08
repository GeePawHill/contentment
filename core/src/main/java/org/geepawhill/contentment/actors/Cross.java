package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Color;

public class Cross extends GenericActor
{

	private GroupSource target;
	private BezierAtom leftToRight;
	private BezierAtom rightToLeft;
	private double xsize;
	private double ysize;
	private Point offset;

	public Cross(ScriptWorld world, Actor target, double size)
	{
		this(world,target.groupSource(),size,size, new Point(0,0));
	}
	
	public Cross(ScriptWorld world, GroupSource target, double xsize, double ysize, Point offset)
	{
		super(world);
		this.target = target;
		this.xsize = xsize;
		this.ysize = ysize;
		this.offset = offset;
		Format crossFormat = new Format(Frames.frame(Color.RED, 7d, 8d));
		leftToRight = new BezierAtom(groupSource(),this::leftToRightBezier, crossFormat);
		rightToLeft = new BezierAtom(groupSource(),this::rightToLeftBezier, crossFormat);
	}

	@Override
	public Cross draw(double ms)
	{
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(.5),leftToRight);
		timed.add(Timing.weighted(.5),rightToLeft);
		world.add(timed);
		return this;
	}
	
	private Bezier leftToRightBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.get()).center().add(offset);
		return new Bezier(new Point(center.x-xadditive, center.y-yadditive),new Point(center.x+xadditive,center.y+yadditive));
	}

	
	private Bezier rightToLeftBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.get()).center().add(offset);
		return new Bezier(new Point(center.x+xadditive, center.y-yadditive), new Point(center.x-xadditive, center.y+yadditive));
	}
}

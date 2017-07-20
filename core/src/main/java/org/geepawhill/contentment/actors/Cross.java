package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericAgentBuilder;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Cross implements Actor<GenericAgentBuilder<Cross>>
{

	private Actor<?> target;
	private Group group;
	private BezierAtom leftToRight;
	private BezierAtom rightToLeft;
	private double xsize;
	private double ysize;
	private Point offset;

	public Cross(Actor<?> target, double size)
	{
		this(target,size,size,new Point(0,0));
	}
	
	public Cross(Actor<?> target, double xsize, double ysize, Point offset)
	{
		this.target = target;
		this.xsize = xsize;
		this.ysize = ysize;
		this.offset = offset;
		this.group = new Group();
		Format crossFormat = new Format(Frames.frame(Color.RED, 7d, 8d));
		leftToRight = new BezierAtom(this,this::leftToRightBezier, crossFormat);
		rightToLeft = new BezierAtom(this,this::rightToLeftBezier, crossFormat);
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		return "Cross";
	}

	@Override
	public Step draw(double ms)
	{
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(.5),leftToRight);
		timed.add(Timing.weighted(.5),rightToLeft);
		return timed;
	}
	
	private Bezier leftToRightBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(new Point(center.x-xadditive, center.y-yadditive),new Point(center.x+xadditive,center.y+yadditive));
	}

	
	private Bezier rightToLeftBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(new Point(center.x+xadditive, center.y-yadditive), new Point(center.x-xadditive, center.y+yadditive));
	}

	@Override
	public GenericAgentBuilder<Cross> builder(ScriptWorld world)
	{
		return new GenericAgentBuilder<>(world,this);
	}

}
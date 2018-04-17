package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Mark;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Color;

public class Cross extends GenericActor
{

	private GroupSource target;
	private Mark leftToRight;
	private Mark rightToLeft;
	private double xsize;
	private double ysize;
	private Point offset;
	
	public Cross(ScriptWorld world, Appearance<? extends Actor> target, double size)
	{
		this(world,target.entrance(),size,size, new Point(0,0));
	}

	public Cross(ScriptWorld world, GroupSource target, double xsize, double ysize, double xoffset, double yoffset)
	{
		this(world,target,xsize,ysize,new Point(xoffset,yoffset));
	}

	public Cross(ScriptWorld world, GroupSource target, double xsize, double ysize, Point offset)
	{
		super(world);
		this.target = target;
		this.xsize = xsize;
		this.ysize = ysize;
		this.offset = offset;
		Format crossFormat = new Format(Frames.frame(Color.RED, 7d, .7d));
		leftToRight = new Mark(entrance(),this::leftToRightBezier);
		rightToLeft = new Mark(entrance(),this::rightToLeftBezier);
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
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(new Point(center.x-xadditive, center.y-yadditive),new Point(center.x+xadditive,center.y+yadditive));
	}

	
	private Bezier rightToLeftBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(
				new Point(center.x+xadditive, center.y-yadditive), new Point(center.x-xadditive, center.y+yadditive));
	}

	@Override
	public void format(Format format)
	{
	}

	@Override
	public void at(Position position)
	{
	}
}

package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.fast.SetBounds;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Cross implements Actor
{

	private Actor target;
	private Group group;
	private BezierStep leftToRight;
	private BezierStep rightToLeft;
	private double xsize;
	private double ysize;
	private Point offset;

	public Cross(Actor target, double size)
	{
		this(target,size,size,new Point(0,0));
	}
	
	public Cross(Actor target, double xsize, double ysize, Point offset)
	{
		this.target = target;
		this.xsize = xsize;
		this.ysize = ysize;
		this.offset = offset;
		this.group = new Group();
		Format crossFormat = new Format(Frames.frame(Color.RED, 7d, 8d));
		leftToRight = new BezierStep(Timing.weighted(.5d),crossFormat);
		rightToLeft = new BezierStep(Timing.weighted(.5d),crossFormat);
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
		Timed phrase = new Timed(ms);
		phrase.add(new SetBounds(target.group(),this::onSetBounds));
		phrase.add(new AddNode(group,leftToRight));
		phrase.add(leftToRight);
		phrase.add(new AddNode(group,rightToLeft));
		phrase.add(rightToLeft);
		return phrase;
	}
	
	private void onSetBounds(PointPair bounds)
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = bounds.center().add(offset);
		leftToRight.changeBezier(new Bezier(new Point(center.x-xadditive, center.y-yadditive),new Point(center.x+xadditive,center.y+yadditive)));
		rightToLeft.changeBezier(new Bezier(new Point(center.x+xadditive, center.y-yadditive), new Point(center.x-xadditive, center.y+yadditive)));
	}

}

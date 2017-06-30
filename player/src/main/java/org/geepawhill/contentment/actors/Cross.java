package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.fast.SetBounds;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.Phrase;
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
	private double size;

	public Cross(Actor target, double size)
	{
		this.target = target;
		this.size = size;
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
		double additive = size/2;
		Point center = bounds.center();
		leftToRight.changeBezier(new Bezier(new Point(center.x-additive, center.y-additive),new Point(center.x+additive,center.y+additive)));
		rightToLeft.changeBezier(new Bezier(new Point(center.x+additive, center.y-additive), new Point(center.x-additive, center.y+additive)));
	}

}

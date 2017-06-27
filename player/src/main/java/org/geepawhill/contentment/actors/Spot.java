package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.step.FastStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Spot implements Actor
{

	final String nickname;
	final Group group;
	final Circle circle;
	
	public Spot(double x,double y)
	{
		this(Names.make(Spot.class.getSimpleName()),x,y);
	}
	
	public Spot(String name,double x, double y)
	{
		this.nickname = name;
		this.group = new Group();
		this.circle = new Circle(x,y,0d);
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	@Override
	public Group group()
	{
		return group;
	}
	
	public void animateDrawText(double fraction,Context context)
	{
		circle.setVisible(true);
	}

	@Override
	public Step draw(double ms)
	{
		return new FastStep(new AddNode(group,circle));
	}

}

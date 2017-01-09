package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

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
		this.circle = new Circle(x,y,0d);
		this.circle.setVisible(false);
		this.group = new Group(circle);
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	@Override
	public void outline(KvOutline output)
	{
		output.append(nickname,"("+circle.getCenterX()+","+circle.getCenterY()+")");
	}
	
	@Override
	public Group group()
	{
		return group;
	}
	
	public Step place()
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d,this::animateDrawText)
		};
		return new TimedSequence(1d, group, substeps);
	}
	
	public void animateDrawText(double fraction,Context context)
	{
		circle.setVisible(true);
	}

}

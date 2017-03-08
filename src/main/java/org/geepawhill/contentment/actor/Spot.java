package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.newstep.OneWayStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.ShowStep;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;
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
	
//	public void place(Sequence sequence)
//	{
//		sequence.add(new OneWayStep((context) -> System.out.println(circle.getBoundsInParent())));
//		sequence.add(new Entrance(this));
//		sequence.add(new ShowStep(group));
//	}
	
	public void place(Sequence sequence)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d,this::animateDrawText)
		};
		sequence.add(new TimedSequence(1d, group, substeps));
	}
	
	public void animateDrawText(double fraction,Context context)
	{
		circle.setVisible(true);
	}

}

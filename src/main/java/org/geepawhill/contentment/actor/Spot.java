package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Spot implements Actor
{

	final String name;
	final Group group;
	final Circle circle;
	
	private static int index=0;
	
	public Spot(String name,double x, double y)
	{
		this.name = name;
		this.circle = new Circle(x,y,0d);
		this.circle.setVisible(false);
		this.group = JfxUtility.makeGroup(index++,this,circle);
	}
	
	public Spot(double x,double y)
	{
		this(Names.make(Spot.class.getSimpleName()),x,y);
	}
	
	@Override
	public void outline(KvOutline output)
	{
		output.append(name,"("+circle.getCenterX()+","+circle.getCenterY()+")");
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

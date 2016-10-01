package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Spot implements Actor
{

	Group group;
	Circle circle;
	
	private static int index=0;
	
	public Spot(double x,double y)
	{
		this.circle = new Circle(x,y,0d);
		this.circle.setVisible(false);
		this.group = JfxUtility.makeGroup(index++,this,circle);
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

package org.geepawhill.contentment.core;

import org.geepawhill.contentment.step.LabelBoxStep;

public class LabelBox implements Actor
{
	final String text;
	
	public LabelBox(String text)
	{
		this.text = text;
	}
	
	public Step sketch(double xCenter, double yCenter)
	{
		return new LabelBoxStep(text, xCenter, yCenter);
	}
}

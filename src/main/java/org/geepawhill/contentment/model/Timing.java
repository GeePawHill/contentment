package org.geepawhill.contentment.model;

import org.geepawhill.contentment.timing.FixedTiming;

public interface Timing
{
	Timing INSTANT = new FixedTiming(.1d);
	
	public double getRatio();
	public double getAbsolute();
	public void setAbsolute(double ms);
}

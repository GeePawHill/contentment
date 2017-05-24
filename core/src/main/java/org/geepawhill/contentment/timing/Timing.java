package org.geepawhill.contentment.timing;

public interface Timing
{
	Timing INSTANT = new FixedTiming(.1d);
	
	public double getRatio();
	public double getAbsolute();
	public void setAbsolute(double ms);
}

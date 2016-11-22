package org.geepawhill.contentment.core;

public class FixedTiming implements Timing
{
	
	public static final Timing INSTANT = new FixedTiming(.1d);
	
	private double ms;

	public FixedTiming(double ms)
	{
		this.ms = ms;
		
	}

	@Override
	public double getRatio()
	{
		return 0d;
	}

	@Override
	public double getAbsolute()
	{
		return ms;
	}

	@Override
	public void setAbsolute(double ms)
	{
		throw new RuntimeException("Attempt to change absolute timing.");
	}

}

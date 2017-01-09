package org.geepawhill.contentment.timing;

import org.geepawhill.contentment.model.Timing;

public class FixedTiming implements Timing
{
	
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

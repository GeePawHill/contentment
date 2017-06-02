package org.geepawhill.contentment.timing;

public class FixedTiming implements Timing
{
	
	private double ms;

	FixedTiming(double ms)
	{
		this.ms = ms;
		
	}

	@Override
	public double weight()
	{
		return 0d;
	}

	@Override
	public double fixed()
	{
		return ms;
	}

	@Override
	public void fix(double ms)
	{
		throw new RuntimeException("Attempt to change absolute timing.");
	}
	
	@Override
	public String toString()
	{
		return String.format("         %8.2fms", ms);
	}

	@Override
	public boolean isWeighted()
	{
		return false;
	}

}

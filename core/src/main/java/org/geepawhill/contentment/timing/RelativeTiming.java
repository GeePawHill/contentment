package org.geepawhill.contentment.timing;

public class RelativeTiming implements Timing
{
	private double ratio;
	private double absolute;

	RelativeTiming(double ratio)
	{
		this.ratio = ratio;
		this.absolute = 0d;
	}

	@Override
	public double weight()
	{
		return ratio;
	}

	@Override
	public double fixed()
	{
		if(absolute==0d) throw new RuntimeException("0 absolute timing set!");
		return absolute;
	}

	@Override
	public void fix(double ms)
	{
		absolute = ms;
	}
	
	@Override
	public boolean isWeighted()
	{
		return absolute==0d;
	}
	
	@Override
	public String toString()
	{
		return String.format("%8.2f %8.2fms", ratio, absolute);
	}

}

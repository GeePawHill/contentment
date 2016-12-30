package org.geepawhill.contentment.core;

import org.geepawhill.contentment.timing.Timing;

public class RelativeTiming implements Timing
{
	
	private double ratio;
	private double absolute;

	public RelativeTiming(double ratio)
	{
		this.ratio = ratio;
		this.absolute = 0d;
	}

	@Override
	public double getRatio()
	{
		return ratio;
	}

	@Override
	public double getAbsolute()
	{
		if(absolute==0d) throw new RuntimeException("0 absolute timing set!");
		return absolute;
	}

	@Override
	public void setAbsolute(double ms)
	{
		absolute = ms;
	}

}

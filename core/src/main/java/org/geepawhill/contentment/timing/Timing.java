package org.geepawhill.contentment.timing;

public interface Timing
{
	public Timing INSTANT = new FixedTiming(.1d);
	
	static public Timing ms(double ms)
	{
		return new FixedTiming(ms);
	}
	
	static public Timing instant()
	{
		return INSTANT;
	}
	
	static public Timing weighted(double weight)
	{
		return new RelativeTiming(weight);
	}
	
	public boolean isWeighted();
	public double weight();
	public double fixed();
	public void fix(double ms);
}

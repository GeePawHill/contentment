package org.geepawhill.contentment.timing;

import java.util.ArrayList;

import org.geepawhill.contentment.step.Step;

public class Scheduler
{

	public static final String RELATIVES_BUT_NO_TOTAL = "Included relative timing with 0d parent.";
	public static final String ABSOLUTE_OVERRUN = "Absolutes are bigger than allocated.";
	private double accumulatedAbsolute;
	private double accumulatedRelative;

	public double build(double ms, ArrayList<Step> steps)
	{
		return build(ms,steps.toArray(new Step[0]));
	}
	
	public double build(double total, Step... steps)
	{
		Timing[] timings = new Timing[steps.length];
		int dest = 0;
		for (Step step : steps)
		{
			timings[dest++] = step.timing();
		}
		return build(total, timings);
	}

	public double build(double total, Timing... timings)
	{
		gatherTotals(timings);
		if (total == 0d) return noTotalSupplied();
		double distribute = amountLeftToDistribute(total);
		return distributeRelatives(distribute, timings);
	}

	private double distributeRelatives(double distribute, Timing... timings)
	{
		double afterDistribution = accumulatedAbsolute;
		for (Timing timing : timings)
		{
			double ratio = timing.getRatio();
			if (ratio != 0d)
			{
				double ms = (ratio * distribute) / accumulatedRelative;
				afterDistribution += ms;
				timing.setAbsolute(ms);
			}
		}
		return afterDistribution;
	}

	private double amountLeftToDistribute(double total)
	{
		double distribute = total - accumulatedAbsolute;
		if (distribute < 0d) throw new RuntimeException(ABSOLUTE_OVERRUN);
		return distribute;
	}

	private double noTotalSupplied()
	{
		if (accumulatedRelative > 0d) { throw new RuntimeException(RELATIVES_BUT_NO_TOTAL); }
		return accumulatedAbsolute;
	}
	
	private void gatherTotals(Timing... timings)
	{
		accumulatedAbsolute = 0d;
		accumulatedRelative = 0d;
		for (Timing timing : timings)
		{
			if (timing.getRatio() == 0d)
			{
				accumulatedAbsolute += timing.getAbsolute();
			}
			else
			{
				accumulatedRelative += timing.getRatio();
			}
		}
	}
}

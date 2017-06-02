package org.geepawhill.contentment.timing;

import java.util.ArrayList;

import org.geepawhill.contentment.step.Step;

public class Scheduler
{

	public static final String RELATIVES_BUT_NO_TOTAL = "Included relative timing with no absolute total.";
	public static final String ABSOLUTE_OVERRUN = "Absolutes are bigger than allocated.";
	private double accumulatedAbsolute;
	private double accumulatedRelative;

	public double schedule(double ms, ArrayList<Step> steps)
	{
		return schedule(ms,steps.toArray(new Step[0]));
	}
	
	public double schedule(double total, Step... steps)
	{
		Timing[] timings = new Timing[steps.length];
		int dest = 0;
		for (Step step : steps)
		{
			timings[dest++] = step.timing();
		}
		return schedule(total, timings);
	}

	public double schedule(double total, Timing... timings)
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
			double ratio = timing.weight();
			if (ratio != 0d)
			{
				double ms = (ratio * distribute) / accumulatedRelative;
				if(ms<.1d) ms = .1d;
				afterDistribution += ms;
				timing.fix(ms);
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
			if (timing.weight() == 0d)
			{
				accumulatedAbsolute += timing.fixed();
			}
			else
			{
				accumulatedRelative += timing.weight();
			}
		}
	}
}

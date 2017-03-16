package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.step.Instant;

public class TestInstant implements Instant
{
	
	public boolean isBefore;
	
	public TestInstant()
	{
		isBefore=true;
	}

	@Override
	public void before(Context context)
	{
		isBefore=true;
	}

	@Override
	public void after(Context context)
	{
		isBefore=false;
	}
	
}
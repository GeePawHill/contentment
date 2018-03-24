package org.geepawhill.contentment.fragments;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.rhythm.SimpleRhythm;

public class SyncTest
{
	private SimpleRhythm rhythm;
	private Context context;
	private Sync sync;
	
	@Before
	public void before()
	{
		context = new Context();
		rhythm = new SimpleRhythm();
		context.setRhythm(rhythm);
		sync = new Sync(1);
	}
	
	@Test
	public void continuesIfNotThereYet()
	{
		assertThat(sync.interpolate(context, 0)).isTrue();
	}
	
	@Test
	public void finishesIfThere()
	{
		rhythm.seekHard(2000);
		assertThat(sync.interpolate(context, 0)).isFalse();
	}

}

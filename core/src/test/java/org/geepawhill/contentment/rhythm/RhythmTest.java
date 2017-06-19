package org.geepawhill.contentment.rhythm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RhythmTest
{
	
	private Rhythm rhythm;

	@Before
	public void before()
	{
		rhythm = new Rhythm();
	}

	@Test
	public void newIsNotVideo()
	{
		assertThat(rhythm.isMedia()).isFalse();
	}
	
	@Test
	public void newBeatIsZero()
	{
		assertThat(rhythm.beat()).isEqualTo(0L);
	}
	
	@Test
	public void seekChangesClock()
	{
		rhythm.seekHard(100L);
		assertThat(rhythm.beat()).isEqualTo(100L);
	}
	
	@Test
	public void nonVideoUpdate()
	{
		rhythm.start();
		try
		{
			Thread.sleep(100L);
		}
		catch (InterruptedException expected)
		{
		}
		rhythm.update();
		assertThat(rhythm.beat()).isGreaterThan(100L);
	}
	
	
	

}

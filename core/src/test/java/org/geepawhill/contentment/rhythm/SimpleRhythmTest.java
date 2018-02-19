package org.geepawhill.contentment.rhythm;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SimpleRhythmTest extends JavaFxTest
{
	final static long SHORT_TIME = 20;
	
	private SimpleRhythm rhythm;

	@Before
	public void before()
	{
		rhythm = new SimpleRhythm();
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
	
	@Test(expected=RuntimeException.class)
	public void doublePlayThrows()
	{
		rhythm.play();
		rhythm.play();
	}
	
	@Test(expected=RuntimeException.class)
	public void doublePauseThrows()
	{
		rhythm.pause();
	}
	
	@Test
	public void changesBeatWhenPlayed() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME);
	}
	
	@Test
	public void pauseDoesntChangeBeat() throws InterruptedException
	{
		long atPause = rhythm.beat();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void pausePauses() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		rhythm.pause();
		long atPause = rhythm.beat();
		Thread.sleep(SHORT_TIME);
		rhythm.update();
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void playAfterPauseWorks() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		rhythm.pause();
		long atPause = rhythm.beat();
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME+atPause);
	}
}

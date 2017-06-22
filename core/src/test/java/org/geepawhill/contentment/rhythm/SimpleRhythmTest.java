package org.geepawhill.contentment.rhythm;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

public class SimpleRhythmTest extends JavaFxTest
{
	
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
		Thread.sleep(100);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(100);
	}
	
	@Test
	public void pauseDoesntChangeBeat() throws InterruptedException
	{
		long atPause = rhythm.beat();
		Thread.sleep(100);
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void pausePauses() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(100);
		rhythm.pause();
		long atPause = rhythm.beat();
		Thread.sleep(100);
		rhythm.update();
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void playAfterPauseWorks() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(100);
		rhythm.pause();
		long atPause = rhythm.beat();
		rhythm.play();
		Thread.sleep(100);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(100+atPause);
	}
}

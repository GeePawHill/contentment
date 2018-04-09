package org.geepawhill.experiment;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.*;

import javafx.animation.AnimationTimer;

public class AnimationExperiment extends ContentmentTest
{

	private AnimationTimer timer;
	private int lastNowCount;

	@Before
	public void before()
	{
		timer = new AnimationTimer() {

			@Override
			public void handle(long now)
			{
				timerCallback(now);
			} 
		};
	}
	
	@Test
	public void lastNowNotUpdatedUntilStarted() throws Exception
	{
		lastNowCount=0;
		Thread.sleep(100);
		assertThat(lastNowCount).isEqualTo(0);
		timer.start();
		Thread.sleep(100);
		timer.stop();
		assertThat(lastNowCount).isGreaterThan(0);
	}

	protected void timerCallback(long now)
	{
		lastNowCount+=1;
	}

}

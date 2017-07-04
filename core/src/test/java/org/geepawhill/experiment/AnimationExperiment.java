package org.geepawhill.experiment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

import javafx.animation.AnimationTimer;

public class AnimationExperiment extends JavaFxTest
{

	private AnimationTimer timer;
	private long lastNow;
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
		lastNow = now;
		lastNowCount+=1;
	}

}

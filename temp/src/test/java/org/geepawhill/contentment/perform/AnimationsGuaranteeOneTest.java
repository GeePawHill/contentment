package org.geepawhill.contentment.perform;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;

import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.Test;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.util.Duration;

public class AnimationsGuaranteeOneTest extends ContentmentTest
{

	private boolean oneReceived;

	@Test
	public void test() throws Exception
	{
		System.out.println(Platform.isFxApplicationThread());
		oneReceived = false;
		CountDownLatch latch = new CountDownLatch(1);
		Transition transition = new Transition()
		{

			{
				System.out.println(getCycleDuration());
				setCycleDuration(Duration.ONE);
			}

			@Override
			protected void interpolate(double frac)
			{
				if (frac == 1d)
				{
					oneReceived = true;
					latch.countDown();
				}

			}
		};
		runner.play(transition);
		assertThat(oneReceived).isTrue();
	}

}

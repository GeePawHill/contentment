package org.geepawhill.contentment.perform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.geepawhill.contentment.test.JavaFxRunner;
import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Test;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.util.Duration;

public class AnimationsGuaranteeOneTest extends JavaFxTest
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

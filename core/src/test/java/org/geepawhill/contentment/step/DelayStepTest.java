package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;

import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Test;

public class DelayStepTest extends JavaFxTest
{

	@Test
	public void takesSomeTime()
	{
		LocalDateTime start = LocalDateTime.now();
		runner.slow(new DelayStep(100d));
		assertThat(Duration.between(start, LocalDateTime.now()).toMillis()).isStrictlyBetween(80L, 150L);
	}

	@Test
	public void skips()
	{
		LocalDateTime start = LocalDateTime.now();
		runner.context.skipDelays(true);
		runner.slow(new DelayStep(1000d));
		assertThat(Duration.between(start, LocalDateTime.now()).toMillis()).isLessThan(100L);
	}

}

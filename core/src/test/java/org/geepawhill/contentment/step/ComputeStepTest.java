package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.junit.Before;
import org.junit.Test;

public class ComputeStepTest extends JavaFxTest
{

	private boolean result;
	private ComputeStep step;

	@Before
	public void before()
	{
		step = new ComputeStep(this::compute);
	}

	public void compute(Context context, double fraction)
	{
		this.result = true;
	}

	@Test
	public void slowCalls()
	{
		runner.slow(new Sequence().add(step));
		assertThat(result).isTrue();
	}

	@Test
	public void fastCalls()
	{
		runner.fast(new Sequence().add(step));
		assertThat(result).isTrue();
	}
}

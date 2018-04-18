package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.*;
import org.geepawhill.contentment.timing.Timing;
import org.junit.Test;

public class AtomStepTest extends ContentmentTest
{
	

	@Test
	public void slowTest()
	{
		TestAtom atom = new TestAtom();
		Single step = new Single(Timing.ms(40d),atom);
		runner.slow(step);
		assertThat(atom.fractions.size()).isGreaterThan(2);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void fastTest()
	{
		TestAtom atom = new TestAtom();
		Single step = new Single(Timing.ms(40d),atom);
		runner.slow(step);
		assertThat(atom.fractions).contains(0d,1d);
	}

}

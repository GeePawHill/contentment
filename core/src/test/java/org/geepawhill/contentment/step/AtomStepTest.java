package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestAtom;
import org.geepawhill.contentment.timing.Timing;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AtomStepTest extends JavaFxTest
{
	

	@Test
	public void slowTest()
	{
		TestAtom atom = new TestAtom();
		AtomStep step = new AtomStep(Timing.ms(40d),atom);
		runner.slow(step);
		assertThat(atom.fractions.size()).isGreaterThan(2);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void fastTest()
	{
		TestAtom atom = new TestAtom();
		AtomStep step = new AtomStep(Timing.ms(40d),atom);
		runner.slow(step);
		assertThat(atom.fractions).contains(0d,1d);
	}

}

package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestAtom;
import org.junit.Test;

public class AtomStepTest extends JavaFxTest
{
	

	@Test
	public void slowTest()
	{
		TestAtom atom = new TestAtom();
		AtomStep step = new AtomStep(40,atom);
		runner.slow(step);
		assertThat(atom.fractions.size()).isGreaterThan(2);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void fastTest()
	{
		TestAtom atom = new TestAtom();
		AtomStep step = new AtomStep(40,atom);
		runner.slow(step);
		assertThat(atom.fractions).contains(0d,1d);
	}

}

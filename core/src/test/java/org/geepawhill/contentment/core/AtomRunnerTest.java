package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestAtom;
import org.junit.Test;

public class AtomRunnerTest extends JavaFxTest
{
	
	@Test
	public void atZeroTime()
	{
		TestAtom atom = new TestAtom();
		runner.play(0L,atom);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void atSmallTime()
	{
		TestAtom atom = new TestAtom();
		runner.play(40L,atom);
		assertThat(atom.fractions).contains(0d,1d);
		assertThat(atom.fractions.size()).isGreaterThan(2);
	}


}

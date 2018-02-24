package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.ContentmentTest;
import org.geepawhill.contentment.test.JavaFxRunner;
import org.geepawhill.contentment.test.TestAtom;
import org.junit.Test;

import javafx.application.Platform;

public class AtomRunnerTest extends ContentmentTest
{
	JavaFxRunner runner = new JavaFxRunner();
	
	@Test
	public void atZeroTime()
	{
		assertThat(Platform.isFxApplicationThread()).isFalse();
		TestAtom atom = new TestAtom();
		runner.play(0L,atom);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void atSmallTime()
	{
		assertThat(Platform.isFxApplicationThread()).isFalse();
		TestAtom atom = new TestAtom();
		runner.play(40L,atom);
		assertThat(atom.fractions).contains(0d,1d);
		assertThat(atom.fractions.size()).isGreaterThan(2);
	}
}

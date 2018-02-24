package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.Before;
import org.junit.Test;
public class PhraseTest extends ContentmentTest
{
	private Phrase onlyOne;
	private Phrase both;
	private TestNote one;
	private TestNote two;
	private boolean gotFinish;
	private OnFinished recordFinish;

	@Before
	public void before()
	{
		one = new TestNote();
		two = new TestNote();

		onlyOne = new Phrase();
		onlyOne.add(one);

		both = new Phrase();
		both.add(one);
		both.add(two);
		
		gotFinish = false;

		recordFinish = () -> gotFinish = true;
	}

	@Test
	public void fastOne()
	{
		onlyOne.fast(getContext());
		assertPlayed(one);
	}

	@Test
	public void fastBoth()
	{
		both.fast(getContext());
		assertPlayed(one);
		assertPlayed(two);
	}

	@Test
	public void slowOne()
	{
		onlyOne.slow(getContext(), recordFinish);
		assertPlaying(one);
		one.finish(getContext());
		assertPlayed(one);
		assertThat(gotFinish).isTrue();
	}

	@Test
	public void slowBoth()
	{
		both.slow(getContext(), recordFinish);
		assertPlaying(one);
		assertUndone(two);
		one.finish(getContext());
		assertPlayed(one);
		assertPlaying(two);
		two.finish(getContext());
		assertPlayed(two);
		assertThat(gotFinish).isTrue();
	}

	private void assertPlayed(TestNote Step)
	{
		assertThat(Step.state).isEqualTo(TestNote.State.Played);
	}

	private void assertUndone(TestNote Step)
	{
		assertThat(Step.state).isEqualTo(TestNote.State.Undone);
	}

	private void assertPlaying(TestNote Step)
	{
		assertThat(Step.state).isEqualTo(TestNote.State.Playing);
	}

}

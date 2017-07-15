package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

public class PhraseTest extends JavaFxTest
{
	private Phrase empty;
	private Phrase onlyOne;
	private Phrase both;
	private TestNote one;
	private TestNote two;
	private boolean gotFinish;
	private OnFinished recordFinish;

	@Before
	public void before()
	{
		one = new TestNote(1L);
		two = new TestNote(9L);

		empty = new Phrase();

		onlyOne = new Phrase();
		onlyOne.add(one);

		both = new Phrase();
		both.add(one);
		both.add(two);
		
		gotFinish = false;

		recordFinish = () -> gotFinish = true;
	}

	@Test
	public void sumsMs()
	{
		assertThat(empty.timing().ms()).isEqualTo(0L);
		assertThat(onlyOne.timing().ms()).isEqualTo(1L);
		assertThat(both.timing().ms()).isEqualTo(10L);
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

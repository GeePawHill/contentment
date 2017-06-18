package org.geepawhill.contentment.perform;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class PhraseTest
{
	private Phrase empty;
	private Phrase onlyOne;
	private Phrase both;
	private Context context = new Context(new Group());
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
		assertThat(empty.ms()).isEqualTo(0L);
		assertThat(onlyOne.ms()).isEqualTo(1L);
		assertThat(both.ms()).isEqualTo(10L);
	}

	@Test
	public void fastOne()
	{
		onlyOne.fast(context);
		assertPlayed(one);
	}

	@Test
	public void fastBoth()
	{
		both.fast(context);
		assertPlayed(one);
		assertPlayed(two);
	}

	@Test
	public void undoOne()
	{
		onlyOne.fast(context);
		onlyOne.undo(context);
		assertUndone(one);
	}

	@Test
	public void undoBoth()
	{
		both.fast(context);
		both.undo(context);
		assertUndone(one);
		assertUndone(two);
	}

	@Test
	public void slowOne()
	{
		onlyOne.slow(context, recordFinish);
		assertPlaying(one);
		one.finish(context);
		assertPlayed(one);
		assertThat(gotFinish).isTrue();
	}

	@Test
	public void slowBoth()
	{
		both.slow(context, recordFinish);
		assertPlaying(one);
		assertUndone(two);
		one.finish(context);
		assertPlayed(one);
		assertPlaying(two);
		two.finish(context);
		assertPlayed(two);
		assertThat(gotFinish).isTrue();
	}

	private void assertPlayed(TestNote playable)
	{
		assertThat(playable.state).isEqualTo(TestNote.State.Played);
	}

	private void assertUndone(TestNote playable)
	{
		assertThat(playable.state).isEqualTo(TestNote.State.Undone);
	}

	private void assertPlaying(TestNote playable)
	{
		assertThat(playable.state).isEqualTo(TestNote.State.Playing);
	}
}

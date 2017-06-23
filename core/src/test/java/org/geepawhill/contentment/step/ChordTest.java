package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class ChordTest
{
	private Chord empty;
	private Chord onlyOne;
	private Chord both;
	private Context context = new Context(new Group(), new SimpleRhythm());
	private TestNote one;
	private TestNote two;
	private boolean gotFinish;
	private OnFinished recordFinish;

	@Before
	public void before()
	{
		one = new TestNote(1L);
		two = new TestNote(9L);

		empty = new Chord();

		onlyOne = new Chord();
		onlyOne.add(one);

		both = new Chord();
		both.add(one);
		both.add(two);
		
		gotFinish = false;

		recordFinish = () -> gotFinish = true;
	}

	@Test
	public void maxesMs()
	{
		assertThat(empty.timing().ms()).isEqualTo(0L);
		assertThat(onlyOne.timing().ms()).isEqualTo(1L);
		assertThat(both.timing().ms()).isEqualTo(9L);
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
		assertPlaying(two);
		one.finish(context);
		assertThat(gotFinish).isFalse();
		two.finish(context);
		assertPlayed(one);
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
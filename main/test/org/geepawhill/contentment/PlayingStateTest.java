package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.geepawhill.contentment.TestStep.*;

public class PlayingStateTest {

	private Context context;
	
	@Before
	public void before()
	{
		context = new Context();
		context.stepper.load(oneStepSequence);
	}

	@Test
	public void playDoesNothing() {
		assertEquals(context.playing,context.playing.onPlay(context));
	}
	
	@Test
	public void pauseTellsCurrentToPause() {
		assertEquals(context.paused,context.playing.onPause(context));
		assertTrue(oneStep.paused);
	}
	
	@Test
	public void resumeDoesNothing() {
		assertEquals(context.playing,context.playing.onResume(context));
	}
	
	@Test
	public void stopStops() {
		assertEquals(context.stopped,context.playing.onStop(context));
	}

}

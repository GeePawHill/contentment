package org.geepawhill.contentment;

import static org.geepawhill.contentment.TestStep.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StoppedStateTest {

	private Context context;
	
	@Before
	public void before()
	{
		context = new Context();
		context.stepper.load(oneStepSequence);
	}

	@Test
	public void stopDoesNothing() {
		assertEquals(context.stopped, context.stopped.onStop(context));
	}
	
	@Test
	public void playPlays() {
		assertEquals(context.playing, context.stopped.onPlay(context));
		assertTrue(oneStep.isPlaying);
	}
	
	@Test
	public void pauseDoesNothing() {
		assertEquals(context.stopped, context.stopped.onPause(context));
	}
	
	@Test
	public void resumeDoesNothing() {
		assertEquals(context.stopped, context.stopped.onResume(context));
	}
}

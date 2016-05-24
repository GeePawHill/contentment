package org.geepawhill.contentment;

import static org.geepawhill.contentment.TestStep.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PausedStateTest {

	private Context context;
	
	@Before
	public void before()
	{
		context = new Context();
		context.stepper.load(oneStepSequence);
	}

	@Test
	public void stopStops() {
		assertEquals(context.stopped,context.paused.onStop(context));
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void playResumes() {
		oneStep.isPlaying=true;
		oneStep.isPaused=true;
		assertEquals(context.playing,context.paused.onPlay(context));
		assertTrue(oneStep.isPlaying);
		assertFalse(oneStep.isPaused);
	}
	
	@Test
	public void resumeResumes() {
		oneStep.isPlaying=true;
		oneStep.isPaused=true;
		assertEquals(context.playing,context.paused.onResume(context));
		assertTrue(oneStep.isPlaying);
		assertFalse(oneStep.isPaused);
	}
	
	@Test
	public void pauseDoesNothing() {
		assertEquals(context.paused,context.paused.onPause(context));
	}

}

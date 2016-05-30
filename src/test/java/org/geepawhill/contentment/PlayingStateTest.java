package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.layout.Pane;

import static org.geepawhill.contentment.TestStep.*;

public class PlayingStateTest {

	private Context context;
	
	@Before
	public void before()
	{
		Pane canvas = new Pane();
		context = new Context(canvas);
		context.stepper.load(oneStepSequence);
	}

	@Test
	public void playDoesNothing() {
		assertEquals(context.playing,context.playing.onPlay(context));
	}
	
	@Test
	public void pauseTellsCurrentToPause() {
		assertEquals(context.paused,context.playing.onPause(context));
		assertTrue(oneStep.isPaused);
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

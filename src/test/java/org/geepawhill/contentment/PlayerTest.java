package org.geepawhill.contentment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.TestStep;
import org.geepawhill.contentment.core.TestStop;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.Group;

public class PlayerTest
{
	static public TestStep nonStopFirst = new TestStep();
	static public TestStop stopFirst = new TestStop();
	static public TestStep nonStopSecond = new TestStep();
	static public TestStop stopSecond = new TestStop();
	static public TestStep nonStopThird = new TestStep();
	
	static public Sequence mixedSequence = new Sequence(nonStopFirst,stopFirst,nonStopSecond,stopSecond,nonStopThird);


	private Player player;
	Group canvas;

	@Before
	public void before()
	{
		canvas = new Group();
		player = new Player(canvas);
	}

	@Test
	public void newIsEmpty()
	{
		assertEquals(0, player.size());
	}

	@Test
	public void newIsBeforeAll()
	{
		assertEquals(0, player.current());
		assertBefore();
	}

	@Test
	public void loadLoads()
	{
		player.reset(mixedSequence);
		assertEquals(5, player.size());
	}

	@Test
	public void loadBeforesAll()
	{
		nonStopSecond.isBefore = false;
		player.reset(mixedSequence);
		assertTrue(nonStopSecond.isBefore);
		assertBefore();
	}
	
	@Test
	public void playGoesToEnd()
	{
		player.reset(mixedSequence);
		player.play();
		nonStopFirst.finishPlaying();
		stopFirst.finishPlaying();
		nonStopSecond.finishPlaying();
		stopSecond.finishPlaying();
		nonStopThird.finishPlaying();
		assertAfter();
	}
	
	@Test
	public void pauseAndResume()
	{
		player.reset(new Sequence(nonStopFirst));
		player.play();
		assertPlaying();
		player.pause();
		assertTrue(nonStopFirst.isPaused);
		assertPaused();
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		nonStopFirst.finishPlaying();
		assertFalse(nonStopFirst.isBefore);
		assertAfter();
	}
	
	@Test
	public void forwardBreaksPlaying()
	{
		player.reset(new Sequence(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.forward();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
	}

	@Test
	public void forwardBreaksPaused()
	{
		player.reset(new Sequence(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.pause();
		assertTrue(nonStopFirst.isPaused);
		player.forward();
		assertFalse(nonStopFirst.isPaused);
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
	}
	
	@Test
	public void backwardBreaksPlaying()
	{
		player.reset(new Sequence(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.backward();
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
	}

	@Test
	public void backwardBreaksPaused()
	{
		player.reset(new Sequence(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.pause();
		assertTrue(nonStopFirst.isPaused);
		player.backward();
		assertFalse(nonStopFirst.isPaused);
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
	}
	
	@Test
	public void forwardBeforeStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence(nonStopFirst,stopFirst));
		player.forward();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
	}
	
	@Test
	public void playOneBeforeStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence(nonStopFirst,stopFirst));
		player.playOne();
		nonStopFirst.finishPlaying();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
	}

	
	@Test
	public void forwardOnStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence(stopFirst,nonStopFirst,stopSecond));
		player.forward();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopSecond.isBefore);
		assertEquals(stopSecond,player.currentStep());
	}
	
	@Test
	public void playOneOnStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence(stopFirst,nonStopFirst,stopSecond));
		player.playOne();
		stopFirst.finishPlaying();
		nonStopFirst.finishPlaying();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopSecond.isBefore);
		assertEquals(stopSecond,player.currentStep());
	}


	@Test
	public void forwardOnLastStopGoesToEnd()
	{
		player.reset(new Sequence(stopFirst,nonStopSecond,nonStopThird));
		player.forward();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopSecond.isPlaying);
		assertFalse(nonStopSecond.isBefore);
		assertFalse(nonStopThird.isPlaying);
		assertEquals(nonStopThird,player.currentStep());
		assertFalse(nonStopThird.isBefore);
		assertAfter();
	}
	
	@Test
	public void backwardWithNoStopGoesToZero()
	{
		player.reset(new Sequence(nonStopFirst,nonStopSecond));
		player.forward();
		player.backward();
		assertFalse(nonStopSecond.isPlaying);
		assertTrue(nonStopSecond.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
		assertEquals(nonStopFirst,player.currentStep());
		assertBefore();
	}
	
	@Test
	public void backwardOnStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence(stopFirst,stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
		assertBefore();
	}
	
	@Test
	public void backwardOnNonStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence(stopFirst,nonStopSecond));
		player.forward();
		player.backward();
		assertFalse(nonStopSecond.isPlaying);
		assertTrue(nonStopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
		assertBefore();
	}
	
	@Test
	public void backwardBeforeStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence(stopFirst,nonStopFirst,stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
		assertBefore();
	}

	
	@Test
	public void backwardSkGoesBeforePreviousStop()
	{
		player.reset(new Sequence(stopFirst,stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst,player.currentStep());
		assertBefore();
	}


	@Test
	public void playOneOnLastStopGoesToEnd()
	{
		player.reset(new Sequence(stopFirst,nonStopSecond,nonStopThird));
		player.playOne();
		stopFirst.finishPlaying();
		nonStopSecond.finishPlaying();
		nonStopThird.finishPlaying();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopSecond.isPlaying);
		assertFalse(nonStopSecond.isBefore);
		assertFalse(nonStopThird.isPlaying);
		assertEquals(nonStopThird,player.currentStep());
		assertFalse(nonStopThird.isBefore);
		assertAfter();
	}
	
	

	@Ignore
	@Test
	public void forwardStopsOnMarkedStep()
	{
//		TestStep unmarked = new TestStep();
//		Sequence mixedSequence = new Sequence();
//		mixedSequence.marked(TestStep.step2);
//		mixedSequence.add(false,unmarked);
//		mixedSequence.marked(TestStep.twoStep);
//		player.reset(mixedSequence);
//		player.forward();
//		assertTrue(player.currentStep() instanceof Stop);
//		assertFalse(TestStep.step2.isBefore);
//		assertFalse(unmarked.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
	}

	@Ignore
	@Test
	public void forwardWhileBeforeSteps()
	{
//		player.reset(TestStep.twoStepSequence);
//		player.forward();
//		assertEquals(1, player.current());
//		assertFalse(TestStep.step2.isBefore);
//		assertBefore();
	}

	@Ignore
	@Test
	public void forwardWhilePlayingSteps()
	{
		player.reset(mixedSequence);
		player.play();
		player.forward();
		assertEquals(1, player.current());
		assertFalse(nonStopSecond.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
		assertBefore();
	}

	@Ignore
	@Test
	public void forwardWhilePausedSteps()
	{
		player.reset(mixedSequence);
		player.play();
		player.pause();
		player.forward();
		assertEquals(1, player.current());
		assertFalse(nonStopSecond.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
		assertBefore();
	}

	@Test
	public void forwardNoopsAtEnd()
	{
		player.reset(mixedSequence);
		for(int i=0;i<6;i++) player.forward();
		assertEquals(nonStopThird,player.currentStep());
		assertFalse(nonStopThird.isBefore);
		assertAfter();
	}

	@Ignore
	@Test
	public void backwardStopsOnMarkedStep()
	{
//		player.reset(mixedSequence);
//		player.forward();
//		player.backward();
//		assertEquals(TestStep.step2, player.currentStep());
//		assertTrue(TestStep.step2.isBefore);
//		assertTrue(unmarked.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
	}

//	@Test
//	public void backwardWhileBeforeGoesBackward()
//	{
//		player.reset(TestStep.mixedSequence);
//		player.play();
//		step0.finishPlaying(player.context);
//		step1.finishPlaying(player.context);
//		player.backward();
//		assertEquals(1, player.current());
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}
//
//	@Test
//	public void backwardWhileAfterDoesBefore()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.forward();
//		player.forward();
//		player.backward();
//		assertEquals(1, player.current());
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}
//
//	@Test
//	public void backwardWhilePlayingDoesBefore()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.play();
//		TestStep.step2.finishPlaying(player.context);
//		player.backward();
//		assertEquals(1, player.current());
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}
//
//	@Test
//	public void backwardWhilePausedDoesBefore()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.play();
//		TestStep.step2.finishPlaying(player.context);
//		player.pause();
//		player.backward();
//		assertEquals(1, player.current());
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}
//
//	@Test
//	public void backwardackwardNoopsAtBeginning()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.backward();
//		assertEquals(0, player.current());
//		assertTrue(TestStep.step2.isBefore);
//	}
//
//	@Test
//	public void pauseBeforeNoops()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.pause();
//		assertTrue(TestStep.step2.isBefore);
//	}
//
//	@Test
//	public void pauseWhilePlayingPauses()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		player.pause();
//		assertTrue(TestStep.step2.isPaused);
//		assertPaused();
//	}
//
//	@Test
//	public void pauseWhilePausedPlays()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		player.pause();
//		assertTrue(TestStep.step2.isPaused);
//		assertPaused();
//		player.pause();
//		assertFalse(TestStep.step2.isPaused);
//		assertPlaying();
//	}
//
//	@Test
//	public void pauseAfterNoops()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.forward();
//		TestStep.step2.isChanged = false;
//		player.pause();
//		assertFalse(TestStep.step2.isChanged);
//	}
//
//	@Test
//	public void seekNoChangeDoesBefore()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.forward();
//		player.seek(0);
//		assertEquals(0, player.current());
//		assertTrue(TestStep.step2.isBefore);
//	}
//
//	@Test
//	public void seekForward()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.seek(1);
//		assertEquals(1, player.current());
//		assertFalse(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//	}
//
//	@Test
//	public void seekBackward()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.forward();
//		player.forward();
//		player.seek(0);
//		assertEquals(0, player.current());
//		assertTrue(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//	}
//
//	@Test
//	public void seekCurrentResets()
//	{
//		player.reset(TestStep.twoStepSequence);
//		TestStep.step2.isBefore = false;
//		player.seek(0);
//		assertEquals(0, player.current());
//		assertTrue(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//	}
//
//	@Test
//	public void seekBeforeStart()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.forward();
//		player.forward();
//		player.seek(-20);
//		assertEquals(0, player.current());
//		assertTrue(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//	}
//
//	@Test
//	public void seekAfterEnd()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.seek(20);
//		assertEquals(1, player.current());
//		assertFalse(TestStep.step2.isBefore);
//		assertFalse(TestStep.twoStep.isBefore);
//		assertEquals(PlayState.After, player.state());
//	}
//
//	@Test
//	public void seekWhilePlayingStops()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.play();
//		player.seek(1);
//		assertEquals(1, player.current());
//		assertFalse(TestStep.step2.isBefore);
//		assertFalse(TestStep.step2.isPlaying);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertFalse(TestStep.twoStep.isPlaying);
//	}
//
//	@Test
//	public void seekWhilePausedStops()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.play();
//		player.pause();
//		player.seek(1);
//		assertEquals(1, player.current());
//		assertFalse(TestStep.step2.isBefore);
//		assertFalse(TestStep.step2.isPlaying);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertFalse(TestStep.twoStep.isPlaying);
//	}
//
//	@Test
//	public void playWhileBeforePlays()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		assertTrue(TestStep.step2.isPlaying);
//		assertPlaying();
//	}
//
//	@Test
//	public void playWhilePlayingNoops()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		assertTrue(TestStep.step2.isPlaying);
//		assertTrue(TestStep.step2.isChanged);
//		TestStep.step2.isChanged = false;
//		player.play();
//		assertFalse(TestStep.step2.isChanged);
//	}
//
//	@Test
//	public void playWhilePausedResumes()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		assertTrue(TestStep.step2.isPlaying);
//		assertTrue(TestStep.step2.isChanged);
//		player.pause();
//		assertTrue(TestStep.step2.isPaused);
//		player.play();
//		assertTrue(TestStep.step2.isPlaying);
//	}
//
//	@Test
//	public void playWhileAfterNoops()
//	{
//		player.reset(TestStep.oneStepSequence);
//		player.play();
//		TestStep.step2.finishPlaying(player.context);
//		TestStep.step2.isChanged = false;
//		player.play();
//		assertFalse(TestStep.step2.isChanged);
//		assertAfter();
//	}
//
//	@Test
//	public void playChainsToAllSteps()
//	{
//		player.reset(TestStep.twoStepSequence);
//		player.play();
//		TestStep.step2.finishPlaying(player.context);
//		assertTrue(TestStep.twoStep.isPlaying);
//		TestStep.twoStep.finishPlaying(player.context);
//		assertAfter();
//	}
//
//	@Test
//	public void playOneDoesNotChain()
//	{
//		player.reset(TestStep.mixedSequence);
//		player.playOne();
//		TestStep.step2.finishPlaying(player.context);
//		assertFalse(TestStep.twoStep.isPlaying);
//		assertBefore();
//	}
//
//	@Test
//	public void playOneStopsOnMarked()
//	{
//		player.reset(mixedSequence);
//		player.playOne();
//		step1.finishPlaying(player.context);
//		assertTrue(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}
//	
//	@Test
//	public void playOnePlaysThroughUnmarked()
//	{
//		TestStep unmarked = new TestStep();
//		Sequence mixedSequence = new Sequence();
//		mixedSequence.add(false,unmarked);
//		mixedSequence.marked(TestStep.step2);
//		mixedSequence.marked(TestStep.twoStep);
//		player.reset(mixedSequence);
//		player.playOne();
//		assertTrue(unmarked.isPlaying);
//		unmarked.finishPlaying(player.context);
//		assertTrue(TestStep.step2.isPlaying);
//		TestStep.step2.finishPlaying(player.context);
//		assertEquals(2, player.current());
//		assertFalse(unmarked.isBefore);
//		assertFalse(TestStep.step2.isBefore);
//		assertTrue(TestStep.twoStep.isBefore);
//		assertBefore();
//	}

	@Ignore
	@Test
	public void playOneStopsChainingWhilePlaying()
	{
		player.reset(mixedSequence);
		player.play();
		player.playOne();
		stopFirst.finishPlaying();
		assertTrue(nonStopSecond.isPlaying);
		assertEquals(nonStopSecond, player.currentStep());
		assertBefore();
	}

	private void assertAfter()
	{
		assertEquals(PlayState.After, player.state());
	}

	private void assertPlaying()
	{
		assertEquals(PlayState.Playing, player.state());
	}

	private void assertBefore()
	{
		assertEquals(PlayState.Before, player.state());
	}

	private void assertPaused()
	{
		assertEquals(PlayState.Paused, player.state());
	}

}

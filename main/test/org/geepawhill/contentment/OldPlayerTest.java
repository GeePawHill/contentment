package org.geepawhill.contentment;

import static org.junit.Assert.*;
import static org.geepawhill.contentment.PlayState.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import org.junit.Before;
import org.junit.Test;

public class OldPlayerTest
{

	private final class TestingAction implements Action
	{
		private EventHandler<ActionEvent> onFinished;
		public boolean wasPaused;
		public boolean wasResumed;
		public boolean wasPlayed;
		
		TestingAction()
		{
			wasPaused=false;
			wasPlayed=false;
			wasResumed=false;
		}

		@Override
		public void play(Pane destination, EventHandler<ActionEvent> onFinished)
		{
			this.onFinished = onFinished;
			if(wasPaused)
			{
				wasResumed=true;
			}
			this.wasPlayed=true;
		}
		
		public void finishPlaying()
		{
			onFinished.handle(null);
		}

		@Override
		public void pause()
		{
			wasPaused=true;
			
		}
	}

	private OldPlayer player;
	private ActionList actions;
	
	
	@Before
	public void before()
	{
		actions = new ActionList();
		player = new OldPlayer();
	}

	@Test
	public void startsBefore()
	{
		assertEquals(Before,player.status());
	}
	
	@Test
	public void emptyPlaysToAfter()
	{
		player.play(null);
		assertEquals(After,player.status());
	}
	
	@Test
	public void resetResets()
	{
		player.play(null);
		player.reset();
		assertEquals(Before,player.status());
	}
	
	@Test
	public void resetActionsResets()
	{
		player.play(null);
		player.reset(actions);
		assertEquals(Before,player.status());
	}
	
	@Test
	public void playPlaysFromStart()
	{
		TestingAction action = new TestingAction();
		actions.add(action);
		player.reset(actions);
		player.play(null);
		assertTrue(action.wasPlayed);
		assertFalse(action.wasResumed);
		assertFalse(action.wasPaused);
		assertEquals(Playing,player.status());
		action.finishPlaying();
	}
	
	@Test
	public void pausePauses()
	{
		TestingAction action = new TestingAction();
		actions.add(action);
		player.reset(actions);
		player.play(null);
		assertEquals(Playing,player.status());
		player.pause();
		assertEquals(Paused,player.status());
		assertTrue(action.wasPaused);
	}
	
	@Test
	public void playResumesFromPause()
	{
		TestingAction action = new TestingAction();
		actions.add(action);
		player.reset(actions);
		player.play(null);
		assertEquals(Playing,player.status());
		player.pause();
		assertEquals(Paused,player.status());
		assertTrue(action.wasPaused);
		player.play(null);
		action.finishPlaying();
		assertTrue(action.wasResumed);
	}

}

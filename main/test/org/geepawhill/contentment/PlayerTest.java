package org.geepawhill.contentment;

import static org.junit.Assert.*;
import static org.geepawhill.contentment.PlayState.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{

	private final class TestingAction implements Action
	{
		private EventHandler<ActionEvent> onFinished;

		@Override
		public void play(Pane destination, EventHandler<ActionEvent> onFinished)
		{
			this.onFinished = onFinished;
		}
		
		public void finishPlaying()
		{
			onFinished.handle(null);
		}
	}

	private Player player;
	private ActionList actions;
	
	
	@Before
	public void before()
	{
		actions = new ActionList();
		player = new Player();
	}

	@Test
	public void startsBefore()
	{
		assertEquals(Before,player.status());
	}
	
	@Test
	public void emptyPlaysToAfter()
	{
		player.play();
		assertEquals(After,player.status());
	}
	
	@Test
	public void resetResets()
	{
		player.play();
		player.reset();
		assertEquals(Before,player.status());
	}
	
	@Test
	public void resetActionsResets()
	{
		player.play();
		player.reset(actions);
		assertEquals(Before,player.status());
	}
	
	@Test
	public void playPlays()
	{
		TestingAction action = new TestingAction();
		actions.add(action);
		player.reset(actions);
		player.play();
		assertEquals(Playing,player.status());
		action.finishPlaying();
		assertEquals(After,player.status());
	}

}

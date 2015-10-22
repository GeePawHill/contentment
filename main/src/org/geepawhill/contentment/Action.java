package org.geepawhill.contentment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public interface Action
{
	public void play(Pane destination,EventHandler<ActionEvent> onFinished);
}

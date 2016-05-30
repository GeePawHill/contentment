package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

public interface Step
{
	public void jumpBefore();
	public void play();
	public void pause();
	public void resume();
	public void stop();
	void jumpAfter(Pane canvas);
}

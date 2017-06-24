package org.geepawhill.contentment.rhythm;

import javafx.beans.property.LongProperty;

public interface Rhythm
{

	LongProperty beatProperty();

	long beat();

	void seekHard(long ms);

	void update();

	void play();
	
	void pause();

	boolean isPlaying();
}
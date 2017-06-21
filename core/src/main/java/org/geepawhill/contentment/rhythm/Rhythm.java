package org.geepawhill.contentment.rhythm;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class Rhythm
{
	private SimpleLongProperty beatProperty;
	
	
	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
	}

	public LongProperty beatProperty()
	{
		return beatProperty;
	}
	
	public long beat()
	{
		return beatProperty.get();
	}

	public void seekHard(long ms)
	{
		beatProperty.set(ms);
	}
	
	public void seekSoft(long ms)
	{
		if(beat()<ms) seekHard(ms);
	}
	
	public void update()
	{
		beatProperty.set(getPlayerTime());
	}

	private long getPlayerTime()
	{
		return 1L;
	}
}

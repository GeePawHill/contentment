package org.geepawhill.contentment.rhythm;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.WritableLongValue;

public class Rhythm
{

	private SimpleLongProperty beatProperty;
	private LocalDateTime then;

	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
	}

	public boolean isMedia()
	{
		return false;
	}

	public LongProperty beatProperty()
	{
		return beatProperty;
	}

	public long beat()
	{
		return beatProperty.get();
	}

	public void seek(long ms)
	{
		beatProperty.set(ms);
	}

	public void start()
	{
		then = LocalDateTime.now();
	}

	public void update()
	{
		LocalDateTime now = LocalDateTime.now();
		beatProperty.set(Duration.between(then, now).toMillis());
	}

}

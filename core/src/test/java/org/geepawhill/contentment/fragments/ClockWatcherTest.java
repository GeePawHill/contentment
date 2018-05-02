package org.geepawhill.contentment.fragments;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleLongProperty;

import static org.geepawhill.contentment.test.ContentmentAssertions.*;

public class ClockWatcherTest
{

	@Before
	public void before()
	{
		
	}
	
	@Test
	public void readsProperty()
	{
		SimpleLongProperty beat = new SimpleLongProperty(100);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("100");
	}
	
	@Test
	public void changesWhenPropertyChanges()
	{
		SimpleLongProperty beat = new SimpleLongProperty(100);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("100");
		beat.set(200);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("200");
	}
}

package org.geepawhill.contentment.fragments;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

import static org.assertj.core.api.Assertions.*;

import org.geepawhill.contentment.core.Context;

public class EntranceTest
{
	private Context context;

	@Before
	public void before()
	{
		context = new Context();
	}

	@Test
	public void addsNewGroup()
	{
		Entrance entrance = new Entrance( );
		entrance.prepare(context);
		entrance.interpolate(context, 1);
		assertThat(context.canvas.getChildren().contains(entrance.get())).isTrue();
	}
	
	@Test
	public void isInstant()
	{
		Entrance entrance = new Entrance();
		entrance.prepare(context);
		assertThat(entrance.interpolate(context, .1)).isFalse();
	}
}

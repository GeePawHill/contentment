package org.geepawhill.contentment.fragments;
import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.junit.*;

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
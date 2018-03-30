package org.geepawhill.contentment.fragments;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

import static org.assertj.core.api.Assertions.*;

import org.geepawhill.contentment.core.Context;

public class ExitTest
{
	
	private Context context;
	private Group group;
	private GroupSource source;


	@Before
	public void before()
	{
		context = new Context();
		group = new Group();
		source = GroupSource.VALUE(group);
	}


	@Test
	public void removesGroup()
	{
		Exit exit = new Exit( source );
		exit.prepare(context);
		exit.interpolate(context, 1);
		assertThat(context.canvas.getChildren().contains(group)).isFalse();
	}
	
	@Test
	public void isInstant()
	{
		Exit exit = new Exit( source );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
	
	@Test
	public void missingGroup()
	{
		Exit exit = new Exit( source );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
	
	@Test(expected = GroupSource.NoGroupSource.class)
	public void badGroup()
	{
		Exit exit = new Exit( GroupSource.NONE );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
}

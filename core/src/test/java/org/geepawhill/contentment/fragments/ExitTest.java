package org.geepawhill.contentment.fragments;
import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.*;
import org.junit.*;

import javafx.scene.Group;

public class ExitTest
{
	private Context context;
	private Group group;

	@Before
	public void before()
	{
		context = new Context();
		group = new Group();
	}

	@Test
	public void removesGroup()
	{
		context.canvas.getChildren().add(group);
		Exit exit = new Exit( group );
		exit.prepare(context);
		exit.interpolate(context, 1);
		assertThat(context.canvas.getChildren().contains(group)).isFalse();
	}
	
	@Test
	public void isInstant()
	{
		context.canvas.getChildren().add(group);
		Exit exit = new Exit( group );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
	
	@Test(expected=NullPointerException.class)
	public void missingGroupThrows()
	{
		Exit exit = new Exit( group );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
}

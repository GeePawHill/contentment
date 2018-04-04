package org.geepawhill.contentment.fragments;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.text.Text;

import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.core.Context;

public class WipeTest
{
	private Context context;

	@Before
	public void before()
	{
		context = new Context();
	}
	
	@Test
	public void wipes()
	{
		Wipe wipe = new Wipe();
		context.canvas.getChildren().add(new Text());
		wipe.prepare(context);
		assertThat(wipe.interpolate(context, 1d)).isFalse();
		assertThat(context.canvas.getChildren().size()).isEqualTo(0);
	}
}

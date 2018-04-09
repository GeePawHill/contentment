package org.geepawhill.contentment.fragments;
import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.junit.*;

import javafx.scene.text.Text;

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

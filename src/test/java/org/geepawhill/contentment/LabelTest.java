package org.geepawhill.contentment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.step.JfxTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.scene.Node;
import javafx.scene.text.Text;

@RunWith(JfxTestRunner.class)
public class LabelTest
{
	private JfxTester jfxTester;

	@Before
	public void before()
	{
		jfxTester = new JfxTester();
	}

	@Ignore
	@Test
	public void failsOkay()
	{
		System.out.println("Test2 running.");
		fail("Let's see a test fail.");
	}

	@Test
	public void animationTest()
	{
		System.out.println("About to animate.");
		Context context = jfxTester.prepareWindow();
		Label label = new Label("Hi Mom!",800d,450d);
		Step step = label.sketch(1d);
		jfxTester.finish(context, step);
		Text text = (Text)jfxTester.snapshot(context).root.lookup("#label00");
		assertEquals("Hi Mom!", text.getText());
		System.out.println("Animation played.");
	}

}

package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.step.JfxTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxTestRunner.class)
public class LabelTest
{
	private JfxTester tester;
	private Label label;

	@Before
	public void before()
	{
		tester = new JfxTester();
		label = new Label("Hi Mom!",800d,450d);
	}

	@Test
	public void animationTest()
	{
		System.out.println("About to animate.");
		Context context = tester.prepareWindow();
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.finish(context, step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
	}
	
	@Test
	public void snapshot()
	{
		tester.properties(label, Snap.TEXT,Snap.BOUNDS);
	}

}

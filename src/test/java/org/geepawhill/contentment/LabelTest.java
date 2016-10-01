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
	private Context context;

	@Before
	public void before()
	{
		tester = new JfxTester();
		context = tester.prepareWindow();
		label = new Label("Hi Mom!",800d,450d);
	}

	@Test
	public void playChangesText()
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.finish(context, step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
	}
	
	@Test
	public void afterChangesText()
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		step.after(context);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
	}
	
	@Test
	public void beforeResetsText()
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.finish(context, step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
		step.before(context);
		tester.assertProperty(label,  Snap.TEXT, "");
	}
	
	@Test
	public void snapshot()
	{
		tester.properties(label, Snap.TEXT,Snap.BOUNDS);
	}

}

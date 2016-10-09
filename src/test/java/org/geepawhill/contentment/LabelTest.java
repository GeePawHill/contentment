package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.step.JfxTester;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LabelTest extends ApplicationTest
{
	private JfxTester tester;
	private Label label;
	private Context context;

	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
	}
	
	@Before
	public void before()
	{
		label = new Label("Hi Mom!",800d,450d);
	}

	@Test
	public void playChangesText() throws Exception
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.waitForPlay(step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
	}
	
	@Test
	public void beforeBeforesAfterPlay() throws Exception
	{
		tester.beforeBeforesAfterPlay(label, label.sketch(1d));
	}
	
	@Test
	public void beforeBeforesAfterAfter() throws Exception
	{
		tester.beforeBeforesAfterAfter(label, label.sketch(1d));
	}
	
	@Test
	public void afterEqualsPlay() throws Exception
	{
		tester.afterEqualsPlay(label, label.sketch(1d));
	}
	
//	@Test
//	public void afterChangesText()
//	{
//		tester.assertProperty(label, Snap.TEXT, "");
//		Step step = label.sketch(1d);
//		step.after(context);
//		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
//	}
//	
//	@Test
//	public void beforeResetsText()
//	{
//		tester.assertProperty(label, Snap.TEXT, "");
//		Step step = label.sketch(1d);
//		tester.finish(context, step);
//		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
//		step.before(context);
//		tester.assertProperty(label,  Snap.TEXT, "");
//	}
//	
//	@Test
//	public void snapshot()
//	{
//		tester.properties(label, Snap.TEXT,Snap.BOUNDS);
//	}
//

}

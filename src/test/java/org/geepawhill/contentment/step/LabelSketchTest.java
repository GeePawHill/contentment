package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LabelSketchTest extends ApplicationTest
{
	private JfxTester tester;
	private Label label;

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
		tester.beforeSameAsPlayBefore(label, label.sketch(1d));
	}
	
	@Test
	public void beforeBeforesAfterAfter() throws Exception
	{
		tester.beforeSameAsAfterBefore(label, label.sketch(1d));
	}
	
	@Test
	public void afterEqualsPlay() throws Exception
	{
		tester.afterSameAsPlay(label, label.sketch(1d));
	}
	
	@Test
	public void afterChangesText() throws Exception
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.waitForAfter(step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
	}
	
	@Test
	public void beforeResetsText() throws Exception
	{
		tester.assertProperty(label, Snap.TEXT, "");
		Step step = label.sketch(1d);
		tester.waitForAfter(step);
		tester.assertProperty(label, Snap.TEXT, "Hi Mom!");
		tester.waitForBefore(step);
		tester.assertProperty(label,  Snap.TEXT, "");
	}
	
	@Test
	public void snapshot()
	{
		tester.properties(label, Snap.TEXT,Snap.VISIBLE);
	}
}

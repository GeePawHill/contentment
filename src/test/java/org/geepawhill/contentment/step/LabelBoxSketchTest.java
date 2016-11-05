package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LabelBoxSketchTest extends ApplicationTest
{

	private JfxTester tester;
	private LabelBox label;

	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
	}
	
	@Before
	public void before()
	{
		label = new LabelBox("Hi Mom!",800d,450d);
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
	public void beforeSameAsPlayBefore() throws Exception
	{
		tester.beforeSameAsPlayBefore(label, label.sketch(1d));
	}
	
	@Test
	public void beforeSameAsAfterBefore() throws Exception
	{
		tester.beforeSameAsAfterBefore(label, label.sketch(1d));
	}
	
	@Test
	public void afterSameAsPlay() throws Exception
	{
		tester.afterSameAsPlay(label, label.sketch(1d));
	}

}

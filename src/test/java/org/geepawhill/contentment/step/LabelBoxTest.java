package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LabelBoxTest extends ApplicationTest
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

}

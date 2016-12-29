package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.test.JfxTester;
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
		Step step = label.sketch(1d);
		tester.waitForPlay(step);
	}
	
	@Test
	public void beforeSameAsPlayBefore() throws Exception
	{
		tester.beforeSameAsPlayBefore(label.sketch(1d));
	}
	
	@Test
	public void beforeSameAsAfterBefore() throws Exception
	{
		tester.beforeSameAsAfterBefore(label.sketch(1d));
	}
	
	@Test
	public void afterSameAsPlay() throws Exception
	{
		tester.afterSameAsPlay(label.sketch(1d));
	}
	
	@Test
	public void afterChangesText() throws Exception
	{
		Step step = label.sketch(1d);
		tester.waitForAfter(step);
	}
	
	@Test
	public void beforeResetsText() throws Exception
	{
		Step step = label.sketch(1d);
		tester.waitForAfter(step);
		tester.waitForBefore(step);
	}
	
	@Test
	public void snapshot()
	{
	}
}

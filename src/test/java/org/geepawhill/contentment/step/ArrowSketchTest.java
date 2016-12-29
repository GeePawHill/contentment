package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Spot;
import org.geepawhill.contentment.actor.arrow.Arrow;
import org.geepawhill.contentment.test.JfxTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

@Ignore
public class ArrowSketchTest extends ApplicationTest
{

	private JfxTester tester;
	private Arrow arrow;
	private Spot fromSpot;
	private Spot toSpot;

	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
	}
	
	@Before
	public void before() throws Exception
	{
		fromSpot = new Spot(100d,100d);
		tester.waitForAfter(fromSpot.place());
		toSpot = new Spot(200d,200d);
		tester.waitForAfter(toSpot.place());
		arrow = new Arrow(fromSpot,true,toSpot,true);
	}
	
	@Test
	public void beforeSameAsPlayBefore() throws Exception
	{
		tester.beforeSameAsPlayBefore(arrow.sketch(1d));
	}
	
	@Test
	public void beforeSameAsAfterBefore() throws Exception
	{
		tester.beforeSameAsAfterBefore(arrow.sketch(1d));
	}
	
	@Test
	public void afterSameAsPlay() throws Exception
	{
		tester.afterSameAsPlay(arrow.sketch(1d));
	}


}

package org.geepawhill.contentment.test;

import org.geepawhill.contentment.style.LineColor;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

@Ignore
public class JfxTesterTest extends ApplicationTest
{

	JfxTester tester;
	private TestStep playOnly;
	private TestStep afterOnly;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
		
		playOnly = new TestStep(null,null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
			onFinished.run();
		});
		
		
		afterOnly = new TestStep(null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
		},
		null);
	}
	
	@Test
	public void beforeDoesntUndoPlay()
	{
		tester.beforeSameAsPlayBefore(playOnly);
	}
	
	@Test
	public void afterDoesntMatchPlay()
	{
		tester.afterSameAsPlay(playOnly);
	}

	@Test
	public void beforeDoesntUndoAfter()
	{
		tester.beforeSameAsAfterBefore(afterOnly);
	}
}

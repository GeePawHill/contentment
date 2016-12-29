package org.geepawhill.contentment.test;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.StylePush;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


public class JfxTesterTest extends ApplicationTest
{

	JfxTester tester;
	private LambdaStep playOnly;
	private LambdaStep afterOnly;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
		
		playOnly = new LambdaStep(null,null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
			onFinished.run();
		});
		
		
		afterOnly = new LambdaStep(null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
		},
		null);
		
		
		
		sequence = new Sequence(new StylePush(),new StylePush());
	}

	@Ignore
	@Test
	public void beforeDoesntUndoPlay()
	{
		tester.beforeSameAsPlayBefore(playOnly);
	}
	
	@Ignore
	@Test
	public void afterDoesntMatchPlay()
	{
		tester.afterSameAsPlay(playOnly);
	}
	
	@Ignore
	@Test
	public void beforeDoesntUndoAfter()
	{
		tester.beforeSameAsAfterBefore(afterOnly);
	}
	
	@Test
	public void playSequence()
	{
		KvOutline play = tester.waitForPlay(sequence);
		assertNotNull(play.find("Styles.Map2"));
		assertNotNull(play.find("Styles.Map3"));
	}
	
	@Test
	public void afterSequence()
	{
		KvOutline after = tester.waitForAfter(sequence);
		assertNotNull(after.find("Styles.Map2"));
		assertNotNull(after.find("Styles.Map3"));
	}
	
	@Test
	public void beforeSequence()
	{
		tester.waitForPlay(sequence);
		KvOutline before = tester.waitForBefore(sequence);
		assertNull(before.find("Styles.Map2"));
		assertNull(before.find("Styles.Map3"));
	}
}

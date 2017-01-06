package org.geepawhill.contentment.test;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.styles.PopStyles;
import org.geepawhill.contentment.step.styles.PushStyles;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.style.LineColor;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


public class JfxTesterTest extends ApplicationTest
{

	JfxTester tester;
	private TestLambdaStep playOnly;
	private TestLambdaStep afterOnly;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
		
		playOnly = new TestLambdaStep(null,null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
			onFinished.run();
		});
		
		
		afterOnly = new TestLambdaStep(null,(context,onFinished) -> 
		{
			context.styles.set(LineColor.red());
		},
		null);
		
		
		
		sequence = new Sequence();
		sequence.add(new PushStyles());
		sequence.add(new SetStyle(LineColor.red()));
		sequence.add(new PopStyles());
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
		assertEquals(LineColor.red().toString(),play.find("Styles.LineColor").getValue());
	}
	
	@Test
	public void afterSequence()
	{
		KvOutline after = tester.waitForAfter(sequence);
		assertEquals(LineColor.red().toString(),after.find("Styles.LineColor").getValue());
	}
	
	@Test
	public void beforeSequence()
	{
		tester.waitForPlay(sequence);
		KvOutline before = tester.waitForBefore(sequence);
		assertEquals(LineColor.black().toString(),before.find("Styles.LineColor").getValue());
	}
}

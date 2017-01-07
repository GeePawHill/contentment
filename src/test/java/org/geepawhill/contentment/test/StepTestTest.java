package org.geepawhill.contentment.test;

import org.geepawhill.contentment.style.LineColor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class StepTestTest extends StepTest
{

	private TestLambdaStep playOnly;
	private TestLambdaStep afterOnly;
	
	@Before
	public void before()
	{
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

	}
	
	@Test
	public void beforeDoesntUndoPlay()
	{
		assertContractValid(playOnly);
	}
	
	@Test
	public void afterDoesntMatchPlay()
	{
		assertContractValid(playOnly);
	}
	
	@Test
	public void beforeDoesntUndoAfter()
	{
		assertContractValid(afterOnly);
	}

}

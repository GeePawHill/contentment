package org.geepawhill.contentment.test;

import org.geepawhill.contentment.style.ShapePen;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class StepTestTest extends SequenceTester
{

	private TestLambdaStep playOnly;
	private TestLambdaStep afterOnly;
	
	@Before
	public void before()
	{
		playOnly = new TestLambdaStep(null,null,(context,onFinished) -> 
		{
			context.styles.set(ShapePen.first());
			onFinished.run();
		});
		
		
		afterOnly = new TestLambdaStep(null,(context,onFinished) -> 
		{
			context.styles.set(ShapePen.first());
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

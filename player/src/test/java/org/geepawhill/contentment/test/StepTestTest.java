package org.geepawhill.contentment.test;

import org.junit.Ignore;

@Ignore
public class StepTestTest // extends SequenceTester
{

//	private TestLambdaStep playOnly;
//	private TestLambdaStep afterOnly;
//	
//	@Before
//	public void before()
//	{
//		playOnly = new TestLambdaStep(null,null,(context,onFinished) -> 
//		{
//			context.actors.add(new Stroke(new PointPair(0d,0d,100d,100d),new Format(Frames.unspecified())));
//			onFinished.run();
//		});
//		
//		
//		afterOnly = new TestLambdaStep(null,(context,onFinished) -> 
//		{
//			context.actors.add(new Stroke(new PointPair(0d,0d,100d,100d),new Format(Frames.unspecified())));
//		},
//		(context,onFinished) -> 
//		{
//			context.actors.add(new Stroke(new PointPair(0d,0d,100d,100d),new Format(Frames.unspecified())));
//			onFinished.run();
//		});
//
//	}
//	
//	@Test
//	public void beforeDoesntUndoPlay()
//	{
//		assertContractValid(playOnly);
//	}
//	
//	@Test
//	public void afterDoesntMatchPlay()
//	{
//		assertContractValid(playOnly);
//	}
//	
//	@Test
//	public void beforeDoesntUndoAfter()
//	{
//		assertContractValid(afterOnly);
//	}

}

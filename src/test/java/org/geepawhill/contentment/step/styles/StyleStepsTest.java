package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.test.SequenceTester;
import org.junit.Test;

public class StyleStepsTest extends SequenceTester
{

	@Test
	public void setStylesContract()
	{
		assertContractValid(new GetStyles());
	}
	
	@Test
	public void getStylesContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new GetStyles());
		sequence.add(new SetStyles());
		assertContractValid(sequence);
	}
	
	@Test
	public void setStyleContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SetStyle(LineColor.red()));
		assertContractValid(sequence);
	}
}

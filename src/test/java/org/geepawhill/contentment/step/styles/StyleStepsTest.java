package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.step.RestoreStylesStep;
import org.geepawhill.contentment.step.SaveStylesStep;
import org.geepawhill.contentment.step.SetStyleStep;
import org.geepawhill.contentment.style.ShapePen;
import org.geepawhill.contentment.test.SequenceTester;
import org.junit.Test;

public class StyleStepsTest extends SequenceTester
{

	@Test
	public void setStylesContract()
	{
		assertContractValid(new SaveStylesStep());
	}
	
	@Test
	public void getStylesContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SaveStylesStep());
		sequence.add(new RestoreStylesStep());
		assertContractValid(sequence);
	}
	
	@Test
	public void setStyleContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SetStyleStep(ShapePen.first()));
		assertContractValid(sequence);
	}
}

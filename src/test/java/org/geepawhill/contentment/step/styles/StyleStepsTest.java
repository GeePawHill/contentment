package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.test.JfxTest;
import org.junit.Test;

public class StyleStepsTest extends JfxTest
{

	@Test
	public void setStylesContract()
	{
		Sequence sequence = new Sequence(new InstantStep(new GetStyles()));
		tester.afterSameAsPlay(sequence);
		tester.beforeSameAsPlayBefore(sequence);
		tester.beforeSameAsAfterBefore(sequence);
	}
	
	@Test
	public void getStylesContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new GetStyles());
		sequence.add(new SetStyles());
		tester.afterSameAsPlay(sequence);
		tester.beforeSameAsPlayBefore(sequence);
		tester.beforeSameAsAfterBefore(sequence);
	}
	
	@Test
	public void setStyleContract()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SetStyle(LineColor.red()));
		tester.beforeSameAsPlayBefore(sequence);
		tester.afterSameAsPlay(sequence);
		tester.beforeSameAsAfterBefore(sequence);
	}
}

package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.EntranceStep;
import org.geepawhill.contentment.style.Frames;
import org.junit.Ignore;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


@Ignore
public class SequenceRunnerTest extends ApplicationTest
{

	private JavaFxRunner runner;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new JavaFxRunner();
		runner.prepareWindow(stage);
		
		sequence = new Sequence();
		sequence.add(
				new EntranceStep(
						new Stroke(
								new PointPair(0d,0d,100d,100d),
								new Format(Frames.unspecified()
								)
						)
				)
				);
	}
//	
//	@Test
//	public void playSequence()
//	{
//		ContextOutline play = runner.waitForPlay(sequence);
//		play.assertKey("Actors.Stroke_1");
//	}
//	
//	@Test
//	public void afterSequence()
//	{
//		ContextOutline after = runner.waitForAfter(sequence);
//		after.assertKey("Actors.Stroke_1");
//	}
//	
//	@Test
//	public void beforeSequence()
//	{
//		runner.waitForPlay(sequence);
//		ContextOutline before = runner.waitForBefore(sequence);
//		before.assertKeyAbsent("Actors.Stroke_1");
//	}
}

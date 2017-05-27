package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.style.Frames;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


public class SequenceRunnerTest extends ApplicationTest
{

	private SequenceRunner runner;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new SequenceRunner();
		runner.prepareWindow(stage);
		
		sequence = new Sequence();
		sequence.add(
				new Entrance(
						new Stroke(
								new PointPair(0d,0d,100d,100d),
								new Format(Frames.unspecified()
								)
						)
				)
				);
	}
	
	@Test
	public void playSequence()
	{
		ContextOutline play = runner.waitForPlay(sequence);
		play.assertKey("Actors.Stroke_1");
	}
	
	@Test
	public void afterSequence()
	{
		ContextOutline after = runner.waitForAfter(sequence);
		after.assertKey("Actors.Stroke_1");
	}
	
	@Test
	public void beforeSequence()
	{
		runner.waitForPlay(sequence);
		ContextOutline before = runner.waitForBefore(sequence);
		before.assertKeyAbsent("Actors.Stroke_1");
	}
}

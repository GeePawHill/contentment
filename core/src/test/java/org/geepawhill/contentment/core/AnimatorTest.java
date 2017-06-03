package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.geepawhill.contentment.test.JavaFxRunner;
import org.geepawhill.contentment.test.Repeat;
import org.geepawhill.contentment.test.RepeatRule;
import org.junit.Rule;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

@SuppressWarnings("unused")
public class AnimatorTest extends ApplicationTest
{
	
	private JavaFxRunner runner;
	
	ArrayList<Double> interpolatorCalls;
	
	@Rule
    public RepeatRule repeatRule = new RepeatRule();


	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new JavaFxRunner();
		runner.prepareWindow(stage);
		interpolatorCalls = new ArrayList<>();
	}
	
	@Test
	public void animatorFinishesWithZeroTime()
	{
		Animator animator = new Animator();
		runner.waitForPlay(animator, 0d, this::interpolator);
		assertThat(interpolatorCalls).containsOnlyOnce(1d);
	}
	
//	@Repeat(100)
	@Test
	public void animatorCallsInterpolatorOften()
	{
		Animator animator = new Animator();
		runner.waitForPlay(animator, 100d, this::interpolator);
		assertThat(interpolatorCalls).containsOnlyOnce(1d);
		assertThat(interpolatorCalls.size()).isGreaterThan(1);
	}

	
	public void interpolator(Context context, double fraction)
	{
		interpolatorCalls.add(fraction);
	}

}

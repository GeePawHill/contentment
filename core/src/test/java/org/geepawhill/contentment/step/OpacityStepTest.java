package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class OpacityStepTest extends JavaFxTest
{
	
	private TestActor actor;
	private Sequence sequence;
	private ArrayList<Double> opacities;

	@Before
	public void before()
	{
		actor = new TestActor();
		sequence = new Sequence(new EntranceStep(actor), new OpacityStep(1d,actor,0d));
		opacities = new ArrayList<>();
	}
	
	@Test
	public void slowFades()
	{
		runner.slow(sequence);
		assertThat(actor.group().getOpacity()).isCloseTo(0d,within(0.001d));
	}
	
	@Test
	public void fastFades()
	{
		runner.fast(sequence);
		assertThat(actor.group().getOpacity()).isCloseTo(0d,within(0.001d));
	}
	
	@Test
	public void undo()
	{
		runner.fast(sequence);
		runner.undo(sequence);
		assertThat(actor.group().getOpacity()).isCloseTo(1d,within(0.001d));
	}

	@Test
	public void interpolatorGoesDown()
	{
		actor.group.setOpacity(.5d);
		runner.context.setAfter(this::gatherOpacity);
		runner.slow(new OpacityStep(100d,actor,0d));
		double lastOpacity = .5d;
		for(Double opacity : opacities)
		{
			assertThat(opacity).isLessThanOrEqualTo(lastOpacity);
			lastOpacity = opacity;
		}
	}
	
	@Test
	public void interpolatorGoesUp()
	{
		actor.group.setOpacity(0d);
		runner.context.setAfter(this::gatherOpacity);
		runner.slow(new OpacityStep(50d,actor,1d));
		double lastOpacity = 0d;
		for(Double opacity : opacities)
		{
			assertThat(opacity).isGreaterThanOrEqualTo(lastOpacity);
			lastOpacity = opacity;
		}
	}

	
	public void gatherOpacity(Context context, double fraction)
	{
		opacities.add(actor.group.getOpacity());
	}
}

package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class ExitStepTest extends JavaFxTest
{

	private TestActor actor;
	private Sequence sequence;

	@Before
	public void before()
	{
		actor = new TestActor();
		sequence = new Sequence(new EntranceStep(actor), new ExitStep(actor));
	}

	@Test
	public void slowRemoves()
	{
		runner.slow(sequence);
		assertThat(runner.context.actors.contains(actor)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor.group);
	}

	@Test
	public void fastRemoves()
	{
		runner.fast(sequence);
		assertThat(runner.context.actors.contains(actor)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor.group);
	}

	@Test
	public void undoAdds()
	{
		runner.fast(sequence);
		runner.undo(sequence.last());
		assertThat(runner.context.actors.contains(actor)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor.group);
	}

}

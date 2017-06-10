package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class ClearStepTest extends JavaFxTest
{

	private TestActor actor1;
	private TestActor actor2;
	private Sequence sequence;

	@Before
	public void before()
	{
		actor1 = new TestActor();
		actor2 = new TestActor();
		sequence = new Sequence(new EntranceStep(actor1), new EntranceStep(actor2), new ClearStep());
	}

	@Test
	public void slowRemoves()
	{
		runner.slow(sequence);
		assertThat(runner.context.actors.contains(actor1)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor1.group);
	}

	@Test
	public void fastRemoves()
	{
		runner.fast(sequence);
		assertThat(runner.context.actors.contains(actor1)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor1.group);
	}

	@Test
	public void undoAdds()
	{
		runner.fast(sequence);
		runner.undo(sequence.last());
		assertThat(runner.context.actors.contains(actor1)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor1.group);
	}

}

package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class EntranceStepTest extends JavaFxTest
{

	private TestActor actor;
	private Sequence sequence;

	@Before
	public void before()
	{
		actor = new TestActor();
		sequence = new Sequence().add(new Entrance(actor));
	}

	@Test
	public void slowAdds()
	{
		runner.slow(sequence);
		assertThat(runner.context.actors.contains(actor)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor.group);
	}

	@Test
	public void fastAdds()
	{
		runner.fast(sequence);
		assertThat(runner.context.actors.contains(actor)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor.group);
	}

	@Test
	public void undoRemoves()
	{
		runner.fast(sequence);
		runner.undo(sequence);
		assertThat(runner.context.actors.contains(actor)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor.group);
	}

}

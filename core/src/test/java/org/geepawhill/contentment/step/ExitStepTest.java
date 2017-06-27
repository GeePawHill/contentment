package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.fast.Exit;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class ExitStepTest extends JavaFxTest
{

	private TestActor actor;
	private Phrase both;
	private Phrase entrance;
	private Phrase exit;

	@Before
	public void before()
	{
		actor = new TestActor();
		both = new Phrase().add(new Entrance(actor)).add(new Exit(actor));
		entrance = new Phrase().add(new Entrance(actor));
		exit = new Phrase().add(new Exit(actor));
	}

	@Test
	public void slowRemoves()
	{
		runner.slow(both);
		assertThat(runner.context.actors.contains(actor)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor.group);
	}

	@Test
	public void fastRemoves()
	{
		runner.fast(both);
		assertThat(runner.context.actors.contains(actor)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor.group);
	}

	@Test
	public void undoAdds()
	{
		runner.fast(entrance);
		runner.undo(exit);
		assertThat(runner.context.actors.contains(actor)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor.group);
	}

}

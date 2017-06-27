package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.fast.Clear;
import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

public class ClearStepTest extends JavaFxTest
{

	private TestActor actor1;
	private TestActor actor2;
	private Phrase both;
	private Phrase entrance;
	private Phrase exit;

	@Before
	public void before()
	{
		actor1 = new TestActor();
		actor2 = new TestActor();
		
		both = new Phrase().add(new Entrance(actor1)).add( new Entrance(actor2)).add(new Clear());
		entrance = new Phrase().add(new Entrance(actor1)).add(new Entrance(actor2));
		exit = new Phrase().add(new Clear());
	}

	@Test
	public void slowRemoves()
	{
		runner.slow(both);
		assertThat(runner.context.actors.contains(actor1)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor1.group);
	}

	@Test
	public void fastRemoves()
	{
		runner.fast(both);
		assertThat(runner.context.actors.contains(actor1)).isFalse();
		assertThat(runner.context.canvas.getChildren()).doesNotContain(actor1.group);
	}

	@Test
	public void undoAdds()
	{
		runner.fast(entrance);
		runner.fast(exit);
		runner.undo(exit);
		assertThat(runner.context.actors.contains(actor1)).isTrue();
		assertThat(runner.context.canvas.getChildren()).contains(actor1.group);
	}

}

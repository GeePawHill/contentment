package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class EntranceStepTest extends JavaFxTest
{
	
	private TestActor actor;
	private Sequence sequence;

	private static class TestActor implements Actor {
		
		private Group group;

		public TestActor()
		{
			group = new Group();
		}

		@Override
		public Group group()
		{
			return group;
		}

		@Override
		public String nickname()
		{
			return "TestActor";
		}

		@Override
		public Sequence draw(double ms)
		{
			return new Sequence();
		}
		
	}
	
	@Before
	public void before()
	{
		actor = new TestActor();
		sequence = new Sequence(new EntranceStep(actor));
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

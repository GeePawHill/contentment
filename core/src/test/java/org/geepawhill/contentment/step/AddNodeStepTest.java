package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.text.Text;

public class AddNodeStepTest extends JavaFxTest
{

	private TestActor actor;
	private Sequence sequence;
	private Text text;

	@Before
	public void before()
	{
		actor = new TestActor();
		text = new Text();
		sequence = new Sequence().add(new Entrance(actor)).add(new AddNode(actor, text));
	}

	@Test
	public void slowAdds()
	{
		runner.slow(sequence);
		assertThat(actor.group.getChildren()).contains(text);
	}

	@Test
	public void fastAdds()
	{
		runner.fast(sequence);
		assertThat(actor.group.getChildren()).contains(text);
	}

	@Test
	public void undoRemoves()
	{
		runner.fast(sequence);
		runner.undo(sequence);
		assertThat(actor.group.getChildren()).doesNotContain(text);
	}

}

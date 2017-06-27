package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestActor;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.text.Text;

public class AddNodeStepTest extends JavaFxTest
{

	private TestActor actor;
	private Phrase phrase;
	private Text text;

	@Before
	public void before()
	{
		actor = new TestActor();
		text = new Text();
		phrase = new Phrase().add(new Entrance(actor)).add(new AddNode(actor, text));
	}

	@Test
	public void slowAdds()
	{
		runner.slow(phrase);
		assertThat(actor.group.getChildren()).contains(text);
	}

	@Test
	public void fastAdds()
	{
		runner.fast(phrase);
		assertThat(actor.group.getChildren()).contains(text);
	}

	@Test
	public void undoRemoves()
	{
		runner.fast(phrase);
		runner.undo(phrase);
		assertThat(actor.group.getChildren()).doesNotContain(text);
	}

}

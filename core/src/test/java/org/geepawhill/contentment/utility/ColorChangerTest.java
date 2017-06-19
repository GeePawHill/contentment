package org.geepawhill.contentment.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ColorChangerTest extends JavaFxTest
{

	private Line line;
	private Text text;
	private ColorChanger changer;

	@Before
	public void before()
	{
		line = new Line(0d, 0d, 100d, 100d);
		line.setStroke(Color.RED);
		line.setFill(Color.RED);
		text = new Text();
		text.setStroke(Color.RED);
		text.setFill(Color.RED);

	}

	@Test
	public void changesColor()
	{
		changer = new ColorChanger(Color.BLUE);
		changer.accept(line);
		assertThat(line.getStroke()).isEqualTo(Color.BLUE);
		assertThat(line.getFill()).isEqualTo(Color.RED);
		assertThat(changer.result).isEqualTo(Color.RED);
	}

	@Test
	public void changesFillOnText()
	{
		changer = new ColorChanger(Color.BLUE);
		changer.accept(text);
		assertThat(text.getStroke()).isEqualTo(Color.BLUE);
		assertThat(text.getFill()).isEqualTo(Color.BLUE);
		assertThat(changer.result).isEqualTo(Color.RED);
	}

}

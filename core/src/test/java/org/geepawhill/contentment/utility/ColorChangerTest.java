package org.geepawhill.contentment.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ColorChangerTest extends ContentmentTest
{
	@Test
	public void changesStrokeOnLine()
	{
		Line line = new Line(0d, 0d, 100d, 100d);
		line.setStroke(Color.RED);
		line.setFill(Color.RED);
		new ColorChanger(Color.BLUE).accept(line);
		assertThat(line.getStroke()).isEqualTo(Color.BLUE);
		assertThat(line.getFill()).isEqualTo(Color.RED);
		assertThat(new ColorChanger(Color.BLUE).result).isEqualTo(Color.RED);
	}

	@Test
	public void alsoChangesFillOnText()
	{
		Text text = new Text();
		text.setStroke(Color.RED);
		text.setFill(Color.RED);
		new ColorChanger(Color.BLUE).accept(text);
		assertThat(text.getStroke()).isEqualTo(Color.BLUE);
		assertThat(text.getFill()).isEqualTo(Color.BLUE);
		assertThat(new ColorChanger(Color.BLUE).result).isEqualTo(Color.RED);
	}

}

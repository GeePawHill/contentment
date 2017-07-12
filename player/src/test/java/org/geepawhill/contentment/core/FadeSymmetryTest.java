package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.Centered;
import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class FadeSymmetryTest extends JavaFxTest
{

	private ScriptBuilder builder;
	private Letters actor;
	private Format majorFormat;

	@Before
	public void before()
	{
		builder = new ScriptBuilder();
		Paint majorColor = Color.RED;
		Font majorHand = Font.font("Chewed Pen BB", FontPosture.ITALIC, 90d);
		majorFormat = new Format(TypeFace.font(majorHand, 3d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
		actor = new Letters("Hi Mom", new Centered(400d, 400d), majorFormat);
	}

	@Test
	public void test()
	{
		builder.buildPhrase();
		builder.appear(actor);
		Addable appear = builder.endBuild();
		
		builder.buildPhrase();
		builder.fadeDown(1d, actor);
		Addable fadeOut = builder.endBuild();
		
		builder.buildPhrase();
		builder.fadeUp(1d, actor);
		Addable fadeIn = builder.endBuild();
		
		runner.fast(appear);
		runner.fast(fadeOut);
		runner.fast(fadeIn);
		assertThat(actor.group().getChildren().get(0).getBoundsInLocal().getWidth()).isGreaterThan(100d);
		
		runner.undo(fadeIn);
		runner.undo(fadeOut);
		assertThat(actor.group().getChildren().get(0).getBoundsInLocal().getWidth()).isGreaterThan(100d);
		
	}

}

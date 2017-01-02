package org.geepawhill.contentment.step;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.step.styles.PopStyles;
import org.geepawhill.contentment.step.styles.PushStyles;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.StyleId;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class StylePopTest
{

	@Test
	public void popUndoes()
	{
		Context context = new Context(new Group());
		Style redLine = LineColor.lineColor("RED", Color.RED);
		context.styles.set(redLine);
		PushStyles push = new PushStyles();
		push.after(context);
		Style blackLine = LineColor.lineColor("BLACK", Color.BLACK);
		context.styles.set(blackLine);
		PopStyles pop = new PopStyles();
		pop.after(context);
		assertEquals(redLine,context.styles.get(StyleId.LineColor));
		pop.before(context);
		assertEquals(blackLine,context.styles.get(StyleId.LineColor));
	}

}

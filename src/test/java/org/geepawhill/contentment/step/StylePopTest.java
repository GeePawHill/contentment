package org.geepawhill.contentment.step;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleId;
import org.junit.Test;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StylePopTest
{

	@Test
	public void popUndoes()
	{
		Context context = new Context(new Pane());
		Style redLine = Style.lineColor(Color.RED);
		context.styles.set(redLine);
		StylePush push = new StylePush();
		push.after(context);
		Style blackLine = Style.lineColor(Color.BLACK);
		context.styles.set(blackLine);
		StylePop pop = new StylePop();
		pop.after(context);
		assertEquals(redLine,context.styles.get(StyleId.LineColor));
		pop.before(context);
		assertEquals(blackLine,context.styles.get(StyleId.LineColor));
	}

}

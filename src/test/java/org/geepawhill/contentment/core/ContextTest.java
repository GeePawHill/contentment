package org.geepawhill.contentment.core;

import static org.junit.Assert.assertFalse;

import org.geepawhill.contentment.outline.KvMatcher;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.style.LineColor;
import org.junit.Test;

import javafx.scene.Group;

public class ContextTest
{

	@Test
	public void detectsStyleCorruption()
	{
		Context context = new Context(new Group());
		KvOutline before = context.outline();
		SetStyle style = new SetStyle(LineColor.red());
		style.after(context);
		KvOutline after = context.outline();
		KvMatcher comparator = new KvMatcher();
		assertFalse(comparator.match(before, after).match);
	}

}

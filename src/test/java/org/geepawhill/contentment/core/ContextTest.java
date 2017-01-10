package org.geepawhill.contentment.core;

import org.geepawhill.contentment.test.ContextOutline;
import org.junit.Test;

import javafx.scene.Group;

public class ContextTest
{

	@Test
	public void requiredStyles()
	{
		Context context = new Context(new Group());
		ContextOutline outline = new ContextOutline(context.outline());
		outline.baseKey("Styles");
		outline.assertBase(StyleId.ShapePen.name());
	}

}

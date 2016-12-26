package org.geepawhill.contentment.core;

import static org.junit.Assert.assertFalse;

import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.StyleStep;
import org.geepawhill.contentment.tree.Dump;
import org.geepawhill.contentment.tree.KeyValueTreeComparator;
import org.geepawhill.contentment.tree.KeyValueTreeMessage;
import org.geepawhill.contentment.tree.TypedTree;
import org.junit.Test;

import javafx.scene.Group;

public class ContextTest
{

	@Test
	public void detectsStyleCorruption()
	{
		Dump before = new Dump();
		Context context = new Context(new Group());
		context.dump(before);
		StyleStep style = new StyleStep(LineColor.red());
		style.after(context);
		Dump after = new Dump();
		context.dump(after );
		KeyValueTreeComparator comparator = new KeyValueTreeComparator();
		TypedTree<KeyValueTreeMessage> details = new TypedTree<>();
		assertFalse(comparator.match(before, after, details));
	}

}

package org.geepawhill.contentment.core;

import static org.junit.Assert.*;

import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.StyleStep;
import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.KeyValueTreeComparator;
import org.geepawhill.contentment.tree.TreeOutput;
import org.junit.Test;

import javafx.scene.Group;

public class ContextTest
{

	@Test
	public void detectsStyleCorruption()
	{
		TreeOutput<KeyValue> before = new TreeOutput<>();
		Context context = new Context(new Group());
		context.dump(before);
		StyleStep style = new StyleStep(LineColor.red());
		style.after(context);
		TreeOutput<KeyValue> after = new TreeOutput<>();
		context.dump(after );
		KeyValueTreeComparator comparator = new KeyValueTreeComparator();
		TreeOutput<String> details = new TreeOutput<>();
		System.out.println(before.asText("Before"));
		System.out.println(after.asText("After"));
		assertFalse(comparator.match(before, after, details,true));
		System.out.println(details.asText("Context"));
	}

}

package org.geepawhill.contentment.test;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.PenWidth;
import org.geepawhill.contentment.style.StyleStep;
import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.KeyValueTreeComparator;
import org.geepawhill.contentment.tree.KeyValueTreeMessage;
import org.geepawhill.contentment.tree.TreeOutput;
import org.junit.Test;

import javafx.scene.Group;

public class JfxTesterTest
{

	@Test
	public void junitRunTimePopup()
	{
		TreeOutput<KeyValue> before = new TreeOutput<>();
		before.append(new KeyValue("CorrectInBoth","Value"));
		before.append(new KeyValue("MissingInActual","Value"));
		before.append(new KeyValue("DifferentValue","Value"));
		
		TreeOutput<KeyValue> after = new TreeOutput<>();
		after.append(new KeyValue("CorrectInBoth","Value"));
		after.append(new KeyValue("DifferentValue","Whoops!"));
		after.append(new KeyValue("MissingInExpected","Value"));

		JfxTester tester = new JfxTester();
		TreeOutput<KeyValueTreeMessage> details = new TreeOutput<>();
		tester.compareSnaps(before,after,details);
		System.out.println(details.asText("Details"));
		assertFalse(tester.compareSnapsVisual(before,after));
	}

}

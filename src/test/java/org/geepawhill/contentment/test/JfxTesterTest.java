package org.geepawhill.contentment.test;

import static org.junit.Assert.assertFalse;

import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.KeyValueTreeMessage;
import org.geepawhill.contentment.tree.TreeOutput;
import org.junit.Ignore;
import org.junit.Test;


public class JfxTesterTest
{

	@Ignore
	@Test
	public void junitRunTimePopup()
	{
		TreeOutput<KeyValue> before = new TreeOutput<>();
		before.append(new KeyValue("Parent"));
		before.indent();
		before.append(new KeyValue("CorrectInBoth","Value"));
		before.append(new KeyValue("MissingInActual","Value"));
		before.append(new KeyValue("DifferentValue","Value"));
		
		TreeOutput<KeyValue> after = new TreeOutput<>();
		after.append(new KeyValue("Parent"));
		after.indent();
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

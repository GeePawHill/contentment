package org.geepawhill.contentment.test;

import static org.junit.Assert.assertFalse;

import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.junit.Ignore;
import org.junit.Test;


public class JfxTesterTest
{

	@Ignore
	@Test
	public void junitRunTimePopup()
	{
		KvOutline before = new KvOutline();
		before.append(new KeyValue("Parent"));
		before.indent();
		before.append(new KeyValue("CorrectInBoth","Value"));
		before.append(new KeyValue("MissingInActual","Value"));
		before.append(new KeyValue("DifferentValue","Value"));
		
		KvOutline after = new KvOutline();
		after.append(new KeyValue("Parent"));
		after.indent();
		after.append(new KeyValue("CorrectInBoth","Value"));
		after.append(new KeyValue("DifferentValue","Whoops!"));
		after.append(new KeyValue("MissingInExpected","Value"));

		JfxTester tester = new JfxTester();
		assertFalse(tester.compareSnapsVisual(before,after));
	}

}

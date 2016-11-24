package org.geepawhill.contentment.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KeyValueTreeComparatorTest
{

	private KeyValueTreeComparator comparator;
	private TreeOutput<KeyValue> actual;
	private TreeOutput<KeyValue> expected;
	
	@Before
	public void before()
	{
		comparator = new KeyValueTreeComparator();
		actual = new TreeOutput<>();
		expected = new TreeOutput<>();
	}

	@Test
	public void emptiesMatch()
	{
		assertTrue(comparator.match(expected,actual, new TreeOutput<KeyValueTreeMessage>()));
	}
	
	@Test
	public void samesMatch()
	{
		appendExpected("Item 1");
		appendActual("Item 1");
		assertTrue(comparator.match(expected,actual, new TreeOutput<KeyValueTreeMessage>()));
	}
	
	@Test
	public void missingActualFail()
	{
		appendExpected("Item 1");
		TreeOutput<KeyValueTreeMessage> details = new TreeOutput<>();
		assertFalse(comparator.match(expected,actual, details));
		assertEquals(2,details.asList().size());
	}
	
	@Test
	public void extraActualFail()
	{
		appendActual("Item 1");
		TreeOutput<KeyValueTreeMessage> details = new TreeOutput<>();
		assertFalse(comparator.match(expected,actual, details));
		assertEquals(2,details.asList().size());
	}

	@Test
	public void wrongValueFail()
	{
		appendExpected("Item 1","key");
		appendActual("Item 1","whoops");
		TreeOutput<KeyValueTreeMessage> details = new TreeOutput<KeyValueTreeMessage>();
		assertFalse(comparator.match(expected,actual, details));
		assertEquals(2,details.asList().size());
	}
	
	private void appendExpected(String key,String value)
	{
		expected.append(new KeyValue(key,value));
	}
	
	private void appendExpected(String key)
	{
		appendExpected(key,"");
		expected.indent();
	}

	private void appendActual(String key,String value)
	{
		actual.append(new KeyValue(key,value));
	}
	
	private void appendActual(String key)
	{
		appendActual(key,"");
		actual.indent();
	}

}

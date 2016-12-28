package org.geepawhill.contentment.outline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.geepawhill.contentment.outline.BasicOutline;
import org.geepawhill.contentment.outline.KeyValue;
import org.junit.Before;
import org.junit.Test;

public class KvMatcherTest
{

	private KvMatcher matcher;
	private KvOutline actual;
	private KvOutline expected;
	
	@Before
	public void before()
	{
		matcher = new KvMatcher();
		actual = new KvOutline();
		expected = new KvOutline();
	}

	@Test
	public void emptiesMatch()
	{
		assertTrue(matcher.match(expected,actual, new BasicOutline<KvDifference>()));
	}
	
	@Test
	public void samesMatch()
	{
		appendExpected("Item 1");
		appendActual("Item 1");
		assertTrue(matcher.match(expected,actual, new BasicOutline<KvDifference>()));
	}
	
	@Test
	public void missingActualFail()
	{
		appendExpected("Item 1");
		BasicOutline<KvDifference> details = new BasicOutline<>();
		assertFalse(matcher.match(expected,actual, details));
		assertEquals(2,details.asList().size());
	}
	
	@Test
	public void extraActualFail()
	{
		appendActual("Item 1");
		BasicOutline<KvDifference> details = new BasicOutline<>();
		assertFalse(matcher.match(expected,actual, details));
		assertEquals(2,details.asList().size());
	}

	@Test
	public void wrongValueFail()
	{
		appendExpected("Item 1","key");
		appendActual("Item 1","whoops");
		BasicOutline<KvDifference> details = new BasicOutline<KvDifference>();
		assertFalse(matcher.match(expected,actual, details));
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

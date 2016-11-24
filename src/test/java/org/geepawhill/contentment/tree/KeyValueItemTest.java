package org.geepawhill.contentment.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KeyValueItemTest
{

	@Test
	public void itemConstructors()
	{
		KeyValueItem pair = new KeyValueItem("key", "value");
		assertEquals("key", pair.key());
		assertEquals("value", pair.value());

		KeyValueItem noValue = new KeyValueItem("key");
		assertEquals("key", noValue.key());
		assertEquals("", noValue.value());
	}

	@Test
	public void fullKeys()
	{
		KeyValueItem parent = new KeyValueItem("parent");
		KeyValueItem child = new KeyValueItem("child", "child");
		parent.getChildren().add(child);
		assertEquals("parent", parent.fullKey());
		assertEquals("parent.child", child.fullKey());
	}

	@Test
	public void compareBothMatches()
	{
		KeyValueItem left = new KeyValueItem("key", "value");
		KeyValueItem right = new KeyValueItem("key", "value");
		assertTrue(left.keysMatch(right));
		assertTrue(left.valuesMatch(right));
	}
	
	@Test
	public void keysMismatch()
	{
		KeyValueItem left = new KeyValueItem("key", "value");
		KeyValueItem right = new KeyValueItem("whoops", "value");
		assertFalse(left.keysMatch(right));
	}
	
	@Test
	public void valuesMismatch()
	{
		KeyValueItem left = new KeyValueItem("key", "value");
		KeyValueItem right = new KeyValueItem("key", "whoops");
		assertFalse(left.valuesMatch(right));
	}
}

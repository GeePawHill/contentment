package org.geepawhill.contentment.outline;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.geepawhill.contentment.outline.ValueTree.ValueItemConsumer;
import org.junit.Before;
import org.junit.Test;

public class ValueTreeTest
{

	private ValueTree tree;
	private ArrayList<ValueItem> items;
	private ValueItemConsumer catcher;
	
	private final String[] NO_KEYS = new String[] {};

	@Before
	public void before()
	{
		tree = new ValueTree();
		items = new ArrayList<>();
		catcher = new ValueItemConsumer()
		{

			@Override
			public void accept(ValueItem t)
			{
				items.add(t);
			}
		};
	}

	@Test
	public void empty()
	{
		tree.produce(catcher);
		assertThat(items.size()).isEqualTo(0);
	}

	@Test
	public void oneRootItem()
	{
		tree.add("root");
		tree.produce(catcher);
		assertItem(0,NO_KEYS,"root");
	}
	
	@Test
	public void twoRootItems()
	{
		tree.add("root0");
		tree.add("root1");
		tree.produce(catcher);
		assertItem(0,NO_KEYS,"root0");
		assertItem(1,NO_KEYS,"root1");
	}
	
	@Test
	public void indent()
	{
		tree.add("root");
		tree.indent();
		tree.add("child");
		tree.produce(catcher);
		assertItem(0,NO_KEYS,"root");
		assertItem(1,keys("root") , "child");
	}
	
	@Test
	public void dedent()
	{
		tree.add("root0");
		tree.indent();
		tree.add("child");
		tree.dedent();
		tree.add("root1");
		tree.produce(catcher);
		assertItem(0,NO_KEYS,"root0");
		assertItem(1,keys("root0") , "child");
		assertItem(2,NO_KEYS,"root1");
		tree.printFormatted();
	}
	
	public void assertItem(int index,String[] keys, String value)
	{
		assertThat(items.size()).isGreaterThan(index);
		ValueItem item = items.get(index);
		assertThat(item.keys).isEqualTo(keys);
		assertThat(item.value).isEqualTo(value);
	}
	
	private String[] keys(String...keys)
	{
		return keys;
	}
}

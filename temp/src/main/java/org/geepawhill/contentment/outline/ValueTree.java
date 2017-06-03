package org.geepawhill.contentment.outline;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class ValueTree
{
	ArrayList<ValueItem> items;
	int indent;

	ArrayList<String> keysNow;

	@FunctionalInterface
	public static interface ValueItemConsumer extends Consumer<ValueItem>
	{
	}

	public ValueTree()
	{
		this.items = new ArrayList<>();
		this.keysNow = new ArrayList<>();
	}
	
	public int size()
	{
		return items.size();
	}
	
	public ValueItem get(int index)
	{
		return items.get(index);
	}


	public void add(String item)
	{
		items.add(new ValueItem(keysNow.toArray(new String[] {}), item));
	}
	
	public void add(String item, String value)
	{
		add(item);
		indent();
		add(value);
		dedent();
	}

	public void indent()
	{
		keysNow.add(items.get(items.size() - 1).value);
	}

	public void dedent()
	{
		keysNow.remove(keysNow.size() - 1);
	}

	public void produce(ValueItemConsumer consumer)
	{
		for (ValueItem item : items)
		{
			consumer.accept(item);
		}
	}
	
	public void printFormatted(PrintStream output)
	{
		ValueItemConsumer printer = new ValueItemConsumer() {

			@Override
			public void accept(ValueItem t)
			{
				String keys = String.join("", Collections.nCopies(t.keys.length, "\t"));
				output.println(keys+t.value);
			}};
		produce(printer);
	}
	
	public void printFormatted()
	{
		printFormatted(System.out);
	}
	
	public void printFull(PrintStream output)
	{
		ValueItemConsumer printer = new ValueItemConsumer() {

			@Override
			public void accept(ValueItem t)
			{
				String keys = String.join(".", t.keys);
				if(keys.length()>0) keys+=".";
				output.println(keys+t.value);
			}};
		produce(printer);
	}

	public void printFull()
	{
		printFull(System.out);
	}
}

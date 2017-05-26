package org.geepawhill.contentment.outline;

import java.util.ArrayList;

public class ValueTreeMatcher
{

	private ValueItemComparator comparator;

	public ValueTreeMatch match(ValueTree left, ValueTree right)
	{
		ArrayList<ValueItem> rightItems = new ArrayList<>(right.items); 
		ValueTreeMatch result = new ValueTreeMatch();
		comparator = new ValueItemComparator();
		for(ValueItem leftItem : left.items)
		{
			ValueItem rightItem = findItem(leftItem,rightItems);
			if(rightItem!=null)
			{
				result.both.add(leftItem);
				rightItems.remove(rightItem);
			}
			else
			{
				result.left.add(leftItem);
			}
		}
		result.right.addAll(rightItems);
		return result;
	}

	private ValueItem findItem(ValueItem item, ArrayList<ValueItem> list)
	{
		for(ValueItem candidate : list)
		{
			if(comparator.compare(item,candidate)==0) return candidate;
		}
		return null;
	}

}

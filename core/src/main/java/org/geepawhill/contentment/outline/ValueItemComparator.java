package org.geepawhill.contentment.outline;

import java.util.Comparator;

public class ValueItemComparator implements Comparator<ValueItem>
{

	@Override
	public int compare(ValueItem o1, ValueItem o2)
	{
		int result = o1.keys.length - o2.keys.length;
		if(result!=0) return result;
		for(int k =0; k<o1.keys.length; k++)
		{
			result = o1.keys[k].compareTo(o2.keys[k]);
			if(result!=0) return result;
		}
		return o1.value.compareTo(o2.value);
	}
	
}
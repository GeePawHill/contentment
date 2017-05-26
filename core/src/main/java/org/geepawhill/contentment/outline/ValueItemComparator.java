package org.geepawhill.contentment.outline;

import java.util.Comparator;

public class ValueItemComparator 
{

	public int compare(ValueItem o1, ValueItem o2)
	{
		int result = compareKeys(o1,o2);
		return result==0 ? o1.value.compareTo(o2.value) : result;
	}
	
	public int compareKeys(ValueItem o1, ValueItem o2)
	{
		int result = o1.keys.length - o2.keys.length;
		if(result!=0) return result;
		for(int k =0; k<o1.keys.length; k++)
		{
			result = o1.keys[k].compareTo(o2.keys[k]);
			if(result!=0) return result;
		}
		return 0;
	}
	
}
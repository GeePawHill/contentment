package org.geepawhill.contentment.outline;

public class ValueItem
{
	public final String[] keys;
	public final String value;
	
	static public final String[] NO_KEYS = new String[] {};
	
	public ValueItem(String[] keys, String value)
	{
		this.keys = keys;
		this.value = value;
	}
}

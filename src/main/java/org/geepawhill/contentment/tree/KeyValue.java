package org.geepawhill.contentment.tree;

public class KeyValue
{

	private String key;
	private String value;

	public KeyValue(String key, String value)
	{
		this.setKey(key);
		this.setValue(value);
	}
	
	public KeyValue(String key)
	{
		this(key,"");
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		String result = key;
		if(value!=null && !value.isEmpty()) result+=" = "+value;
		return result;
	}

}

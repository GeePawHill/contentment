package org.geepawhill.contentment.outline;

public class KvDifference
{
	private String key;
	private String actual;
	private String expected;

	public KvDifference(String key,String expected,String actual)
	{
		this.setKey(key);
		this.setActual(actual);
		this.setExpected(expected);
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getActual()
	{
		return actual;
	}

	public void setActual(String actual)
	{
		this.actual = actual;
	}

	public String getExpected()
	{
		return expected;
	}

	public void setExpected(String expected)
	{
		this.expected = expected;
	}
	
	public String toString()
	{
		String result = getKey();
		if(!"".equals(getExpected())) result+=" Expected:"+getExpected();
		if(!"".equals(getActual())) result+= " Actual:" +getActual();
		return result;
	}
}
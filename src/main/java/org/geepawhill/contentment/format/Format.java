package org.geepawhill.contentment.format;

import java.util.HashMap;

public class Format
{
	private final HashMap<String,IStyle> overrides;
	public final String name;
	public final Format base;
	
	public Format(String name)
	{
		this(name,null);
	}

	public Format(String name, Format base)
	{
		this.name = name;
		this.base = base;
		overrides = new HashMap<>();
	}

	public IStyle findStyle(String styleName)
	{
		Format candidate = this;
		while(candidate!=null)
		{
			IStyle result = candidate.overrides.get(styleName);
			if(result!=null) return result;
			candidate = candidate.base;
		}
		return null;
	}

	public void override(String styleName, IStyle style)
	{
		overrides.put(styleName, style);
	}

	public IStyle style(String styleName)
	{
		IStyle result = findStyle(styleName);
		if(result==null) throw new RuntimeException("Attempt to get non-existent style: "+styleName);
		return result;
	}

}

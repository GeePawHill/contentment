package org.geepawhill.contentment.format;

import java.util.HashMap;

public class Format
{
	private final HashMap<String,Style> overrides;
	public final String nickname;
	public final Format base;
	
	public Format(String name)
	{
		this(name,null);
	}

	public Format(String name, Format base)
	{
		this.nickname = name;
		this.base = base;
		this.overrides = new HashMap<>();
	}

	public Style find(String key)
	{
		Format candidate = this;
		while(candidate!=null)
		{
			Style result = candidate.overrides.get(key);
			if(result!=null) return result;
			candidate = candidate.base;
		}
		return null;
	}

	public void override(Style style)
	{
		overrides.put(style.key(), style);
	}

	public Style require(String key)
	{
		Style result = find(key);
		if(result==null) throw new MissingFormatException(key,nickname);
		return result;
	}

}

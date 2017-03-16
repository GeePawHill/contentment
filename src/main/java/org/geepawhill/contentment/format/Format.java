package org.geepawhill.contentment.format;

import java.util.HashMap;

import javafx.scene.shape.Shape;

public class Format
{
	private final HashMap<String,Style> overrides;
	public final String nickname;
	public final Format base;
	
	public Format(String name,Style... styles)
	{
		this(name,null,styles);
	}

	public Format(String name, Format base, Style... styles)
	{
		this.nickname = name;
		this.base = base;
		this.overrides = new HashMap<>();
		for(Style style : styles) put(style);
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

	public void put(Style style)
	{
		overrides.put(style.key(), style);
	}

	public Style require(String key)
	{
		Style result = find(key);
		if(result!=null) return result;
		throw new MissingFormatException(key,nickname);
	}

	public void apply(String key, Shape	shape)
	{
		require(key).apply(shape);
	}

}

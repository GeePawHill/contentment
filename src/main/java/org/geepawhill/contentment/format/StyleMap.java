package org.geepawhill.contentment.format;

import java.util.HashMap;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

public class StyleMap implements Outliner
{
	private HashMap<String,Style> map;
	
	public StyleMap()
	{
		map = new HashMap<>();
	}
	
	public Style get(String kind)
	{
		return map.get(kind);
	}
	
	public Style set(Style style)
	{
		Style result = get(style.key());
		map.put(style.key(), style);
		return result;
	}

	@Override
	public void outline(KvOutline output)
	{
		output.indent();
		for (Style value : map.values())
		{
			value.outline(output);
		}
		output.dedent();
	}

	public StyleMap deepCopy()
	{
		StyleMap result = new StyleMap();
		result.map.putAll(map);
		return result;
	}
}

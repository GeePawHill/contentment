package org.geepawhill.contentment.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Styles
{
	
	List<HashMap<StyleId,Style>> old;
	
	public Styles()
	{
		old = new ArrayList<>();
		push();
	}

	public void set(Style style)
	{
		old.get(0).put(style.id,style);
	}

	public Style get(StyleId id)
	{
		for(HashMap<StyleId,Style> map : old)
		{
			if(map.containsKey(id)) return map.get(id);
		}
		throw new RuntimeException("Asked for unset style: "+id.toString());
	}

	public void push()
	{
		HashMap<StyleId, Style> hashMap = new HashMap<StyleId,Style>();
		push(hashMap);
	}

	public void push(HashMap<StyleId, Style> map)
	{
		old.add(0,map);
	}

	public HashMap<StyleId, Style> pop()
	{
		if(old.isEmpty()) throw new RuntimeException("Too many style pops.");
		HashMap<StyleId,Style> result = old.remove(0);
		return result;
	}

}

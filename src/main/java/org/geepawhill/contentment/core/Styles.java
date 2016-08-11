package org.geepawhill.contentment.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Styles
{
	
	List<HashMap<StyleId,Style>> old;
	
	public Styles()
	{
		old = new LinkedList<>();
		push();
	}

	public void set(Style style)
	{
		old.get(0).put(style.id(),style);
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
		old.add(0,new HashMap<StyleId,Style>());
	}

	public void pop()
	{
		if(old.isEmpty()) throw new RuntimeException("Too many style pops.");
		old.remove(0);
	}

}

package org.geepawhill.contentment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Styles
{
	
	List<HashMap<Style,Object>> old;
	
	public Styles()
	{
		old = new LinkedList<>();
		push();
	}

	public void set(Style style, Object value)
	{
		old.get(0).put(style, value);
	}

	public Object get(Style style)
	{
		for(HashMap<Style,Object> map : old)
		{
			if(map.containsKey(style)) return map.get(style);
		}
		throw new RuntimeException("Asked for unset style: "+style.toString());
	}

	public void push()
	{
		old.add(0,new HashMap<Style,Object>());
	}

	public void pop()
	{
		if(old.isEmpty()) throw new RuntimeException("Too many style pops.");
		old.remove(0);
	}

}

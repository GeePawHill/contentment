package org.geepawhill.contentment.step;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Bounds;

public class Snapshot
{
	
	private final HashMap<String,Object> properties;
	
	public Snapshot()
	{
		this.properties = new HashMap<>();
	}

	public void put(String property, Object object)
	{
		if(properties.containsKey(property)) throw new RuntimeException("Attempt to add duplicate property: "+property);
		properties.put(property, object);
	}

	public Object get(String property)
	{
		return properties.get(property);
	}

	public void add(Snapshot addition)
	{
		for( Map.Entry<String,Object> entry : addition.properties.entrySet())
		{
			put(entry.getKey(),entry.getValue());
		}
		
	}

	public void add(String prefix, Snapshot addition)
	{
		for( Map.Entry<String,Object> entry : addition.properties.entrySet())
		{
			put(prefix+"."+entry.getKey(),entry.getValue());
		}
	}

	public String asString(String property)
	{
		return (String)get(property);
	}

	public double asDouble(String property)
	{
		return (Double)get(property);
	}
	
	public Bounds asBounds(String property)
	{
		return (Bounds)get(property);
	}
	
}

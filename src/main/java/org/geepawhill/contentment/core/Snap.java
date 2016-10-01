package org.geepawhill.contentment.core;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Bounds;

public class Snap
{
	
	public final static String TEXT="Text";
	public final static String BOUNDS="Bounds";
	
	private final HashMap<String,Object> properties;
	
	public Snap()
	{
		this.properties = new HashMap<>();
	}

	public void add(String property, Object object)
	{
		if(properties.containsKey(property)) throw new RuntimeException("Attempt to add duplicate property: "+property);
		properties.put(property, object);
	}

	public Object get(String property)
	{
		return properties.get(property);
	}

	public void add(Snap addition)
	{
		for( Map.Entry<String,Object> entry : addition.properties.entrySet())
		{
			add(entry.getKey(),entry.getValue());
		}
		
	}

	public void add(String prefix, Snap addition)
	{
		for( Map.Entry<String,Object> entry : addition.properties.entrySet())
		{
			add(prefix+"."+entry.getKey(),entry.getValue());
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

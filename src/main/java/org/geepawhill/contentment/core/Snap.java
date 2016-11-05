package org.geepawhill.contentment.core;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class Snap
{
	
	public final static String TEXT="Text";
	public final static String BOUNDS="Bounds";
	public final static String VISIBLE="Visible";
	
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

	public boolean isEqual(Snap other, boolean noisy)
	{
		boolean result = true;
		for( Map.Entry<String,Object> entry : properties.entrySet())
		{
			Object myValue = entry.getValue();
			Object otherValue = other.get(entry.getKey());
			if(!myValue.equals(otherValue))
			{
				if(noisy==true) System.out.println("Snap Mismatch: |"+myValue+"|"+otherValue+"|");
				result= false;
			}
		}
		return result;
	}

	public void addGeometry(Node node)
	{
		addGeometry("",node);
	}

	private boolean isVisible(Node node)
	{
		if(node.getScene()==null) return false;
		Node next = node;
		while(next!=null)
		{
			if(!next.isVisible()) return false;
			next = next.getParent();
		}
		return true;
	}

	public void addGeometry(String prefix, Node	node)
	{
		if(prefix==null || prefix.equals("")) prefix="";
		else prefix=prefix+".";
		boolean visible = isVisible(node);
		add(prefix+VISIBLE,visible);
		if (visible)
		{
			add(prefix+BOUNDS,node.getBoundsInParent());
		}
		
	}

	
}

package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.geometry.BoundingBox;

public class SnapshotTest
{

	private Snapshot shot;
	
	@Before
	public void before()
	{
		shot = new Snapshot();
	}

	@Test
	public void add()
	{
		shot.put("Property","Value");
		assertEquals("Value",shot.get("Property"));
	}
	
	
	@Test(expected=RuntimeException.class)
	public void addDuplicate()
	{
		shot.put("Property","Value");
		shot.put("Property", "Value2");
	}

	@Test
	public void addSnapshotSuccess()
	{
		shot.put("Property","Value");
		Snapshot addition = new Snapshot();
		addition.put("Property2", "Value2");
		shot.add(addition);
		assertEquals("Value",shot.get("Property"));
		assertEquals("Value2",shot.get("Property2"));
	}

	@Test(expected=RuntimeException.class)
	public void addSnapshotDuplicate()
	{
		shot.put("Property","Value");
		Snapshot addition = new Snapshot();
		addition.put("Property", "Value");
		shot.add(addition);
	}

	@Test
	public void addSub()
	{
		shot.put("Property","Value");
		Snapshot addition = new Snapshot();
		addition.put("Property", "Value2");
		shot.add("Sub",addition);
		assertEquals("Value",shot.get("Property"));
		assertEquals("Value2",shot.get("Sub.Property"));
		
	}
	
	@Test
	public void getTypes()
	{
		shot.put("String", "Value");
		assertEquals("Value",shot.asString("String"));
		shot.put("Double", 1d);
		assertEquals(1d,shot.asDouble("Double"),.0001d);
		shot.put("Bounds", new BoundingBox(0d, 50d, 100d, 200d));
		assertEquals(new BoundingBox(0d, 50d, 100d, 200d),shot.asBounds("Bounds"));
	}
	
}

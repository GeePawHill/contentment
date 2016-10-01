package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Snap;
import org.junit.Before;
import org.junit.Test;

import javafx.geometry.BoundingBox;

public class SnapshotTest
{

	private Snap shot;
	
	@Before
	public void before()
	{
		shot = new Snap();
	}

	@Test
	public void add()
	{
		shot.add("Property","Value");
		assertEquals("Value",shot.get("Property"));
	}
	
	
	@Test(expected=RuntimeException.class)
	public void addDuplicate()
	{
		shot.add("Property","Value");
		shot.add("Property", "Value2");
	}

	@Test
	public void addSnapshotSuccess()
	{
		shot.add("Property","Value");
		Snap addition = new Snap();
		addition.add("Property2", "Value2");
		shot.add(addition);
		assertEquals("Value",shot.get("Property"));
		assertEquals("Value2",shot.get("Property2"));
	}

	@Test(expected=RuntimeException.class)
	public void addSnapshotDuplicate()
	{
		shot.add("Property","Value");
		Snap addition = new Snap();
		addition.add("Property", "Value");
		shot.add(addition);
	}

	@Test
	public void addSub()
	{
		shot.add("Property","Value");
		Snap addition = new Snap();
		addition.add("Property", "Value2");
		shot.add("Sub",addition);
		assertEquals("Value",shot.get("Property"));
		assertEquals("Value2",shot.get("Sub.Property"));
		
	}
	
	@Test
	public void getTypes()
	{
		shot.add("String", "Value");
		assertEquals("Value",shot.asString("String"));
		shot.add("Double", 1d);
		assertEquals(1d,shot.asDouble("Double"),.0001d);
		shot.add("Bounds", new BoundingBox(0d, 50d, 100d, 200d));
		assertEquals(new BoundingBox(0d, 50d, 100d, 200d),shot.asBounds("Bounds"));
	}
	
}

package org.geepawhill.contentment.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;

public class PointPairTest
{

	@Test
	public void constructors()
	{
		PointPair[] pairs = new PointPair[] { 
				new PointPair(50d,150d,200d,400d),
				new PointPair(new Point(50d,150d),new Point(200d,400d)),
				new PointPair(new BoundingBox(50d,150d,150d,250d)),
				new PointPair(new Rectangle(50d,150d,150d,250d))
				};
		for(PointPair pair : pairs)
		{
			assertEquals(50d,pair.from.x,0.0001);
			assertEquals(150d,pair.from.y,0.0001);
			assertEquals(200d,pair.to.x,0.0001);
			assertEquals(400d,pair.to.y,0.0001);
		}
	}
	
	@Test
	public void centers()
	{
		PointPair pair = new PointPair(50d,150d,200d,400d);
		assertEquals(125d,pair.centerX(),0.0001);
		assertEquals(275d,pair.centerY(),0.0001);
		assertEquals(new Point(125d,275d),pair.center());
	}
	
	@Test
	public void directions()
	{
		PointPair pair = new PointPair(50d,150d,200d,400d);
		assertEquals(new Point(50d,150d),pair.northwest());
		assertEquals(new Point(50d,400d),pair.northeast());
		assertEquals(new Point(125d,150d),pair.north());
		assertEquals(new Point(50d,400d),pair.southwest());
		assertEquals(new Point(200d,400d),pair.southeast());
		assertEquals(new Point(125d,400d),pair.south());
		assertEquals(new Point(50d,275d),pair.west());
		assertEquals(new Point(200d,275d),pair.east());
	}

}

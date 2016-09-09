package org.geepawhill.contentment.step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class NodeKeeperTest
{
	
	private Group canvas;
	private Text node1;
	private Text node2;
	private NodeKeeper keeper;

	@Before
	public void before()
	{
		canvas = new Group();
		node1 = new Text();
		node2 = new Text();
		keeper = new NodeKeeper();
	}

	@Test
	public void nodeAdded()
	{
		keeper.keep(node1);
		assertEquals(1,keeper.size());
		keeper.addTo(canvas);
		assertTrue(canvas.getChildren().contains(node1));
	}
	
	@Test
	public void nodesRemoved()
	{
		keeper.keep(node1,node2);
		keeper.addTo(canvas);
		keeper.removeFrom(canvas);
		assertEquals(0,canvas.getChildren().size());
	}
	
	@Test
	public void nothingExtraRemoved()
	{
		canvas.getChildren().add(node2);
		keeper.keep(node1);
		keeper.addTo(canvas);
		keeper.removeFrom(canvas);
		assertEquals(1,canvas.getChildren().size());
		assertEquals(node2,canvas.getChildren().get(0));
	}


}

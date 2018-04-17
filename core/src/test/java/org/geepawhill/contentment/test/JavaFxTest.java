package org.geepawhill.contentment.test;

import org.junit.Before;
import org.junit.Test;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.*;

import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.geometry.PointPair;

public class JavaFxTest extends ContentmentTest
{

	@Before
	public void before()
	{
		
	}
	
	@Test
	public void whenIsBoundsActive()
	{
		Group group = new Group();
		Text text = new Text("Here's some text.");
		text.setTextAlignment(TextAlignment.LEFT);
		text.setTextOrigin(VPos.TOP);
		text.setTranslateX(100);
		text.setTranslateY(0);
		System.out.println(new PointPair(text));
		group.getChildren().add(text);
		
	}
}

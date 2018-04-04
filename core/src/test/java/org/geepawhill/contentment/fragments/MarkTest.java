package org.geepawhill.contentment.fragments;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;
import javafx.scene.shape.*;

import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;

public class MarkTest
{
	private Group group;
	private Bezier bezier;
	private Context context;

	@Before
	public void before()
	{
		group = new Group();
		bezier = new Bezier(new PointPair(0,0,100,100));
	}
	
	@Test
	public void addsPath()
	{
		Mark mark = new Mark(GroupSource.value(group),BezierSource.value(bezier),Format.DEFAULT);
		mark.prepare(context);
		assertThat(group.getChildren().size()).isEqualTo(1);
	}
	
	@Test
	public void addsTwoStepPath()
	{
		Mark mark = new Mark(GroupSource.value(group),BezierSource.value(bezier),Format.DEFAULT);
		mark.prepare(context);
		assertThat(group.getChildren().size()).isEqualTo(1);
		Path path = (Path)group.getChildren().get(0);
		assertThat(path.getElements().size()).isEqualTo(2);
		assertThat( path.getElements().get(0)).isInstanceOf(MoveTo.class);
		assertThat( path.getElements().get(1)).isInstanceOf(CubicCurveTo.class);
	}
	
	@Test
	public void completedPathValues()
	{
		Mark mark = new Mark(GroupSource.value(group),BezierSource.value(bezier),Format.DEFAULT);
		mark.prepare(context);
		mark.interpolate(context, 1d);
		Path path = (Path)group.getChildren().get(0);
		MoveTo moveTo = (MoveTo)path.getElements().get(0);
		assertThat(new Point(moveTo.getX(),moveTo.getY())).isEqualTo(new Point(0,0));
		CubicCurveTo curveTo = (CubicCurveTo)path.getElements().get(1);
		assertThat(new Point(curveTo.getX(),curveTo.getY())).isEqualTo(new Point(100,100));
	}

	
	@Test
	public void partialPathValues()
	{
		Mark mark = new Mark(GroupSource.value(group),BezierSource.value(bezier),Format.DEFAULT);
		mark.prepare(context);
		mark.interpolate(context, .5d);
		Path path = (Path)group.getChildren().get(0);
		MoveTo moveTo = (MoveTo)path.getElements().get(0);
		assertThat(new Point(moveTo.getX(),moveTo.getY())).isEqualTo(new Point(0,0));
		CubicCurveTo curveTo = (CubicCurveTo)path.getElements().get(1);
		assertThat(new Point(curveTo.getX(),curveTo.getY())).isEqualTo(new Point(50,50));
	}

}

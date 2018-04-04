package org.geepawhill.contentment.fragments;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.core.*;

public class FaderTest
{
	private Group group;
	private Context context;

	@Before
	public void before()
	{
		group = new Group();
		context = new Context();
	}
	
	@Test
	public void fadesUp()
	{
		group.setOpacity(0d);
		Fader fader = new Fader(GroupSource.value(group),1d);
		fader.prepare(context);
		fader.interpolate(context, 1);
		assertThat(group.getOpacity()).isCloseTo(1d, within(0.01d));
	}
	
	@Test
	public void fadesDown()
	{
		group.setOpacity(1d);
		Fader fader = new Fader(GroupSource.value(group),0d);
		fader.prepare(context);
		fader.interpolate(context, 1);
		assertThat(group.getOpacity()).isCloseTo(0d, within(0.01d));
	}
	
	@Test
	public void worksPartially()
	{
		group.setOpacity(.25d);
		Fader fader = new Fader(GroupSource.value(group),1d);
		fader.prepare(context);
		fader.interpolate(context, .5d);
		assertThat(group.getOpacity()).isCloseTo(.625d, within(0.01d));
	}
}

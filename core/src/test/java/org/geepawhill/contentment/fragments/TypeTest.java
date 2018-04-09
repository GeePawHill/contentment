package org.geepawhill.contentment.fragments;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.*;

import javafx.scene.*;
import javafx.scene.text.Text;


public class TypeTest extends ContentmentTest
{
	private Group owner;
	private String source;
	private Context context;

	@Before
	public void before()
	{
		owner = new Group();
		source = "Hi mom";
	}

	@Test
	public void addsEmptyText()
	{
		Type mark = new Type(GroupSource.value(owner), source);
		mark.prepare(context);
		assertThat(owner.getChildren().size()).isEqualTo(1);
		Node added = owner.getChildren().get(0);
		assertThat(added).isInstanceOf(Text.class);
		Text text = (Text)added;
		assertThat(text.getText()).isEmpty();
	}

	@Test
	public void completedString()
	{
		Type mark = new Type(GroupSource.value(owner), source);
		mark.prepare(context);
		mark.interpolate(context, 1d);
		Node added = owner.getChildren().get(0);
		Text text = (Text)added;
		assertThat(text.getText()).isEqualTo(source);
	}

	@Test
	public void partialString()
	{
		Type mark = new Type(GroupSource.value(owner), source);
		mark.prepare(context);
		mark.interpolate(context, .5d);
		Node added = owner.getChildren().get(0);
		Text text = (Text)added;
		assertThat(text.getText()).isEqualTo(source.substring(0,source.length()/2));
	}
}

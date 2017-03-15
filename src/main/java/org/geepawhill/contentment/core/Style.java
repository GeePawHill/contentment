package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.scene.shape.Shape;

public class Style implements Outliner
{
	public final StyleId id;
	private final StyleApplier applier;
	
	private final String kind;
	private final String nickname;
	private final String value;

	public Style(String nickname, StyleId id, StyleApplier applier, String value)
	{
		this.nickname = nickname;
		this.id = id;
		this.kind = id.name();
		this.applier = applier;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return nickname;
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);
	}
	
	@Override
	public void outline(KvOutline output)
	{
		output.append(id.toString(),nickname);
	}

	public String kind()
	{
		return kind;
	}
}

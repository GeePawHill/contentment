package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.scene.shape.Shape;

public class Style implements Outliner
{
	private final StyleApplier applier;
	
	private final String kind;
	private final String nickname;
	private final String value;

	public Style(String kind, String nickname, StyleApplier applier, String value)
	{
		this.nickname = nickname;
		this.kind = kind;
		this.applier = applier;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return nickname+" ("+value+")";
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);
	}
	
	@Override
	public void outline(KvOutline output)
	{
		output.append(kind,toString());
	}

	public String kind()
	{
		return kind;
	}
}

package org.geepawhill.contentment.format;

import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.Outliner;

import javafx.scene.shape.Shape;

public class Style implements Outliner
{
	private final StyleApplier applier;
	
	private final String key;
	private final String nickname;
	private final String value;

	public Style(String key, String nickname, StyleApplier applier, String value)
	{
		this.nickname = nickname;
		this.key = key;
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
		output.append(key,toString());
	}

	public String key()
	{
		return key;
	}
}

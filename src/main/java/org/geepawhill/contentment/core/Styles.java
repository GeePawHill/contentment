package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

public class Styles implements Outliner
{
	StyleMap styles;

	public Styles()
	{
		styles = new StyleMap();
	}

	public Style set(Style style)
	{
		return styles.set(style);
	}

	public Style get(StyleId id)
	{
		Style result = styles.get(id.name());
		if (result!=null) return result;
		throw new RuntimeException("Attempt to get un-set style: " + id.name());
	}

	public StylesMemo getAll()
	{
		return new StylesMemo(styles.deepCopy());
	}

	public StylesMemo setAll(StylesMemo memo)
	{
		StylesMemo result = new StylesMemo(styles);
		styles = memo.stash;
		return result;
	}

	@Override
	public void outline(KvOutline output)
	{
		output.append("Styles");
		styles.outline(output);
	}
}

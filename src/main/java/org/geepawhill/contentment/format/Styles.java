package org.geepawhill.contentment.format;

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

	public Style get(String kind)
	{
		Style result = styles.get(kind);
		if (result!=null) return result;
		throw new RuntimeException("Attempt to get un-set style: " + kind);
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
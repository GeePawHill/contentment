package org.geepawhill.contentment.core;

import java.util.HashMap;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

public class Styles implements Outliner
{
	HashMap<StyleId, Style> styles;

	public Styles()
	{
		styles = new HashMap<>();
	}

	public Style set(Style style)
	{
		Style oldStyle = styles.get(style.id);
		styles.put(style.id, style);
		return oldStyle;
	}

	public Style get(StyleId id)
	{
		if (styles.containsKey(id)) return styles.get(id);
		throw new RuntimeException("Attempt to get un-set style: " + id.name());
	}

	@SuppressWarnings("unchecked")
	public StylesMemo getAll()
	{
		return new StylesMemo((HashMap<StyleId, Style>) styles.clone());
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
		dumpMap(output, styles);
	}

	private void dumpMap(KvOutline output, HashMap<StyleId, Style> map)
	{
		output.indent();
		for (Style value : map.values())
		{
			value.outline(output);
		}
		output.dedent();
	}
}

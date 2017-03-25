package org.geepawhill.contentment.format;

import java.util.HashMap;

import org.geepawhill.contentment.model.Outliner;
import org.geepawhill.contentment.outline.KvOutline;

public class Styles implements Outliner
{
	HashMap<String,Style> styles;

	public Styles()
	{
		styles = new HashMap<>(); 
	}

	public Style set(Style style)
	{
		return styles.put(style.key(),style);
	}

	public Style get(String kind)
	{
		Style result = styles.get(kind);
		if (result != null) return result;
		throw new RuntimeException("Attempt to get un-set style: " + kind);
	}

	@Override
	public void outline(KvOutline output)
	{
		output.append("Styles");
		output.indent();
		for (Style value : styles.values())
		{
			value.outline(output);
		}
		output.dedent();
	}

}

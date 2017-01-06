package org.geepawhill.contentment.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.geepawhill.contentment.outline.KvOutline;

public class Styles
{
	
	static public class StylesMemo
	{
		private HashMap<StyleId, Style> map;
	}

	List<HashMap<StyleId, Style>> old;

	public Styles()
	{
		old = new ArrayList<>();
		push();
	}

	public void set(Style style)
	{
		old.get(0).put(style.id, style);
	}

	public Style get(StyleId id)
	{
		for (HashMap<StyleId, Style> map : old)
		{
			if (map.containsKey(id)) return map.get(id);
		}
		throw new RuntimeException("Asked for unset style: " + id.toString());
	}

	public void push()
	{
		HashMap<StyleId, Style> hashMap = new HashMap<StyleId, Style>();
		StylesMemo memo = new StylesMemo();
		memo.map = hashMap;
		push(memo);
	}

	public void push(StylesMemo memo)
	{
		old.add(0, memo.map);
	}

	public StylesMemo pop()
	{
		if (old.isEmpty()) throw new RuntimeException("Too many style pops.");
		StylesMemo memo = new StylesMemo();
		memo.map = old.remove(0);
		return memo;
	}

	public void dump(KvOutline output)
	{
		output.append("Styles");
		output.indent();
		int mapNumber = 0;
		for (HashMap<StyleId, Style> map : old)
		{
			output.append("Map" + mapNumber);
			dumpMap(output, map);
			mapNumber += 1;
		}
		output.dedent();
	}

	private void dumpMap(KvOutline output, HashMap<StyleId, Style> map)
	{
		output.indent();
		for (Style value : map.values())
		{
			value.dump(output);
		}
		output.dedent();
	}

}

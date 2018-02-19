package org.geepawhill.contentment.flow;

import java.util.Vector;

public class Flow
{
	public static class Line
	{
		public String text;
		public Color color;
		public Size size;
	}

	private final Vector<Line> lines;

	public Flow()
	{
		lines = new Vector<>();
	}

	public Vector<Line> lines()
	{
		return lines;
	}

	public void load(String source)
	{
		String[] markups = source.split("\n");
		for (String markup : markups)
		{
			Line line = new Line();
			line.text = markup.substring(2);
			line.color = chooseColor(markup);
			line.size = chooseSize(markup);
			lines.add(line);
		}
	}

	private Color chooseColor(String substring)
	{
		char colorChar = substring.toLowerCase().charAt(0);
		switch (colorChar)
		{
		default:
		case 'P':
			return Color.Primary;
		case 's':
			return Color.Secondary;
		case 'e':
			return Color.Emphatic;
		}
	}

	private Size chooseSize(String substring)
	{
		char sizeChar = substring.toLowerCase().charAt(1);
		switch (sizeChar)
		{
		default:
		case 'n':
			return Size.Normal;
		case 'j':
			return Size.Jumbo;
		case 's':
			return Size.Small;
		}
	}
}

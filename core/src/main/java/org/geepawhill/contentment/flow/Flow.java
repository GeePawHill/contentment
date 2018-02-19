package org.geepawhill.contentment.flow;

import java.util.Vector;

public class Flow
{
	static class Line
	{
		public String text;
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
		for(String markup : markups)
		{
			Line line = new Line();
			line.text = markup.substring(2);
			lines.add(line);
		}
	}
}

package org.geepawhill.contentment.flow;

import java.util.Vector;

import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.TopLeft;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.text.Text;

public class Flow
{
	public static class Line
	{
		public String text;
		public Color color;
		public Size size;
		public PointPair layout;
	}

	private final FormatTable table;
	private final Vector<Line> lines;
	private final Text sizer;
	private ScriptWorld world;

	public Flow(ScriptWorld world)
	{
		this.world = world;
		lines = new Vector<>();
		sizer = new Text();
		table = new FormatTable();
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
		layout();
	}
	
	public Letters letters(int i)
	{
		Line line = lines.get(i);
		TopLeft position = new TopLeft(line.layout.from);
		Letters result = new Letters(world,line.text,position,table.get(line.size, line.color));
		return result;
	}

	private void layout()
	{
		double lastEndY = 0; 
		for(Line line : lines)
		{
			Format format = table.get(line.size,line.color);
			format.apply(TypeFace.FACE, sizer);
			sizer.setText(line.text);
			PointPair layout = new PointPair(sizer.getLayoutBounds());
			double newLastEndY = lastEndY+1+layout.height();
			line.layout = new PointPair(0,lastEndY+1,layout.width(),newLastEndY);
			lastEndY = newLastEndY;
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

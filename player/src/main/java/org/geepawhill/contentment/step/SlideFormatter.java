package org.geepawhill.contentment.step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.geepawhill.contentment.step.SlideLine.Layout;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class SlideFormatter
{

	public static final Color PAINT_TITLE = Color.rgb(255, 255, 0);
	public static final Color PAINT_1 = Color.rgb(71,240,48);
	public static final Color PAINT_2 = Color.rgb(0,176,240);
	public static final Color PAINT_3 = Color.RED;
	public static final double VMARGIN = 20d;
	public static final double HMARGIN = 40d;

	private ArrayList<SlideLine> lines;
	
	public List<Text> layout(String... source)
	{
		return layoutFormats(source).stream().map((format) -> format.text).collect(Collectors.toList());
	}

	public List<SlideLine> layoutFormats(String... source)
	{
		lines = new ArrayList<>();
		addFormats(source);
		double maxY = distributeVertically();
		double scale = performScaling(maxY);
		layoutHorizontally(scale);
		return lines;
	}

	private void layoutHorizontally(double scale)
	{
		for (SlideLine line : lines)
		{
			switch (line.layout)
			{
			case LEFT:
				line.text.setX(HMARGIN);
				break;
			case RIGHT:
				line.text.getTransforms()
						.add(new Translate((1600d - HMARGIN) / scale - line.text.getBoundsInLocal().getWidth(), 0d));
				break;
			case CENTER:
				line.text.getTransforms()
						.add(new Translate(800d / scale - line.text.getBoundsInLocal().getWidth() / 2d, 0d));
				break;
			case INDENT:
				line.text.setX(2d * HMARGIN);
				break;
			}
		}
	}

	private double performScaling(double maxY)
	{
		if (maxY <= 600d) return 1d;
		double scale = 900d / maxY;
		for (SlideLine line : lines)
		{
			line.text.getTransforms().add(new Scale(scale, scale));
		}
		return scale;
	}

	private void addFormats(String... sources)
	{
		for (String source : sources)
		{
			addFormat(source);
		}
	}

	private double distributeVertically()
	{
		double nextY = VMARGIN;
		for(SlideLine line : lines)
		{
			nextY = line.setAndIncrementY(nextY);
		}
		return nextY;
	}

	private SlideLine addFormat(String source)
	{
		SlideLine line = makeFormat(source);
		lines.add(line);
		return line;
	}

	private SlideLine makeFormat(String source)
	{
		if (source.startsWith("+++"))
		{
			return new SlideLine(source, 3, 40d, PAINT_3, Layout.INDENT);
		}
		else if (source.startsWith("++"))
		{
			return new SlideLine(source, 2, 60d, PAINT_2, Layout.LEFT);
		}
		else if (source.startsWith("+"))
		{
			return new SlideLine(source, 1, 80d, PAINT_1, Layout.RIGHT);
		}
		else if (source.startsWith("="))
		{
			return new SlideLine(source, 1, 100d, PAINT_TITLE, Layout.CENTER);
		}
		else
		{
			return new SlideLine(source, 0, 100d, PAINT_TITLE, Layout.LEFT);
		}
	}

}

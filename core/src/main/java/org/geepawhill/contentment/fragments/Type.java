package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class Type implements NodeAtom
{
	public final String source;
	private Text text;
	private GroupSource group;
	private Format format;
	private Position position;
	private String lastPartial;

	public Type(GroupSource group, String source, Format format, Position position)
	{
		this.group = group;
		if (source == null || source.isEmpty()) source = " ";
		this.source = source;
		this.format = format;
		this.position = position;
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		if (!partialSource.equals(lastPartial))
		{
			lastPartial = partialSource;
			text.setText(partialSource);
		}
		return true;
	}

	@Override
	public void prepare(Context context)
	{
		this.text = new Text();
		text.setText(source);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		PointPair dimensions = new PointPair(text.getBoundsInLocal());
		position.position(text, dimensions);
		text.setText("");
		JfxUtility.addIfNeeded(group, text);
		lastPartial = "";
	}

	public void at(Position position)
	{
		this.position = position;
	}

	public void format(Format format)
	{
		this.format = format;
	}

	public Text text()
	{
		return text;
	}

	@Override
	public Node get()
	{
		return text;
	}
	
	@Override
	public String toString()
	{
		return "Letters: Source |"+source+"| Group |"+group.get()+"|";
	}
}

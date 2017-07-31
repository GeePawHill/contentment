package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.text.Text;

public class LettersAtom implements Atom
{
	private Text text;
	private GroupSource group;
	private String source;
	private Format format;
	private Position position;
	private String lastPartial;

	public LettersAtom(GroupSource group, String source, Format format, Position position)
	{
		this.group = group;
		this.source = source;
		this.format = format;
		this.position = position;
	}

	@Override
	public boolean partial(Context context, double fraction)
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
	public void setup(Context context)
	{
		this.text = new Text();
		if (source == null || source.isEmpty()) source = " ";
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
}

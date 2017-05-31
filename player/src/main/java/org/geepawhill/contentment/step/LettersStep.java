package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;

import javafx.geometry.VPos;
import javafx.scene.text.Text;

public class LettersStep implements Step
{
	private final Timing timing;
	private final Point center;
	private final String source;
	public final Text text;
	private Format format;

	public LettersStep(Timing timing, String source, Point center, Format format)
	{
		this.timing = timing;
		this.center = center;
		this.text = new Text();
		this.source = source;
		this.format = format;
	}

	@Override
	public void fast(Context context)
	{
		interpolate(context, 1d);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(context, 0d);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new Animator().play(context,onFinished,timing.getAbsolute(),this::interpolate);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	public void interpolate(Context context, double fraction)
	{
		if (fraction == 0d)
		{
			text.setText("");
		}
		text.setTextOrigin(VPos.CENTER);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		text.setText(partialSource);
		text.setX(center.x - text.getBoundsInParent().getWidth() / 2d);
		text.setY(center.y);
	}
	
	public String toString()
	{
		return "Letters: "+source;
	}
}

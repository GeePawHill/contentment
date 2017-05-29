package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.scene.text.Text;

public class LettersStep implements Step
{

	private final Timing timing;
	private final Point center;
	private final String source;
	private final Text text;
	private Transition transition;
	private Format format;

	public LettersStep(Timing timing, String source, Point center, Text text, Format format)
	{
		this.timing = timing;
		this.center = center;
		this.text = text;
		this.source = source;
		this.format = format;
	}

	@Override
	public void after(Context context)
	{
		interpolate(1d, context);
	}

	@Override
	public void unplay(Context context)
	{
		interpolate(0d, context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition(context, this::interpolate, timing().getAbsolute());
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	private void interpolate(double fraction, Context context)
	{
		if (fraction == 0d)
		{
			text.setVisible(false);
		}
		else
			text.setVisible(true);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		text.setText(partialSource);
		text.setX(center.x - text.getBoundsInParent().getWidth() / 2d);
		text.setY(center.y);
	}
}

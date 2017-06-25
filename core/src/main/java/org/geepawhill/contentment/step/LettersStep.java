package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Aligner;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class LettersStep implements ShapeStep
{
	private final Timing timing;
	private final Point center;
	private final String source;
	public final Text text;
	private Format format;
	private Aligner aligner;
	private Object oldPartialSource;

	public LettersStep(Timing timing, String source, Point center, Format format)
	{
		this(timing,source,center,format,HPos.CENTER);
	}

	public LettersStep(Timing timing, String source, Point center, Format format, HPos align)
	{
		this.timing = timing;
		this.center = center;
		this.text = new Text(" ");
		this.source = source;
		this.format = format;
		this.aligner = Aligner.align(align);
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
		new Animator().play(context, onFinished, timing.ms(), this::interpolate);
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
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		if(partialSource.equals(oldPartialSource)) return;
		oldPartialSource=partialSource;
		text.setTextOrigin(VPos.CENTER);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		text.setText(partialSource);
		aligner.align(center, text);
	}

	public String toString()
	{
		return "Letters: " + source;
	}

	@Override
	public Shape shape()
	{
		return text;
	}
}

package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.BezierSource;
import org.geepawhill.contentment.geometry.BezierSplit;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.shape.Path;

/**
 * Animates a single (bezier) line in a given format to the screen using a Path
 * node.
 * 
 * @author GeePaw
 *
 */
public class Mark implements Fragment
{
	private final Path path;
	private final BezierSource source;
	private final GroupSource owner;
	private Format format;

	public Mark(GroupSource owner, BezierSource source, Format format)
	{
		this.owner = owner;
		this.source = source;
		this.path = new Path();
		this.format = format;
	}

	public Mark(GroupSource owner, Format format, PointPair points)
	{
		this(owner, () -> new Bezier(points), format);
	}

	@Override
	public void prepare(Context context)
	{
		format.apply(Frames.KEY, path);
		JfxUtility.addIfNeeded(owner, path);
		interpolate(context, 0d);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		new BezierSplit(fraction, source.get()).setPathToBefore(path);
		return true;
	}

	public void format(Format format)
	{
		this.format = format;
	}
}

package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.BezierSplit;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.shape.Path;

public class BezierAtom implements Atom
{
	public final Path path;
	private BezierSource source;
	private Format format;
	private Actor owner;
	
	public BezierAtom(Actor owner, BezierSource source, Format format)
	{
		this.owner = owner;
		this.source = source;
		this.format = format;
		this.path = new Path();
	}


	public BezierAtom(Actor owner, Format format, PointPair points)
	{
		this(owner, () -> new Bezier(points), format);
	}

	@Override
	public void setup(Context context)
	{
		format.apply(Frames.KEY, path);
		JfxUtility.addIfNeeded(owner, path);
	}

	@Override
	public void partial(Context context, double fraction)
	{
		new BezierSplit(fraction, source.get()).setPathToBefore(path);
	}
}
package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

class Curves
{
	private final ScriptWorld world;
	private final Curve[] curves;

	public Curves(ScriptWorld world, GroupSource owner, Bezier... beziers)
	{
		this.world = world;
		this.curves = new Curve[beziers.length];
		int next = 0;
		for (Bezier bezier : beziers)
		{
			curves[next++] = new Curve(owner, () -> bezier, Format.DEFAULT);
		}
	}

	public void format(Format format)
	{
		for (Curve curve : curves)
		{
			curve.format(format);
		}
	}

	public void draw(double ms)
	{
		for (Curve curve : curves)
		{
			world.add(new AtomStep(Timing.ms(ms / curves.length), curve));
		}
	}

}
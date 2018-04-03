package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

class Curves
{
	private final ScriptWorld world;
	private final Curve[] curves;

	public Curves(ScriptWorld world, Curve... curves)
	{
		this.world = world;
		this.curves = curves;
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
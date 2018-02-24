package org.geepawhill.contentment.actors;

import java.util.Random;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

public class Stroke extends GenericActor {
	private final Bezier bezier;
	private final BezierAtom atom;
	private Random random;

	public Stroke(ScriptWorld world, PointPair points) {
		super(world);
		random = new Random();
		this.bezier = jiggle(random, points);
		this.atom = new BezierAtom(groupSource(), () -> bezier, Format.DEFAULT);
	}

	public Bezier jiggle(Random random, PointPair points) {
		double variance = points.distance() * .1;
		Bezier chosen = new Bezier(points.from, points.along(random.nextDouble()).jiggle(random, 1d, variance),
				points.along(random.nextDouble()).jiggle(random, 1d, variance), points.to);
		return chosen;
	}

	public Stroke format(Format format) {
		atom.format(format);
		return this;
	}

	@Override
	public Stroke draw(double ms) {
		world.add(new AtomStep(Timing.ms(ms), atom));
		return this;
	}
}

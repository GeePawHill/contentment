package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class MoveStep implements Step
{

	private Actor<?> actor;
	private Point destination;
	private Timing timing;
	private PointPair translation;
	private Translate translate;

	public MoveStep(double ms, Actor<?> actor, Point destination)
	{
		this.actor = actor;
		this.destination = destination;
		this.timing = Timing.ms(ms);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		setTranslation();
		new Animator().play(context, onFinished, timing.ms(), this::interpolate);
	}

	private void setTranslation()
	{
		translate = getOrAddTranslate(actor);
		Point start = new PointPair(actor.group().getBoundsInLocal()).from;
		translation = new PointPair(translate.getX(), translate.getY(), destination.x - start.x, destination.y - start.y);
	}

	public Translate getOrAddTranslate(Actor<?> actor)
	{
		for (Transform transform : actor.group().getTransforms())
		{
			if (transform instanceof Translate) return (Translate) transform;
		}
		Translate translate = new Translate(0d, 0d);
		actor.group().getTransforms().add(translate);
		return translate;
	}

	public void interpolate(Context context, double fraction)
	{
		translate.setX(translation.along(fraction).x);
		translate.setY(translation.along(fraction).y);
	}

	@Override
	public void fast(Context context)
	{
		setTranslation();
		interpolate(context, 1d);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

}

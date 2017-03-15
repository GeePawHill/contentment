package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.step.ContextTransition;
import org.geepawhill.contentment.style.Font;
import org.geepawhill.contentment.style.ShapePen;

import javafx.animation.Transition;
import javafx.scene.text.Text;

public class LettersStep implements Step
{

	private final Timing timing;
	private final Point center;
	private final String letters;
	private final Text text;
	private Transition transition;

	public LettersStep(Timing timing, String letters, Point center, Text text)
	{
		this.timing = timing;
		this.center = center;
		this.text = text;
		this.letters = letters;
	}

	@Override
	public void after(Context context)
	{
		interpolate(1d,context);
	}

	@Override
	public void before(Context context)
	{
		interpolate(0d,context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition( context,this::interpolate,timing().getAbsolute());
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public void pause(Context context)
	{
		transition.pause();
	}

	@Override
	public void resume(Context context)
	{
		transition.play();
	}

	@Override
	public Timing timing()
	{
		return timing;
	}
	
	private void interpolate(double fraction, Context context)
	{
		if(fraction==0d)
		{
			text.setVisible(false);
		}
		else
			text.setVisible(true);
		context.apply(Font.KIND,text);
		context.apply(ShapePen.KIND,text);
		String newText = letters.substring(0, (int) (fraction * letters.length()));
		text.setText(newText);
		text.setX(center.x-text.getBoundsInParent().getWidth()/2d);
		text.setY(center.y);
	}
}

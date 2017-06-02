package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Drawable;
import org.geepawhill.contentment.core.Sequence;

import javafx.scene.paint.Paint;

public class CommonSteps
{
	private Sequence sequence;

	public CommonSteps(Sequence sequence)
	{
		this.sequence = sequence;
	}

	public void clear()
	{
		sequence.add(new ClearStep());
	}

	public void hide(Actor actor)
	{
		sequence.add(new HideStep(actor.group()));
	}

	public void show(Actor actor)
	{
		sequence.add(new ShowStep(actor.group()));
	}

	public void cue()
	{
		sequence.add(new CueStep());
	}

	public void delay(double d)
	{
		sequence.add(new DelayStep(d));
	}
	
	public void keyframe(double seconds)
	{
		keyframe(0d,seconds);
	}
	
	public void keyframe(double minutes,double seconds)
	{
		double adjusted = (minutes*60d+seconds)*1000d;
		double here = sequence.runTime();
		double delay = adjusted-here;
		if(delay<0) delay = 1d;
		sequence.add(new DelayStep(delay));
	}
	
	public void reColor(Actor actor,Paint paint)
	{
		sequence.add(new ColorFlipStep(actor,paint));
	}
	
	public Sequence sketch(double ms,Drawable drawable)
	{
		Sequence result = new Sequence();
		result.add(new EntranceStep(drawable));
		result.add(drawable.draw(ms));
		sequence.add(result);
		return result;
	}
	
	public Sequence flash(Drawable drawable)
	{
		Sequence result = new Sequence();
		result.add(new EntranceStep(drawable));
		result.add(drawable.draw(1d));
		sequence.add(result);
		return result;
	}
	
	public Sequence fadeIn(double ms, Drawable drawable)
	{
		Sequence result = new Sequence();
		result.add(new EntranceStep(drawable));
		result.add(new UndolessSetStep<Double>(0d,(opacity) -> drawable.group().setOpacity(opacity)));
		result.add(drawable.draw(ms));
		result.add(new OpacityStep(ms,drawable,1d));
		sequence.add(result);
		return result;
	}

}

package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.Chord;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

public class ScriptWorld
{
	private Addable working;
	private Slide slide;
	
	public ScriptWorld()
	{
		working = new Phrase();
		slide = new Slide();
	}

	public void add(Step step)
	{
		working.add(step);
	}
	public Addable buildPhrase()
	{
		working = new Phrase();
		return working;
	}

	public Addable buildChord()
	{
		working = new Chord();
		return working;
	}
	
	public Addable buildMore(Addable addable)
	{
		working = addable;
		return working;
	}
	
	public Addable endBuild()
	{
		Addable temp = working;
		working=new Phrase();
		return temp;
	}

	public Actor slide()
	{
		return slide;
	}
}

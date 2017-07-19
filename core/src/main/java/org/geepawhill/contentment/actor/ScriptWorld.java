package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.Chord;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

public class ScriptWorld
{
	private Addable working;
	
	public ScriptWorld()
	{
		working = new Phrase();
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
		working=null;
		return temp;
	}
}

package org.geepawhill.contentment.actor;

import java.util.HashMap;

import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.Chord;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

public class ScriptWorld
{
	private Addable working;
	private Slide slide;
	private final HashMap<String,Actor> namedActors;
	private final HashMap<String,Actors> namedParties;
	
	public ScriptWorld()
	{
		working = new Phrase();
		slide = new Slide(this);
		namedActors = new HashMap<>();
		namedParties = new HashMap<>();
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

	public Slide slide()
	{
		return slide;
	}

	public Actor actor(String actor)
	{
		if(!namedActors.containsKey(actor)) throw new RuntimeException("Can't find actor: ["+actor+"]");
		return namedActors.get(actor);
	}

	public void callActor(String name, Actor actor)
	{
		namedActors.put(name, actor);
	}

	public void addToParty(String name, Actor actor)
	{
		Actors actors = namedParties.getOrDefault(name, new Actors());
		actors.add(actor);
		namedParties.put(name, actors);
	}

	public Actors party(String name)
	{
		if(!namedParties.containsKey(name)) throw new RuntimeException("Can't find party: ["+name+"]");
		return namedParties.get(name);
	}
}

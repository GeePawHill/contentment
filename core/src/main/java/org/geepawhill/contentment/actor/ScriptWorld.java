package org.geepawhill.contentment.actor;

import java.util.HashMap;

import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.Chord;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;

public class ScriptWorld
{
	private Addable working;
	private final HashMap<String,Actor> namedActors;
	private final HashMap<String,Party> namedParties;

	public ScriptWorld()
	{
		working = new Phrase();
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
	
	public Addable endPhrase()
	{
		Addable temp = working;
		working=new Phrase();
		return temp;
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
		Party actors = namedParties.getOrDefault(name, new Party());
		actors.add(actor);
		namedParties.put(name, actors);
	}

	public Party party(String name)
	{
		if(!namedParties.containsKey(name)) throw new RuntimeException("Can't find party: ["+name+"]");
		return namedParties.get(name);
	}

	public void dump()
	{
		working.dump();
	}
}

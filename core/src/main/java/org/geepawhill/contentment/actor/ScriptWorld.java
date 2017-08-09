package org.geepawhill.contentment.actor;

import java.util.HashMap;
import java.util.Stack;

import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.Step;

public class ScriptWorld
{
	private Stack<Addable> working;
	private final HashMap<String,Actor> namedActors;
	private final HashMap<String,Party> namedParties;

	public ScriptWorld()
	{
		working = new Stack<>();
		namedActors = new HashMap<>();
		namedParties = new HashMap<>();
	}
	
	public void add(Step step)
	{
		getWorking().add(step);
	}
	
	public void push(Addable addable)
	{
		working.push(addable);
	}
	
	public Addable pop()
	{
		return working.pop();
	}
	
	public void popAndAppend()
	{
		Addable popped = pop();
		add(popped);
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
		Party actors = namedParties.getOrDefault(name, new Party(this));
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
		getWorking().dump();
	}

	private Addable getWorking()
	{
		return working.peek();
	}
}

package org.geepawhill.contentment.actor;

import java.util.*;

import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Assumptions;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.Addable;

public class ScriptWorld
{
	private Stack<Addable> working;
	private final HashMap<String,Actor> namedActors;
	private final HashMap<String,Party> namedParties;
	private final Assumptions assumptions;
	private final Random random;

	public ScriptWorld()
	{
		working = new Stack<>();
		namedActors = new HashMap<>();
		namedParties = new HashMap<>();
		assumptions = new Assumptions();
		random = new Random();
	}
	
	public void add(Gesture step)
	{
		getWorking().add(step);
	}
	
	public Point jiggle(Point point, double probability, double variance)
	{
		double newX = point.x;
		double newY = point.y;
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * variance;
			newX += sign * change;
		}
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * variance;
			newY += sign * change;
		}
		return new Point(newX, newY);
	}

	
	public boolean flip(double truePercentage)
	{
		return random.nextDouble() < truePercentage/100d;
	}
	
	public double nextDouble()
	{
		return random.nextDouble();
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

	public Assumptions assumptions()
	{
		return assumptions;
	}
}

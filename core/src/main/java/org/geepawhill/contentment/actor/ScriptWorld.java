package org.geepawhill.contentment.actor;

import java.util.*;

import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Assumptions;
import org.geepawhill.contentment.fragments.Entrance;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.Addable;

public class ScriptWorld
{
	private Stack<Addable> working;
	private final HashMap<String,Appearance<? extends Actor>> namedActors;
	private final Assumptions assumptions;
	private final Random random;
	private final Vector<Entrance> appearances;

	public ScriptWorld()
	{
		working = new Stack<>();
		namedActors = new HashMap<>();
		assumptions = new Assumptions();
		random = new Random();
		appearances = new Vector<>();
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
	
	public Appearance<? extends Actor> actor(String actor)
	{
		if(!namedActors.containsKey(actor)) throw new RuntimeException("Can't find actor: ["+actor+"]");
		return namedActors.get(actor);
	}

	public void callActor(String name, Appearance<? extends Actor> actor)
	{
		namedActors.put(name, actor);
	}

	private Addable getWorking()
	{
		return working.peek();
	}

	public Assumptions assumptions()
	{
		return assumptions;
	}

	public void addActor(Entrance entrance)
	{
		appearances.add(entrance);
	}
	
	public void removeActor(Entrance entrance)
	{
		appearances.remove(entrance);
	}

	public Vector<Entrance> entrances()
	{
		return appearances;
	}
}

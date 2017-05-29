package org.geepawhill.contentment.actor;

import java.util.ArrayList;
import java.util.Iterator;

public class Actors implements Iterable<Actor>
{
	
	private ArrayList<Actor> actors;
	
	public Actors()
	{
		actors = new ArrayList<>();
	}

	public int size()
	{
		return actors.size();
	}
	
	public void add(Actor actor)
	{
		actors.add(actor);
	}
	
	public void remove(Actor actor)
	{
		actors.remove(actor);
	}
	
	public Actor get(int i)
	{
		return actors.get(i);
	}

	@Override
	public Iterator<Actor> iterator()
	{
		return actors.iterator();
	}
}
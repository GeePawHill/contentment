package org.geepawhill.contentment.actor;

import java.util.ArrayList;
import java.util.Iterator;

public class Actors implements Iterable<Actor<?>>
{

	private ArrayList<Actor<?>> items;

	public Actors()
	{
		items = new ArrayList<>();
	}

	public int size()
	{
		return items.size();
	}

	public void add(Actor<?> actor)
	{
		items.add(actor);
	}

	public void remove(Actor<?> actor)
	{
		items.remove(actor);
	}

	public Actor<?> get(int i)
	{
		return items.get(i);
	}

	@Override
	public Iterator<Actor<?>> iterator()
	{
		return items.iterator();
	}

	public void addAll(Actors source)
	{
		items.addAll(source.items);
	}

	public void clear()
	{
		items.clear();
	}

	public boolean contains(Actor<?> actor)
	{
		return items.contains(actor);
	}

	public void add(Actor<?>... actors)
	{
		for(Actor<?> actor : actors) items.add(actor);
		
	}
}

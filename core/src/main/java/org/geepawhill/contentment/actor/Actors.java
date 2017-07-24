package org.geepawhill.contentment.actor;

import java.util.ArrayList;
import java.util.Iterator;

import org.geepawhill.contentment.step.Step;

import javafx.scene.paint.Paint;

public class Actors implements Iterable<Actor>, Commandable
{
	private ArrayList<Actor> items;

	public Actors()
	{
		items = new ArrayList<>();
	}

	public int size()
	{
		return items.size();
	}

	public void add(Actor actor)
	{
		items.add(actor);
	}

	public void remove(Actor actor)
	{
		items.remove(actor);
	}

	public Actor get(int i)
	{
		return items.get(i);
	}

	@Override
	public Iterator<Actor> iterator()
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

	public boolean contains(Actor actor)
	{
		return items.contains(actor);
	}

	public void add(Actor... actors)
	{
		for(Actor actor : actors) items.add(actor);
		
	}

	@Override
	public Step draw(double ms)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commandable sketch()
	{
		for(Actor actor : this)
		{
			actor.sketch();
		}
		return this;
	}

	@Override
	public Commandable called(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commandable in(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Commandable reColor(Paint paint)
	{
		for(Actor actor : this)
		{
			actor.reColor(paint);
		}
		return this;
		
	}
	
	public Commandable fadeDown()
	{
		for(Actor actor : this)
		{
			actor.fadeDown();
		}
		return this;
		
	}
}

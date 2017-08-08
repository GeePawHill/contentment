package org.geepawhill.contentment.actor;

import java.util.ArrayList;

import org.geepawhill.contentment.atom.GroupSource;

import javafx.scene.paint.Paint;

public class Party implements Actor
{
	private ArrayList<Actor> items;

	public Party()
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

	public void addAll(Party source)
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
	
	public GroupSource groupSource()
	{
		throw new RuntimeException("Parties have no groupSource().");
	}

	@Override
	public Party draw(double ms)
	{
		return this;
	}

	@Override
	public Actor sketch()
	{
		for(Actor actor : items)
		{
			actor.sketch();
		}
		return this;
	}

	@Override
	public Actor called(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor in(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Actor reColor(Paint paint)
	{
		for(Actor actor : items)
		{
			actor.reColor(paint);
		}
		return this;
		
	}
	
	public Actor fadeDown()
	{
		for(Actor actor : items)
		{
			actor.fadeDown();
		}
		return this;
		
	}
	
	public Actor fadeOut()
	{
		for(Actor actor : items)
		{
			actor.fadeOut();
		}
		return this;
		
	}


	@Override
	public Actor appear()
	{
		for(Actor actor : items)
		{
			actor.appear();
		}
		return this;
	}

	@Override
	public Actor disappear()
	{
		for(Actor actor : items)
		{
			actor.disappear();
		}
		return this;
	}

	@Override
	public Actor fadeUp()
	{
		for(Actor actor : items)
		{
			actor.fadeUp();
		}
		return this;
	}

	@Override
	public Actor fadeIn()
	{
		for(Actor actor : items)
		{
			actor.fadeIn();
		}
		return this;
	}
}

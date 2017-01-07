package org.geepawhill.contentment.core;

import java.util.ArrayList;
import java.util.Iterator;

import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.Outliner;

public class Actors implements Iterable<Actor>, Outliner
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

	@Override
	public void outline(KvOutline output)
	{
		if(size()==0) return;
		{
			output.append("Actors");
			output.indent();
			for(Actor actor : actors)
			{
				actor.outline(output);
			}
			output.dedent();
		}
	}
}

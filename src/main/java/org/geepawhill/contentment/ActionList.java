package org.geepawhill.contentment;

import java.util.ArrayList;
import java.util.Iterator;

public class ActionList implements Iterable<Action>
{

	private ArrayList<Action> actions;
	
	public ActionList()
	{
		actions = new ArrayList<>();
	}

	public void add(Action enterBoxedText)
	{
		actions.add(enterBoxedText);
	}

	@Override
	public Iterator<Action> iterator()
	{
		return actions.iterator();
	}

}

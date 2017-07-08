package org.geepawhill.contentment.fast;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Node;

public class Exit implements Fast
{

	private Actor actor;
	ArrayList<Node> nodes;

	public Exit(Actor actor)
	{
		this.actor = actor;
		this.nodes = new ArrayList<>();
	}

	@Override
	public void undo(Context context)
	{
		actor.group().getChildren().addAll(nodes);
		context.add(actor);
	}

	@Override
	public String toString()
	{
		return "Exit: " + actor.nickname();
	}

	@Override
	public void fast(Context context)
	{
		context.remove(actor);
		nodes.clear();
		nodes.addAll(actor.group().getChildren());
		actor.group().getChildren().clear();
	}
}

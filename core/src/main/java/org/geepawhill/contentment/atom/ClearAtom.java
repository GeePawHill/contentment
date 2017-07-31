package org.geepawhill.contentment.atom;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Node;

public class ClearAtom implements Atom
{
	
	public ClearAtom()
	{
	}

	@Override
	public void setup(Context context)
	{
		context.canvas.getChildren().clear();
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		return false;
	}

}

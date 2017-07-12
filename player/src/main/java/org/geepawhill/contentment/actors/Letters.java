package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private LettersAtom atom;

	public Letters(String source, Position position, Format format)
	{
		this.nickname = Names.make(getClass());
		this.atom = new LettersAtom(this, source, format, position);
		this.group = new Group();
	}

	@Override
	public String nickname()
	{
		return nickname;
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		return new AtomStep((long)ms,atom);
	}
	
	@Override
	public String toString()
	{
		return nickname()+" ["+atom.toString()+"]";
	}

}

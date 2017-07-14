package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.BlockAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class CodeBlock implements Actor
{
	private Group group;
	private BlockAtom atom;

	final static private double XMARGIN = 6;
	final static private double YMARGIN = 10;
	
	public CodeBlock(String source,Format format, Position position)
	{
		this.group = new Group();
		this.atom = new BlockAtom(this,source,format,position);
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		return "CodeBlock";
	}

	@Override
	public Step draw(double ms)
	{
		Phrase phrase = new Phrase();
		phrase.add(new AtomStep(Timing.ms(ms),atom));
		return phrase;
	}
}

package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BlockAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class CodeBlock extends GenericActor
{
	private Group group;
	private BlockAtom atom;

	public CodeBlock(ScriptWorld world,String source, Format format, Position position)
	{
		super(world);
		this.group = new Group();
		this.atom = new BlockAtom(this,source,format,position);
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		Phrase phrase = new Phrase();
		phrase.add(new AtomStep(Timing.ms(ms),atom));
		return phrase;
	}

}

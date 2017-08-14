package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.FixedLettersAtom;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Paint;

public class FixedLetters extends GenericActor
{
	
	FixedLettersAtom atom;
	
	public FixedLetters(ScriptWorld world, int columns, int rows)
	{
		super(world);
		atom = new FixedLettersAtom(groupSource(), columns, rows);
	}

	@Override
	public Actor draw(double ms)
	{
		world.add(new AtomStep(Timing.instant(),atom));
		return this;
	}
	
	public FixedLetters at(Position position)
	{
		atom.at(position);
		return this;
	}
	
	public FixedLetters say(Paint paint,int row,int column,String text)
	{
		world.add(new AtomStep(Timing.instant(), atom.say(paint, row, column, text)));
		return this;
	}


}

package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.CueBuilder;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.timing.Timing;

public class ScriptBuilder
{
	
	protected ScriptWorld world;
	private CueBuilder cueBuilder;

	public ScriptBuilder()
	{
		world = new ScriptWorld();
	}
	
	public CueBuilder cue(long beat)
	{
		addToWorking(new AtomStep(Timing.ms(5000),new MarkAtom(beat*1000)));
		cueBuilder = new CueBuilder(world,beat);
		return and();
	}
	
	public CueBuilder and()
	{
		return cueBuilder;
	}
	
	public void addToWorking(Step step)
	{
		world.add(step);
	}
	
	public Addable buildPhrase()
	{
		return world.buildPhrase();
	}
	
	public Addable buildChord()
	{
		return world.buildChord();
	}
	
	public Addable buildMore(Addable addable)
	{
		return world.buildMore(addable);
	}
	
	public Addable endBuild()
	{
		return world.endBuild();
	}
	
}

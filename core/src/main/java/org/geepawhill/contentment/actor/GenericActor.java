package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

public abstract class GenericActor implements Actor
{
	protected ScriptWorld world;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
	}

	@Override
	public GenericActor sketch()
	{
		world.add(new AtomStep(Timing.instant(),new EntranceAtom(this)));
		world.add(draw(500d));
		return this;
	}

	@Override
	public GenericActor called(String name)
	{
		world.callActor(name,this);
		return this;
	}
	
	@Override
	public Actor in(String name)
	{
		world.addToParty(name,this);
		return this;
	}
}

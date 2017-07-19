package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

public abstract class ActorBuilderBase<ACTOR extends Actor<BUILDER>, BUILDER> implements ActorBuilder<BUILDER>
{
	protected ACTOR actor;
	private ScriptWorld world;

	public ActorBuilderBase(ScriptWorld world, ACTOR actor)
	{
		this.world = world;
		this.actor = actor;
	}

	@Override
	public BUILDER sketch()
	{
		world.add(new AtomStep(Timing.instant(),new EntranceAtom(actor)));
		world.add(actor.draw(500d));
		return downcast();
	}
}

package org.geepawhill.contentment.actor;

public class GenericAgentBuilder<ACTOR extends Actor<GenericAgentBuilder<ACTOR>>>
		extends ActorBuilderBase<ACTOR, GenericAgentBuilder<ACTOR>>
{

	protected ScriptWorld world;

	public GenericAgentBuilder(ScriptWorld world,ACTOR agent)
	{
		super(world, agent);
		this.world = world;
	}

	@Override
	public GenericAgentBuilder<ACTOR> downcast()
	{
		return this;
	}

}

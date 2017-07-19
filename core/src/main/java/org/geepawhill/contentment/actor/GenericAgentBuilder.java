package org.geepawhill.contentment.actor;

public class GenericAgentBuilder<ACTOR extends Actor<GenericAgentBuilder<ACTOR>>>
		extends ActorBuilderBase<ACTOR, GenericAgentBuilder<ACTOR>>
{

	public GenericAgentBuilder(ACTOR agent)
	{
		super(agent);
	}

	@Override
	public GenericAgentBuilder<ACTOR> downcast()
	{
		return this;
	}

}

package org.geepawhill.contentment.actor;

public abstract class ActorBuilderBase<ACTOR extends Actor<BUILDER>, BUILDER> implements ActorBuilder<BUILDER>
{
	protected ACTOR actor;

	public ActorBuilderBase(ACTOR actor)
	{
		this.actor = actor;
	}

	@Override
	public BUILDER sketch()
	{
		// do something to the the agent using only Agent API's
		return downcast();
	}
}

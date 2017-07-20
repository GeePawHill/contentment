package org.geepawhill.contentment.actor;

// base class of all AgentBuilders
// the very weird SUBCLASS parameter is the guy implementing us
// we need the downcast() so we can maintain the fluency chain
public interface ActorBuilder<SUBCLASS>
{
	public SUBCLASS sketch();
	public SUBCLASS called(String name);

	// subclass will provide its properly-cast this
	public SUBCLASS downcast();

}

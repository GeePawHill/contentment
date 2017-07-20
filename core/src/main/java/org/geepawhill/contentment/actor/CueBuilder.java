package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.actors.Slide.Builder;

public class CueBuilder
{
	
	private ScriptWorld world;

	public CueBuilder(ScriptWorld world,long beat)
	{
		this.world = world;
	}
	
	// generically pass through to avoid fluency break for new
	// without this, we'd have to change MarkContext when we added a new kind of Agent
	public <ACTOR extends Actor<BUILDER>, BUILDER> BUILDER actor(ACTOR actor)
	{
		return actor.builder(world);
	}
	
	@SuppressWarnings("unchecked")
	public <ACTOR extends Actor<BUILDER>, BUILDER> BUILDER actor(String actor)
	{
		return (BUILDER) world.actor(actor).builder(world);
	}
	
	public Slide.Builder slide()
	{
		return (Builder) actor(world.slide());
	}

}

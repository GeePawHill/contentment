package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Slide;

public class CueBuilder
{
	
	private ScriptWorld world;

	public CueBuilder(ScriptWorld world,long beat)
	{
		this.world = world;
	}
	
	// generically pass through to avoid fluency break for new
	// without this, we'd have to change MarkContext when we added a new kind of Agent
	public <ACTOR> ACTOR actor(ACTOR actor)
	{
		return actor;
	}
	
	@SuppressWarnings("unchecked")
	public <ACTOR extends Actor> ACTOR actor(String actor)
	{
		return (ACTOR) actor(world.actor(actor));
	}
	
	public Slide slide()
	{
		return world.slide();
	}

	public Letters letters(String source)
	{
		return new Letters(world,source);
	}
	
	public Actors party(String name)
	{
		return world.party(name);
	}

}

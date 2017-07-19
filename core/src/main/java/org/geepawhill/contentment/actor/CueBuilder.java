package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.step.MarkStep;

public class CueBuilder
{
	
	private ScriptWorld world;

	public CueBuilder(ScriptWorld world,long beat)
	{
		this.world = world;
		this.world.add(new MarkStep(beat));
	}
	
	// generically pass through to avoid fluency break for new
	// without this, we'd have to change MarkContext when we added a new kind of Agent
	public <ACTOR extends Actor<BUILDER>, BUILDER> BUILDER actor(ACTOR actor)
	{
		return actor.builder(world);
	}
	
	public Slide.Builder slide()
	{
		return actor(world.slide());
	}

}

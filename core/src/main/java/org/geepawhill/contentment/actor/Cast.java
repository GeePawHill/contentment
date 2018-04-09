package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.step.*;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Paint;

public class Cast<ACTOR extends Actor>
{
	public final ACTOR actor;
	protected final ScriptWorld world;
	protected final Entrance entrance;

	public Cast(ScriptWorld world, ACTOR actor)
	{
		this.actor = actor;
		this.world = world;
		this.entrance = new Entrance();
	}
	
	public GroupSource groupSource()
	{
		return entrance;
	}

	public Cast<ACTOR> sketch()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		actor.draw(500d);
		return this;
	}
	
	public Cast<ACTOR> appear()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		actor.draw(1d);
		return this;
	}

	public Cast<ACTOR> disappear()
	{
		world.add(new AtomStep(Timing.instant(),new Exit(groupSource())));
		return this;
	}

	public Cast<ACTOR> called(String name)
	{
		world.callActor(name,actor);
		return this;
	}
	
	public Cast<ACTOR> in(String name)
	{
		world.addToParty(name,actor);
		return this;
	}
	
	public Cast<ACTOR> reColor(Paint paint)
	{
		world.add(new AtomStep(Timing.instant(),new Recolor(entrance, paint)));
		return this;
	}
	
	public Cast<ACTOR> fadeDown()
	{
		world.add(new AtomStep(Timing.ms(500),new Fader(entrance, 0)));
		return this;
	}
	
	public Cast<ACTOR> fadeOut()
	{
		world.push(new Phrase());
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance, 0)));
		world.add(new AtomStep(Timing.instant(),new Exit(entrance)));
		world.popAndAppend();
		return this;
	}
	
	public Cast<ACTOR> fadeIn()
	{
		world.push(new Phrase());
		world.add(new AtomStep(Timing.instant(),entrance));
		world.add(new AtomStep(Timing.instant(),new Fader(entrance, 0)));
		actor.draw(1d);
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance,1)));
		world.popAndAppend();
		return this;
	}
	
	public Cast<ACTOR> fadeUp()
	{
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance,1)));
		return this;
	}
	
}

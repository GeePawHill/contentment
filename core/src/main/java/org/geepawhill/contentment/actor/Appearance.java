package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.step.*;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Paint;

public class Appearance<ACTOR extends Actor>
{
	public final ACTOR actor;
	protected final ScriptWorld world;
	protected final Entrance entrance;

	public Appearance(ScriptWorld world, ACTOR actor)
	{
		this.actor = actor;
		this.world = world;
		this.entrance = actor.entrance();
		actor.format(world.assumptions().format());
	}
	
	public GroupSource groupSource()
	{
		return entrance;
	}

	public Appearance<ACTOR> sketch()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		actor.draw(500d);
		return this;
	}
	
	public Appearance<ACTOR> appear()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		actor.draw(1d);
		return this;
	}

	public Appearance<ACTOR> disappear()
	{
		world.add(new AtomStep(Timing.instant(),new Exit(groupSource())));
		return this;
	}

	public Appearance<ACTOR> called(String name)
	{
		world.callActor(name,actor);
		return this;
	}
	
	public Appearance<ACTOR> reColor(Paint paint)
	{
		world.add(new AtomStep(Timing.instant(),new Recolor(entrance, paint)));
		return this;
	}
	
	public Appearance<ACTOR> fadeDown()
	{
		world.add(new AtomStep(Timing.ms(500),new Fader(entrance, 0)));
		return this;
	}
	
	public Appearance<ACTOR> fadeOut()
	{
		world.push(new Phrase());
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance, 0)));
		world.add(new AtomStep(Timing.instant(),new Exit(entrance)));
		world.popAndAppend();
		return this;
	}
	
	public Appearance<ACTOR> fadeIn()
	{
		world.push(new Phrase());
		world.add(new AtomStep(Timing.instant(),entrance));
		world.add(new AtomStep(Timing.instant(),new Fader(entrance, 0)));
		actor.draw(1d);
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance,1)));
		world.popAndAppend();
		return this;
	}
	
	public Appearance<ACTOR> fadeUp()
	{
		world.add(new AtomStep(Timing.ms(500d),new Fader(entrance,1)));
		return this;
	}
	
	public Appearance<ACTOR> format(Format format)
	{
		actor.format(format);
		return this;
	}
	
	public Appearance<ACTOR> at(Position position)
	{
		actor.at(position);
		return this;
	}

	public Appearance<ACTOR> centered(Point point)
	{
		at(new Centered(point));
		return this;
	}

	public Appearance<ACTOR> centered(int x, int y)
	{
		at(new Centered(x,y));
		return this;
	}
	
}

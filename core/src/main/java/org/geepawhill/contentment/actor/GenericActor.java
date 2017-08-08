package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.ChangeColorAtom;
import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.atom.ExitAtom;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.atom.OpacityAtom;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Paint;

public abstract class GenericActor implements Actor
{
	protected final ScriptWorld world;
	protected final EntranceAtom entrance;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
		this.entrance = new EntranceAtom();
	}
	
	public GroupSource groupSource()
	{
		return entrance;
	}

	@Override
	public GenericActor sketch()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		draw(500d);
		return this;
	}
	
	@Override
	public GenericActor appear()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		draw(1d);
		return this;
	}

	@Override
	public GenericActor disappear()
	{
		world.add(new AtomStep(Timing.instant(),new ExitAtom(groupSource())));
		return this;
	}


	@Override
	public GenericActor called(String name)
	{
		world.callActor(name,this);
		return this;
	}
	
	@Override
	public Actor in(String name)
	{
		world.addToParty(name,this);
		return this;
	}
	
	@Override
	public Actor reColor(Paint paint)
	{
		world.add(new AtomStep(Timing.instant(),new ChangeColorAtom(entrance, paint)));
		return this;
	}
	
	@Override
	public Actor fadeDown()
	{
		world.add(new AtomStep(Timing.ms(500),new OpacityAtom(entrance, 1, 0)));
		return this;
	}
	
	@Override
	public Actor fadeOut()
	{
		world.add(new AtomStep(Timing.ms(500),new OpacityAtom(entrance, 1, 0)));
		world.add(new AtomStep(Timing.instant(),new ExitAtom(entrance)));
		return this;
	}
	
	@Override
	public Actor fadeIn()
	{
		world.add(new AtomStep(Timing.instant(),entrance));
		world.add(new AtomStep(Timing.instant(),new OpacityAtom(entrance, 1, 0)));
		draw(1d);
		world.add(new AtomStep(Timing.ms(500d),new OpacityAtom(entrance,0,1)));
		return this;
	}
	
	@Override
	public Actor fadeUp()
	{
		world.add(new AtomStep(Timing.ms(500d),new OpacityAtom(entrance,0,1)));
		return this;
	}
}

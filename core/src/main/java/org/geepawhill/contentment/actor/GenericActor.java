package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.ChangeColorAtom;
import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.atom.OpacityAtom;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Paint;

public abstract class GenericActor implements Actor
{
	protected ScriptWorld world;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
	}
	
	public GroupSource groupSource()
	{
		return new GroupSource() {

			@Override
			public Group get()
			{
				return group();
			} };
	}

	@Override
	public GenericActor sketch()
	{
		world.add(new AtomStep(Timing.instant(),new EntranceAtom(this)));
		world.add(draw(500d));
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
		world.add(new AtomStep(Timing.instant(),new ChangeColorAtom(this, paint)));
		return this;
	}
	
	@Override
	public Actor fadeDown()
	{
		world.add(new AtomStep(Timing.ms(500),new OpacityAtom(this, 1, 0)));
		return this;
	}
}

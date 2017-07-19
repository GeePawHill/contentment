package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.ActorBuilderBase;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Letters.Builder;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class Stack implements Actor<Stack.Builder>
{
	
	@Override
	public Group group()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String nickname()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Step draw(double ms)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Stack.Builder builder(ScriptWorld world)
	{
		return new Builder(world);
	}
	
	public class Builder extends ActorBuilderBase<Stack,Builder>
	{
		private ScriptWorld world;

		public Builder(ScriptWorld world)
		{
			super(world, Stack.this);
			this.world = world;
		}
		
		public Builder at(int line)
		{
			return this;
		}
		
		@Override
		public Builder downcast()
		{
			return this;
		}
	}


}

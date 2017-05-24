package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.Entrance;

import javafx.scene.Group;

public class Slide implements Actor
{
	
	private Group group;
	
	public Slide()
	{
		group = new Group();
	}

	@Override
	public void outline(KvOutline output)
	{
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		return null;
	}

	public void show(Sequence sequence, String[] slides)
	{
		sequence.add(new Entrance(this));
		flip(sequence,slides);
	}
	
	public void flip(Sequence sequence, String[] lines)
	{
		sequence.add(new SlideStep(group,lines));
	}
}

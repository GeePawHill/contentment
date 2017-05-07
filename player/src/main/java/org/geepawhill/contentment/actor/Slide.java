package org.geepawhill.contentment.actor;

import java.util.ArrayList;
import java.util.List;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.Instant;
import org.geepawhill.contentment.step.SlideFormat;
import org.geepawhill.contentment.step.SlideFormatter;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

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
		// TODO Auto-generated method stub

	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void show(Sequence sequence, String[] slides)
	{
		sequence.add(new Entrance(this));
		flip(sequence,slides);
	}
	
	static class FlipStep implements Instant
	{
		private Group group;
		private List<Text> texts;
		private List<Node> oldNodes;
		
		public FlipStep(Group group,String... lines)
		{
			this.oldNodes = new ArrayList<>();
			this.group = group;
			this.texts = new ArrayList<>();
			for(SlideFormat format : new SlideFormatter().layout(lines))
			{
				texts.add(format.text);
			}
		}

		@Override
		public void before(Context context)
		{
			group.getChildren().clear();
			for(Node node : oldNodes)
			{
				group.getChildren().add(node);
			}
		}

		@Override
		public void after(Context context)
		{
			oldNodes.clear();
			oldNodes.addAll(group.getChildren());
			group.getChildren().clear();
			group.getChildren().addAll(texts);
		}
		
	}
	
	public void flip(Sequence sequence, String[] slides)
	{
		sequence.add(new FlipStep(group,slides));
	}
	
	
}

package org.geepawhill.contentment.actors;

import java.util.ArrayList;
import java.util.List;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.SlideFormatter;
import org.geepawhill.contentment.step.SlideLine;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

class SlideStep implements Step
{
	private Group group;
	private List<Text> texts;
	private List<Node> oldNodes;
	private String[] lines;
	
	public SlideStep(Group group,String... lines)
	{
		this.lines = lines;
		this.oldNodes = new ArrayList<>();
		this.group = group;
		this.texts = new ArrayList<>();
	}

	@Override
	public void undo(Context context)
	{
		group.getChildren().clear();
		System.out.println("Before: "+oldNodes.size());
		for(Node node : oldNodes)
		{
			group.getChildren().add(node);
			Text t = (Text)node;
			System.out.println("Restore: "+t.getText());
		}
	}

	@Override
	public void instant(Context context)
	{
		for(SlideLine format : new SlideFormatter().layoutFormats(lines))
		{
			texts.add(format.text);
		}
		oldNodes.clear();
		oldNodes.addAll(group.getChildren());
		System.out.println("After: "+oldNodes.size());
		for(Node node : oldNodes)
		{
			Text t = (Text)node;
			System.out.println("Save: "+t.getText());
		}
		group.getChildren().clear();
		group.getChildren().addAll(texts);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		instant(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}
	
}
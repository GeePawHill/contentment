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
		for(Node node : oldNodes)
		{
			group.getChildren().add(node);
		}
	}

	@Override
	public void fast(Context context)
	{
		for(SlideLine format : new SlideFormatter().layoutFormats(lines))
		{
			texts.add(format.text);
		}
		oldNodes.clear();
		oldNodes.addAll(group.getChildren());
		group.getChildren().clear();
		group.getChildren().addAll(texts);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}
	
}
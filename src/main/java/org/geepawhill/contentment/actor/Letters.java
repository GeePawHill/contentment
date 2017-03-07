package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.ActorOutliner;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.newstep.StrokeStep;
import org.geepawhill.contentment.newstep.LettersStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Letters implements Actor
{
	private final String nickname;
	private final Group group;
	private String source;
	private Point center;
	private Text text;

	public Letters(String source, Point center)
	{
		this.nickname = Names.make(getClass());
		this.source = source;
		this.center = center;
		this.text = new Text();
		this.group = new Group(text);
	}

	public Sequence sketch(Sequence sequence, FixedTiming timing)
	{
		if (sequence == null) sequence = new Sequence();
		sequence.add(new Entrance(this));
		sequence.add(new LettersStep(timing, source, center, text));
		return sequence;
	}

	@Override
	public String nickname()
	{
		return nickname;
	}

	@Override
	public void outline(KvOutline output)
	{
		ActorOutliner outliner = new ActorOutliner(this, output);
		outliner.start();
		outliner.append("Source",source);
		outliner.startNode(text);
		if (outliner.visibility(text))
		{
			outliner.append("Current",text.getText());
			outliner.bounds(text);
			outliner.opacity(text);
			outliner.strokeWidth(text);
			outliner.lineColor(text);
		}
		outliner.endNode();
		outliner.end();
	}

	@Override
	public Group group()
	{
		return group;
	}

}

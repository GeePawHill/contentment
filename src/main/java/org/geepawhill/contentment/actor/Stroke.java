package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.newstep.Edge;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Stroke implements Actor
{
	private final String nickname;
	public final Line line;
	private PointPair points;
	private final Group group;

	public Stroke(PointPair points)
	{
		this.nickname = Names.make(getClass());
		this.points = points;
		this.line = new Line();
		this.group = new Group(line);
	}

	public Sequence sketch(Sequence sequence, FixedTiming timing)
	{
		if (sequence == null) sequence = new Sequence();
		sequence.add(new Entrance(this));
		sequence.add(new Edge(timing, points, line));
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
		outliner.startNode(line);
		if (outliner.visibility(line))
		{
			outliner.bounds(line);
			outliner.opacity(line);
			outliner.strokeWidth(line);
			outliner.lineColor(line);
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

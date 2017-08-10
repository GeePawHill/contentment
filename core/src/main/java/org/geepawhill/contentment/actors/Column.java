package org.geepawhill.contentment.actors;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.NodeAtom;
import org.geepawhill.contentment.atom.NodeSource;
import org.geepawhill.contentment.atom.RemoveAtom;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Placement;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class Column extends GenericActor
{

	private final ArrayList<NodeAtom> lines;
	private PointPair area;
	private HPos horizontal;
	private VPos vertical;

	public Column(ScriptWorld world, PointPair area, HPos horizontal, VPos vertical)
	{
		super(world);
		this.area = area;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.lines = new ArrayList<>();
	}

	@Override
	public Column draw(double ms)
	{
		throw new RuntimeException("Attempt to draw Slide actor.");
	}

	public Column head(NodeAtom source)
	{
		wipe();
		line(source);
		return this;
	}

	public void line(NodeAtom line)
	{
		NodeSource above = NodeSource.NONE;
		if(!lines.isEmpty()) above = lines.get(lines.size()-1);
		world.add(new AtomStep(Timing.ms(1d), line));
		line.at(new Placement(area,above,horizontal));
		lines.add(line);
	}

	public Column wipe()
	{
		for (NodeAtom line : lines)
		{
			world.add(new AtomStep(Timing.instant(), new RemoveAtom(entrance, line)));
		}
		lines.clear();
		return this;
	}

	public Column enter()
	{
		world.add(new AtomStep(Timing.instant(), entrance));
		return this;
	}

}

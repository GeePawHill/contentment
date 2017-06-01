package org.geepawhill.contentment.actors;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.connector.arrow.ArrowComputer;
import org.geepawhill.contentment.connector.arrow.ArrowPoints;
import org.geepawhill.contentment.connector.arrow.NodeArrowComputer;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.EntranceStep;
import org.geepawhill.contentment.step.HandStep;
import org.geepawhill.contentment.step.OneWayStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.Scheduler;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Arrow implements Actor
{
	final String nickname;
	
	private final Group group;

	private HandStep mainStep;
	private StrokeStep fromTopStep;
	private StrokeStep fromBottomStep;
	private StrokeStep toTopStep;
	private StrokeStep toBottomStep;
	
	private boolean pointAtFrom;
	private boolean pointAtTo;

	private ArrowComputer computer;
	private ArrowPoints points;

	private ArrayList<Step> steps;

	public Arrow(Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo,Format format)
	{
		this.nickname = Names.make(getClass());
		this.pointAtFrom = pointAtFrom;
		this.pointAtTo = pointAtTo;
		this.computer = new NodeArrowComputer(from.group(),to.group());
		this.group = new Group();
		steps = new ArrayList<>();
		mainStep = new HandStep(new RelativeTiming(.9d),new PointPair(0d,0d,0d,0d),format);
		steps.add(mainStep);
		if(pointAtFrom)
		{
			fromTopStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),format);
			steps.add(fromTopStep);
			fromBottomStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),format);
			steps.add(fromBottomStep);
		}
		if(pointAtTo)
		{
			toTopStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),format);
			steps.add(toTopStep);
			toBottomStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),format);
			steps.add(toBottomStep);
		}
		group.getChildren().add(mainStep.node());
		if(pointAtFrom)
		{
			group.getChildren().add(fromTopStep.shape());
			group.getChildren().add(fromBottomStep.shape());
		}
		if(pointAtTo)
		{
			group.getChildren().add(toTopStep.shape());
			group.getChildren().add(toBottomStep.shape());
		}
	}

	public String nickname()
	{
		return nickname;
	}

	public void sketch(Sequence sequence, double ms)
	{
		sequence.add(new EntranceStep(this));
		sequence.add(new OneWayStep((context) -> boundsChanged()));
		for(Step step : steps) sequence.add(step);
		new Scheduler().build(ms,steps.toArray(new Step[0]));
	}
	
	private void boundsChanged()
	{
		points = computer.compute();
		mainStep.setPoints(points.main);
		if(pointAtFrom)
		{
			fromTopStep.setPoints(points.fromTop);
			fromBottomStep.setPoints(points.fromBottom);
		}
		if(pointAtTo)
		{
			toTopStep.setPoints(points.toTop);
			toBottomStep.setPoints(points.toBottom);
		}
	}
	
	@Override
	public Group group()
	{
		return group;
	}

}

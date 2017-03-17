package org.geepawhill.contentment.actor;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.arrow.ArrowComputer;
import org.geepawhill.contentment.actor.arrow.ArrowPoints;
import org.geepawhill.contentment.actor.arrow.NodeArrowComputer;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.OneWayStep;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class Arrow implements Actor
{
	final String nickname;
	
	private final Group group;

	private Point center;

	private StrokeStep mainStep;
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
		mainStep = new StrokeStep(new RelativeTiming(.8d),new PointPair(0d,0d,0d,0d),format);
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
		group.getChildren().add(mainStep.shape());
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

	@Override
	public void outline(KvOutline output)
	{
	}

	public void sketch(Sequence sequence, double ms)
	{
		sequence.add(new Entrance(this));
		sequence.add(new OneWayStep((context) -> boundsChanged()));
		for(Step step : steps) sequence.add(step);
		new TimingBuilder().build(ms,steps.toArray(new Step[0]));
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
	
	public Step move(double newX,double newY)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(group);
		transition.setToX(newX-center.x);
		transition.setToY(newY-center.y);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
	}

	@Override
	public Group group()
	{
		return group;
	}

}

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
import javafx.scene.shape.Line;
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
	private Line main;
	private Line fromTop;
	private Line fromBottom;
	private Line toTop;
	private Line toBottom;
	private ArrowPoints points;

	private Format format;

	public Arrow(Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo,Format format)
	{
		this.format = format;
		this.nickname = Names.make(getClass());
		this.pointAtFrom = pointAtFrom;
		this.pointAtTo = pointAtTo;
		this.computer = new NodeArrowComputer(from.group(),to.group());
		this.group = new Group();
		this.main = new Line();
		group.getChildren().add(main);
		if(pointAtFrom)
		{
			this.fromTop = new Line();
			this.fromBottom = new Line();
			group.getChildren().add(fromTop);
			group.getChildren().add(fromBottom);
		}
		if(pointAtTo)
		{
			this.toTop = new Line();
			this.toBottom = new Line();
			group.getChildren().add(toTop);
			group.getChildren().add(toBottom);
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
		ArrayList<Step> steps = new ArrayList<>();
		mainStep = new StrokeStep(new RelativeTiming(.8d),new PointPair(0d,0d,0d,0d),main,format);
		steps.add(mainStep);
		sequence.add(mainStep);
		if(pointAtFrom)
		{
			fromTopStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),fromTop,format);
			steps.add(fromTopStep);
			sequence.add(fromTopStep);
			fromBottomStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),fromBottom,format);
			steps.add(fromBottomStep);
			sequence.add(fromBottomStep);
		}
		if(pointAtTo)
		{
			toTopStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),toTop,format);
			steps.add(toTopStep);
			sequence.add(toTopStep);
			toBottomStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),toBottom,format);
			steps.add(toBottomStep);
			sequence.add(toBottomStep);
		}
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
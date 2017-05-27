package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TargetBox implements Actor
{
	final String nickname;
	final String source;
	
	private final Group group;
	private Text text;

	private Point center;
	
	private StrokeStep northStep;
	private StrokeStep southStep;
	private StrokeStep westStep;
	private StrokeStep eastStep;
	private Format format;

	public TargetBox(String source, Point center, Format format)
	{
		this.format = format;
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		text = new Text();
		text.setTextOrigin(VPos.CENTER);
		northStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),format);
		westStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),format);
		southStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),format);
		eastStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),format);
		this.group = JfxUtility.makeGroup(this,text,northStep.shape(),westStep.shape(),southStep.shape(),eastStep.shape());
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
		LettersStep lettersStep = new LettersStep(new RelativeTiming(.6d), source, center, text, format);
		new TimingBuilder().build(ms, lettersStep,northStep,westStep,southStep,eastStep);
		sequence.add(new Entrance(this));
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(text,this::boundsChanged));
		sequence.add(northStep);
		sequence.add(eastStep);
		sequence.add(southStep);
		sequence.add(westStep);
	}
	
	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.change(4d,4d,300d,300d);
		northStep.setPoints(grow.northLine());
		westStep.setPoints(grow.westLine());
		southStep.setPoints(grow.southLine());
		eastStep.setPoints(grow.eastLine());
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

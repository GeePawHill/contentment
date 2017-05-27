package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Placeholder implements Actor
{
	final String nickname;
	final String source;

	private final Group group;
	private Text text;

	private PointPair bounds;

	private StrokeStep northStep;
	private StrokeStep southStep;
	private StrokeStep westStep;
	private StrokeStep eastStep;
	private Format format;

	public Placeholder(String source, Point center, double width, double height)
	{
		this(source, PointPair.centerAt(center, width, height));
	}

	public Placeholder(String source, PointPair bounds)
	{
		this.format = new Format(TypeFace.color(source, Color.YELLOW, 1d), TypeFace.smallFixed(),
				Frames.frame(source, Color.YELLOW, 1d, 1d), Dash.dash(source, 4d, 4d));
		this.nickname = Names.make(getClass());
		this.bounds = bounds;
		this.source = source;
		text = new Text();
		text.setTextOrigin(VPos.CENTER);
		northStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		westStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		southStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		eastStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		this.group = JfxUtility.makeGroup(this, text, northStep.shape(), westStep.shape(), southStep.shape(), eastStep.shape());
	}

	public String nickname()
	{
		return nickname;
	}

	public void sketch(Sequence sequence, double ms)
	{
		LettersStep lettersStep = new LettersStep(new RelativeTiming(.6d), source, bounds.grow(-32d).north(), text, format);
		new TimingBuilder().build(ms, lettersStep, northStep, westStep, southStep, eastStep);
		sequence.add(new Entrance(this));
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(text, this::boundsChanged));
		sequence.add(northStep);
		sequence.add(eastStep);
		sequence.add(southStep);
		sequence.add(westStep);
	}

	private void boundsChanged(PointPair pair)
	{
		northStep.setPoints(bounds.northLine());
		westStep.setPoints(bounds.westLine());
		southStep.setPoints(bounds.southLine());
		eastStep.setPoints(bounds.eastLine());
	}

	@Override
	public Group group()
	{
		return group;
	}

}

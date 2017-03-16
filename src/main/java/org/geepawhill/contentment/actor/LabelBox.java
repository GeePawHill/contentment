package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TextColors;
import org.geepawhill.contentment.style.TextFont;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LabelBox implements Actor
{
	final String nickname;
	final String source;
	
	private final Group group;
	private Text text;
	private Line north;
	private Line south;
	private Line east;
	private Line west;
	

	private Point center;
	
	private StrokeStep northStep;
	private StrokeStep southStep;
	private StrokeStep westStep;
	private StrokeStep eastStep;
	private Format format;
	
	private final static Format UNSPECIFIED = 
			new Format("Unspecified",
					TextColors.unspecified(),
					TextFont.unspecified(),
					Frames.unspecified(),
					Dash.solid()
					);

	public LabelBox(String source, double xCenter, double yCenter)
	{
		this(source,new Point(xCenter,yCenter), UNSPECIFIED);
	}
	
	public LabelBox(String source, Point center, Format format)
	{
		this.format = format;
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		text = new Text();
		text.setTextOrigin(VPos.CENTER);
		north = new Line();
		south = new Line();
		east = new Line();
		west = new Line();
		this.group = JfxUtility.makeGroup(this,text,north,west,south,east);
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
		northStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),north, format);
		westStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),west, format);
		southStep = new StrokeStep(new RelativeTiming(.1d),new PointPair(0d,0d,0d,0d),south,format);
		eastStep = new StrokeStep(new RelativeTiming(.1d), new PointPair(0d,0d,0d,0d),east, format);
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
		PointPair grow = pair.grow(4d);
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

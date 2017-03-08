package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.newstep.BoundsStep;
import org.geepawhill.contentment.newstep.Entrance;
import org.geepawhill.contentment.newstep.LettersStep;
import org.geepawhill.contentment.newstep.OvalStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class OvalText implements Actor
{
	final String nickname;
	final String source;

	private final Group group;
	private Text text;
	private Ellipse oval;

	private Point center;

	private OvalStep ovalStep;

	public OvalText(String source, double xCenter, double yCenter)
	{
		this.nickname = Names.make(getClass());
		this.center = new Point(xCenter, yCenter);
		this.source = source;
		text = new Text(xCenter, yCenter, "");
		oval = new Ellipse();
		this.group = JfxUtility.makeGroup(this, text, oval);
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
		LettersStep lettersStep = new LettersStep(new RelativeTiming(.6d), source, center, text);
		ovalStep = new OvalStep(new RelativeTiming(.4d), new PointPair(0d, 0d, 0d, 0d), oval);
		new TimingBuilder().build(ms, lettersStep, ovalStep);
		sequence.add(new Entrance(this));
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(text, this::boundsChanged));
		sequence.add(ovalStep);
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(4d);
		ovalStep.setPoints(grow);
	}

	@Override
	public Group group()
	{
		return group;
	}

}

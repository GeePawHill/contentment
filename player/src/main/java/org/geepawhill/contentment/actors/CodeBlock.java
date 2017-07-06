package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.fast.Compute;
import org.geepawhill.contentment.format.Aligner;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CodeBlock implements Actor
{
	private String source;
	private Point center;
	private Format format;
	private Text text;
	private Rectangle rectangle;
	private Group group;
	private Aligner aligner;

	public CodeBlock(String source,Point center,Format format, Aligner aligner)
	{
		this.source = source;
		this.center = center;
		this.format = format;
		this.aligner = aligner;
		this.text = new Text();
		this.rectangle = new Rectangle();
		this.group = new Group();
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		return "CodeBlock";
	}

	@Override
	public Step draw(double ms)
	{
		Phrase phrase = new Phrase();
		phrase.add(new Compute(this::computeBounds));
		phrase.add(new AddNode(group,text));
		phrase.add(new AddNode(group,rectangle));
		return phrase;
	}
	
	final static private double XMARGIN = 6;
	final static private double YMARGIN = 10;
	
	private void computeBounds(Context context,double fraction)
	{
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		format.apply(Frames.KEY, rectangle);
		text.setText(source);
		PointPair bounds = new PointPair(text.getBoundsInLocal());
		aligner.align(new Point(center.x-XMARGIN,center.y), text);

		PointPair bounds2 = new PointPair(text.getBoundsInLocal());
		rectangle.setWidth(bounds2.width()+2*XMARGIN);
		rectangle.setHeight(bounds2.height()+2*YMARGIN);
		aligner.align(new Point(center.x, center.y-YMARGIN),rectangle);		
	}
}

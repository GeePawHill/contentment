package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BlockAtom implements Fragment
{

	private GroupSource actor;
	private String source;
	private Format format;
	private Position position;
	private Text text;
	private Rectangle rectangle;

	final static private double XMARGIN = 6;
	final static private double YMARGIN = 10;

	public BlockAtom(GroupSource actor, String source, Format format, Position position)
	{
		this.actor = actor;
		this.source = source;
		this.format = format;
		this.position = position;
		this.rectangle = new Rectangle();
		this.text = new Text();
	}

	@Override
	public void prepare(Context context)
	{
		text.setText(source);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		PointPair dimensions = new PointPair(text.getBoundsInLocal()).grow(XMARGIN, YMARGIN);
		position.position(text, dimensions);
		JfxUtility.addIfNeeded(actor,text);
		dimensions = new PointPair(text.getBoundsInParent()).grow(XMARGIN*2, YMARGIN*2);
		format.apply(Frames.KEY, rectangle);
		rectangle.setWidth(dimensions.width());
		rectangle.setHeight(dimensions.height());
		rectangle.setTranslateX(dimensions.from.x);
		rectangle.setTranslateY(dimensions.from.y);
		JfxUtility.addIfNeeded(actor,rectangle);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}

}

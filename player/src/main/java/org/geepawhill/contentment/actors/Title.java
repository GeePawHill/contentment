package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.ViewPort;
import org.geepawhill.contentment.step.AddNode;
import org.geepawhill.contentment.step.ChangeCentered;
import org.geepawhill.contentment.utility.Names;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Title implements Actor
{
	private static final double FROM_Y = 30d;
	private final String nickname;
	private Group group;
	private Text text;
	private Rectangle rectangle;

	private final double XMARGIN = 20d;
	private final double XINSET = 30d;
	private final double YINSET = 20d;
	private PointPair points;

	public Title()
	{
		this.points = new PointPair(XMARGIN,FROM_Y,ViewPort.WIDTH-XMARGIN-XINSET,30d + 2 * YINSET);
		this.nickname = Names.make(getClass());
		this.group = new Group();
		text = new Text(800d, FROM_Y + YINSET, "");
		rectangle = new Rectangle(points.from.x,points.from.y,points.to.x,points.to.y );
		rectangle.setHeight(50d + 2 * YINSET);
		rectangle.setStrokeWidth(2d);
		rectangle.setFill(Color.color(.3d, .3d, .3d));
		rectangle.setStroke(Color.color(.9d, .9d, .9d));
		rectangle.setArcHeight(40d);
		rectangle.setArcWidth(40d);
		Color color = Color.color(.9d, .9d, .9d);
		text.setFill(color);
		text.setStroke(color);
		text.setTextOrigin(VPos.TOP);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(new Font("Tahoma", 40d));
		text.setTextOrigin(VPos.TOP);
		text.setStrokeWidth(3d);
		text.setStrokeLineCap(StrokeLineCap.ROUND);
	}

	public String nickname()
	{
		return nickname;
	}

	@Override
	public Group group()
	{
		return group;
	}

	public Sequence change(String newSource)
	{
		return new Sequence().add(new ChangeCentered(text, newSource, points));
	}

	@Override
	public Sequence draw(double ms)
	{
		return new Sequence().add(new AddNode(group, rectangle)).add(new AddNode(group, text)).schedule(0d);
	}

}

package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.ShowStep;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Tale implements Actor
{
	private final String nickname;
	private Group group;
	private double fromY;
	private Text text;
	private Rectangle rectangle;

	private final double XMARGIN = 20d;
	private final double XINSET = 30d;
	private final double YINSET = 20d;

//	private final double GREEN = (double) 0xbe / (double) 0xff;
//	private final double BLUE = (double) 0x8b / (double) 0xff;
	
	private static int index=0;

	public Tale(String value, double fromY)
	{
		this.nickname = Names.make(getClass());
		this.fromY = fromY;
		text = new Text(800d, fromY + YINSET, value);
		rectangle = new Rectangle(XMARGIN, fromY, 1600d - XMARGIN - XINSET, 30d + 2 * YINSET);
		this.group = JfxUtility.makeGroup(index++,this,rectangle,text);
		adjustTextSize();
		rectangle.setHeight(text.getBoundsInLocal().getHeight() + 2 * YINSET);
		rectangle.setStrokeWidth(2d);
		rectangle.setFill(Color.color(.3d, .3d, .3d));
		rectangle.setStroke(Color.color(.9d, .9d, .9d));
		rectangle.setArcHeight(40d);
		rectangle.setArcWidth(40d);
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	@Override
	public void outline(KvOutline output)
	{
		
	}

	private void adjustTextSize()
	{
		Color color = Color.color(.9d, .9d, .9d);
		text.setFill(color);
		text.setStroke(color);
		text.setTextOrigin(VPos.TOP);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(new Font("Tahoma", 40d));
		text.setTextOrigin(VPos.TOP);
		text.setX(800d - text.getBoundsInLocal().getWidth() / 2d);
		text.setY(fromY + YINSET);
		text.setStrokeWidth(3d);
		text.setStrokeLineCap(StrokeLineCap.ROUND);
	}

	@Override
	public Group group()
	{
		return group;
	}

	public Step show()
	{
		return new ShowStep(group);
	}

	public Step setText(String newValue)
	{
		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d,(frac,context)-> { changeText(newValue); } ),
		};
		return new TimedSequence(1d, group, substeps);

	}

	private void changeText(String newValue)
	{
		text.setText(newValue);
		adjustTextSize();
	}

}

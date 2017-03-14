package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.newstep.SetTitleStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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

//	private final double GREEN = (double) 0xbe / (double) 0xff;
//	private final double BLUE = (double) 0x8b / (double) 0xff;
	
	public Title(String value)
	{
		this.nickname = Names.make(getClass());
		text = new Text(800d, FROM_Y + YINSET, value);
		rectangle = new Rectangle(XMARGIN, FROM_Y, 1600d - XMARGIN - XINSET, 30d + 2 * YINSET);
		this.group = JfxUtility.makeGroup(this,rectangle,text);
		rectangle.setHeight(50d + 2 * YINSET);
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

	@Override
	public Group group()
	{
		return group;
	}

	public void setText(Sequence sequence, String newValue)
	{
		sequence.add(new SetTitleStep(text,newValue));
	}

}

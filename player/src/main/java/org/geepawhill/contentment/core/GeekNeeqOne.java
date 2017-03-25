package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.TextColors;
import org.geepawhill.contentment.style.TextFont;

import javafx.scene.paint.Color;

public class GeekNeeqOne
{
	
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	Format main = new Format( TextColors.color("main", Color.YELLOW, .8d), TextFont.largeHand());

	private CommonSteps common;

	public GeekNeeqOne(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		
		common.clear();
		
		Title title = new Title("Geek-Neeq #1");
		common.show(title);
		
		Letters technique = new Letters("Technique",new Point(1200d,VQUARTER*3),main);
		technique.fadeIn(sequence,1000d);
		common.delay(5000d);
		
		Letters pattern = new Letters("Implementation Pattern",new Point(1200d,VQUARTER*2),main);
		pattern.fadeIn(sequence,1000d);
		common.delay(5000d);
		
		Letters principle = new Letters("Principle", new Point(1200d,VQUARTER),main);
		principle.fadeIn(sequence, 1000d);
		common.delay(5000d);
	}
	
}

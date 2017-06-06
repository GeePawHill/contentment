package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Placeholder;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;

public class GeekNeeqOne
{
	
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	private Format main = new Format( TypeFace.color("main", Color.YELLOW, .8d), TypeFace.largeHand());

	private CommonSteps common;

	public GeekNeeqOne(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		
		common.clear();
		
		Title title = new Title();
		common.appear(title);
		sequence.add(title.change("Geek-Neeq #1"));
		
		Placeholder visible = new Placeholder("Visible Man",new PointPair(200d,200d,500d,500d));
		common.appear(visible);
		common.cue();
		
		Letters technique = new Letters("Technique",new Point(1200d,VQUARTER*3),main);
		common.fadeIn(1000d,technique);
		common.keyframe(5d);
		common.cue();
		
		Letters pattern = new Letters("Implementation Pattern",new Point(1200d,VQUARTER*2),main);
		common.fadeIn(1000d,pattern);
		common.keyframe(15d);
		common.cue();
		
		Letters principle = new Letters("Principle", new Point(1200d,VQUARTER),main);
		common.fadeIn( 1000d,principle);
		common.cue();
	}
	
}

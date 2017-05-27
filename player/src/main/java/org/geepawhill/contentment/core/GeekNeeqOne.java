package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.Placeholder;
import org.geepawhill.contentment.actor.Title;
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
		common.show(title);
		title.setText(sequence,"Geek-Neeq #1");
		
		Placeholder visible = new Placeholder("Visible Man",new PointPair(200d,200d,500d,500d));
		visible.sketch(sequence, 1d);
		common.stop();
		
		Letters technique = new Letters("Technique",new Point(1200d,VQUARTER*3),main);
		technique.fadeIn(sequence,1000d);
		common.keyframe(5d);
		common.stop();
		
		Letters pattern = new Letters("Implementation Pattern",new Point(1200d,VQUARTER*2),main);
		pattern.fadeIn(sequence,1000d);
		common.keyframe(15d);
		common.stop();
		
		Letters principle = new Letters("Principle", new Point(1200d,VQUARTER),main);
		principle.fadeIn(sequence, 1000d);
		common.stop();
	}
	
}

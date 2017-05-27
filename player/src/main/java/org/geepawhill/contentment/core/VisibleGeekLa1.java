package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Placeholder;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Grid;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;

public class VisibleGeekLa1
{
	
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	private Format main = new Format( TypeFace.color("main", Color.YELLOW, .8d), TypeFace.largeHand());

	private CommonSteps common;

	private Grid grid;

	public VisibleGeekLa1(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.grid = new Grid(8,4,50d,50d);
	}

	public void add()
	{
		Title title = new Title();
		common.show(title);
		title.setText(sequence,"Visible Geek: Mp3 Dream #1");
		
		
		Placeholder visible = new Placeholder("Visible Man",grid.area(6, 1, 2,2));
		visible.sketch(sequence, 1d);
		common.keyframe(9d);
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
		common.delay(5000d);
	}

//	private void addHoldersFor(ArrayList<Placeholder> holders, int i)
//	{
//		holders.add(makeHolder(PointPair.north(i)));
//		if(i==0) return;
//		holders.add(makeHolder(PointPair.northeast(i)));
//		holders.add(makeHolder(PointPair.south(i)));
//		holders.add(makeHolder(PointPair.southwest(i)));
//		holders.add(makeHolder(PointPair.northwest(i)));
//		holders.add(makeHolder(PointPair.southeast(i)));
//		holders.add(makeHolder(PointPair.east(i)));
//		holders.add(makeHolder(PointPair.west(i)));
//		holders.add(makeHolder(PointPair.westsouthwest(i)));
//		holders.add(makeHolder(PointPair.northnortheast(i)));
//	}
//	
//	private Placeholder makeHolder(Point pair)
//	{
//		return new Placeholder("#",pair,25d,25d);
//	}

}

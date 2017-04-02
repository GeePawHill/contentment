package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.Placeholder;
import org.geepawhill.contentment.actor.Stroke;
import org.geepawhill.contentment.actor.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.paint.Color;

public class VisibleGeekLa1
{
	
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	private Format main = new Format( TypeFace.color("main", Color.YELLOW, .8d), TypeFace.largeHand());

	private CommonSteps common;

	public VisibleGeekLa1(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		
		Format gridFormat = new Format(Frames.frame("", Color.YELLOW, 1d, 1d),Dash.solid(),TypeFace.color("",Color.YELLOW,1d),TypeFace.smallSans());
		common.clear();
		ArrayList<Placeholder> holders = new ArrayList<>();
		for(int i=0;i<PointPair.FRAMES/2;i++)
		{
			addHoldersFor(holders,i);
		}
		for(Placeholder holder : holders)
		{
			holder.sketch(sequence, 1d);
		}
//		ArrayList<PointPair> hstrokes = new ArrayList<>();
//		int div=8;
//		for(int i=1;i<div;i++)
//		{
//			double fraction = ((double)i)/((double)div);
//			hstrokes.add(PointPair.hline(fraction));
//		}
//		ArrayList<PointPair> vstrokes = new ArrayList<>();
//		for(int i=1;i<div;i++)
//		{
//			double fraction = ((double)i)/((double)div);
//			vstrokes.add(PointPair.vline(fraction));
//		}
//		for(int h = 0;h<hstrokes.size();h++)
//		{
//			for(int v =0; v<vstrokes.size();v++)
//			{
//				new LabelBox("#",hstrokes.get(h).intersects(vstrokes.get(v)),gridFormat).sketch(sequence, 1d);
//			}
//		}
		common.stop();
		
		Title title = new Title();
		common.show(title);
		title.setText(sequence,"Visible Geek: Mp3 Dream #1");
		
		
		Placeholder visible = new Placeholder("Visible Man",new Point(1200d,300d),300d,500d);
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

	private void addHoldersFor(ArrayList<Placeholder> holders, int i)
	{
		holders.add(makeHolder(PointPair.north(i)));
		if(i==0) return;
		holders.add(makeHolder(PointPair.northeast(i)));
		holders.add(makeHolder(PointPair.south(i)));
		holders.add(makeHolder(PointPair.southwest(i)));
		holders.add(makeHolder(PointPair.northwest(i)));
		holders.add(makeHolder(PointPair.southeast(i)));
		holders.add(makeHolder(PointPair.east(i)));
		holders.add(makeHolder(PointPair.west(i)));
		holders.add(makeHolder(PointPair.westsouthwest(i)));
		holders.add(makeHolder(PointPair.northnortheast(i)));
	}
	
	private Placeholder makeHolder(Point pair)
	{
		return new Placeholder("#",pair,25d,25d);
	}

}

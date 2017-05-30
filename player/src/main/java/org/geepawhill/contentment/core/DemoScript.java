package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Placeholder;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Grid;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.paint.Color;

public class DemoScript
{
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	private Format main = new Format( TypeFace.color("main", Color.YELLOW, .8d), TypeFace.largeHand());

	private CommonSteps common;

	private Grid grid;

	public DemoScript(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.grid = new Grid(8,4,50d,50d);
	}

	public void add()
	{
		Title title = new Title();
		common.show(title);
		title.setText(sequence,"Demo Script");
		common.stop();
		
		title.setText(sequence, "This is a title!");
		common.stop();
		
		Format firstFormat = new Format(TypeFace.largeHand(),TypeFace.color("", Color.RED, 1d));
		Letters theseAreLetters = new Letters("These are letters.",new Point(800d,450d),firstFormat);
		theseAreLetters.fadeIn(sequence, 1d);
		common.stop();
		
		Format secondFormat = new Format(firstFormat,TypeFace.color("", Color.GREEN, 1d));
		Letters fadedIn = new Letters("They can fade in.",new Point(800d, 500d),secondFormat);
		fadedIn.fadeIn(sequence, 500d);
		common.stop();
		
		Format thirdFormat = new Format(firstFormat,TypeFace.color("", Color.BLUE, 1d));
		Letters sketchedIn = new Letters("They can sketch in.",new Point(800d, 550d),thirdFormat);
		sketchedIn.sketch(sequence, new FixedTiming(1000d));
		
		Format fourthFormat = new Format(TypeFace.mediumSans(),TypeFace.color("", Color.WHITE, 1d));
		Letters altFont = new Letters("Letters have font.",new Point(800d,600d),fourthFormat);
		altFont.fadeIn(sequence, 1d);
		
		Format fifthFormat = new Format(TypeFace.mediumSans(),TypeFace.color("", Color.WHITE, .3d));
		Letters altOpacity = new Letters("And opacity",new Point(800d,650d),fifthFormat);
		altOpacity.fadeIn(sequence, 1d);
		
		common.stop();
		
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



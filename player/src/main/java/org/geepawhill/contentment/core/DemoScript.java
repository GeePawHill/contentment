package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.LabelBox;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.paint.Color;

public class DemoScript
{
	private Sequence sequence;
	
	private CommonSteps common;

	private Format firstFormat;

	private Format secondFormat;

	private Format thirdFormat;

	private Format fourthFormat;

	public DemoScript(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		firstFormat = new Format(TypeFace.largeHand(),TypeFace.color("", Color.RED, 1d),Frames.frame("",Color.RED,5d,1d),Dash.solid());
		secondFormat = new Format(firstFormat,TypeFace.color("", Color.GREEN, 1d),Frames.frame("",Color.GREEN,5d,1d));
		thirdFormat = new Format(firstFormat,TypeFace.color("", Color.BLUE, 1d),Frames.frame("",Color.BLUE,5d,1d));
		fourthFormat = new Format(TypeFace.mediumSans(),TypeFace.color("", Color.WHITE, 1d));
	}

	public void add()
	{
		scene1();
		scene2();
	}

	private void scene1()
	{
		Title title = new Title();
		sequence.add(title.flash());
		sequence.add(title.change("Demo Script"));
//		common.stop();
		
		sequence.add(title.change("This is a title!"));
//		common.stop();
		
		Letters theseAreLetters = new Letters("These are letters.",new Point(800d,450d),firstFormat);
		theseAreLetters.fadeIn(sequence, 1d);
	//	common.stop();
		
		Letters fadedIn = new Letters("They can fade in.",new Point(800d, 500d),secondFormat);
		fadedIn.fadeIn(sequence, 500d);
//		common.stop();
		
		Letters sketchedIn = new Letters("They can sketch in.",new Point(800d, 550d),thirdFormat);
		sketchedIn.sketch(sequence, new FixedTiming(1000d));
//		common.stop();
		
		Letters altFont = new Letters("Letters have font.",new Point(800d,600d),fourthFormat);
		altFont.fadeIn(sequence, 1d);
		
		Format fifthFormat = new Format(TypeFace.mediumSans(),TypeFace.color("", Color.WHITE, .3d));
		Letters altOpacity = new Letters("And opacity",new Point(800d,650d),fifthFormat);
		altOpacity.fadeIn(sequence, 1d);
		common.cue();
		common.clear();
	}
	
	private void scene2()
	{
		Title title = new Title();
		sequence.add(title.flash());
		sequence.add(title.change("There are other actors."));
		common.cue();
		
		LabelBox box = new LabelBox(LabelBox.class.getSimpleName(),new Point(400d,300d),firstFormat);
		box.sketch(sequence, 2000d);
		common.cue();
		
		OvalText oval = new OvalText(OvalText.class.getSimpleName(),new Point(800d,300d),secondFormat);
		oval.sketch(sequence, 1d);
		common.cue();
		
		Arrow arrow = new Arrow(box,false,oval,true,thirdFormat);
		arrow.sketch(sequence, 1000d);
		common.cue();

	}
}



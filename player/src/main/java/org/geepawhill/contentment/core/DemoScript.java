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
		firstFormat = new Format(TypeFace.largeHand(), TypeFace.color("", Color.RED, 1d), Frames.frame("", Color.RED, 5d, 1d),
				Dash.solid());
		secondFormat = new Format(firstFormat, TypeFace.color("", Color.GREEN, 1d), Frames.frame("", Color.GREEN, 5d, 1d));
		thirdFormat = new Format(firstFormat, TypeFace.color("", Color.BLUE, 1d), Frames.frame("", Color.BLUE, 5d, 1d));
		fourthFormat = new Format(TypeFace.mediumSans(), TypeFace.color("", Color.YELLOW, 1d));
	}

	public void add()
	{
		scene1();
		scene2();
	}

	private void scene1()
	{
		Title title = new Title();
		common.flash(title);
		sequence.add(title.change("This is a title!"));
		common.cue();

		sequence.add(title.change("Its text can change."));
		common.cue();

		Letters theseAreLetters = new Letters("These are letters.", new Point(800d, 450d), firstFormat);
		common.flash(theseAreLetters);

		Letters fadedIn = new Letters("They can fade in.", new Point(800d, 500d), secondFormat);
		common.fadeIn(500d, fadedIn);

		Letters sketchedIn = new Letters("They can sketch in.", new Point(800d, 550d), thirdFormat);
		sketchedIn.sketch(sequence, new FixedTiming(1000d));

		Letters altFont = new Letters("Letters have font.", new Point(800d, 600d), fourthFormat);
		common.fadeIn(500d,altFont);

		Format fifthFormat = new Format(TypeFace.mediumSans(), TypeFace.color("", Color.YELLOW, .3d));
		Letters altOpacity = new Letters("And opacity", new Point(800d, 650d), fifthFormat);
		common.fadeIn(500d,altOpacity);
		common.cue();
		common.clear();
	}

	private void scene2()
	{
		Title title = new Title();
		sequence.add(title.flash());
		sequence.add(title.change("There are other actors."));

		LabelBox box1 = new LabelBox(LabelBox.class.getSimpleName(), new Point(400d, 300d), firstFormat);
		common.sketch(1000d, box1);

		LabelBox box2 = new LabelBox(LabelBox.class.getSimpleName(), new Point(800d, 300d), firstFormat);
		common.fadeIn(500d, box2);
		
		LabelBox box3 = new LabelBox(LabelBox.class.getSimpleName(), new Point(1200d, 300d), firstFormat);
		common.flash(box3);

		OvalText oval1 = new OvalText(OvalText.class.getSimpleName(), new Point(400d, 500d), secondFormat);
		common.sketch(1000d, oval1);

		OvalText oval2 = new OvalText(OvalText.class.getSimpleName(), new Point(800d, 500d), secondFormat);
		common.fadeIn(500d, oval2);
		
		OvalText oval3 = new OvalText(OvalText.class.getSimpleName(), new Point(1200d, 500d), secondFormat);
		common.flash(oval3);

		Arrow arrow1 = new Arrow(box1, true, oval1, true, thirdFormat);
		common.sketch(1000d, arrow1);

		Arrow arrow2 = new Arrow(box2, true, oval2, true, thirdFormat);
		common.fadeIn(500d, arrow2);
		
		Arrow arrow3 = new Arrow(box3, true, oval3, true, thirdFormat);
		common.flash(arrow3);

		common.cue();
	}
}

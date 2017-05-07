package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.Placeholder;
import org.geepawhill.contentment.actor.Slide;
import org.geepawhill.contentment.actor.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;

public class ResponsesToComplexity
{
	
	private static final double VQUARTER = 1080d/4d;
	
	private Sequence sequence;
	
	private Format main = new Format( TypeFace.color("main", Color.YELLOW, .8d), TypeFace.largeHand());

	private CommonSteps common;
	
	String[] slide1 = {
			"Who Is This Guy?",
			"+GeePaw, Mike, Michael, Hill",
			"++geepawhill @ geepawhill.org",
			"++@geepawhill",
			"++sayat.me/geepawhill",
			"+Backstory",
			"++geek for 35 years, coach for 18 years",
			"++i’m “GeePaw” because of so many grandkids",
			"++Object Mentor, Industrial Logic, Independent",
			"++mostly VBCA’s nowadays, ones you know",
	};

	String[] slide2 = {
			"The Flock & The Three Flows",
			"+Valued Results",
			"++where are we going?",
			"+Geek Joy",
			"++how will we get there?",
			"+Courageous Curiosity",
			"++where are we now?",
			"=Software Development Excellence",
			"=(Suspect All Systems)"
	};
	
	String[] slide3 = {
	"Complexity Blah-Blah-Blah",
	"+There’s Tons Of Theory",
	"++non-linear dynamics, chaos theory (math)",
	"++complex adaptive systems (systems theory)",
	"++ecology, organism (biology)",
	"++the mangle (science studies)",
	"++anything & everything (history)",
	"+We Need *Practice*",
	"++actual direct advice",
	"++a pause in the flavor wars",
	"++no rulesets or drop-in systems",
	"++forces & relationships",
	"+And We Already Have Some",
	"++we can see the entire agile movement as a response",
	"++find the parts of agility that work",
	"++try to add new and different parts"
	};


	
	public ResponsesToComplexity(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		common.clear();
		Slide slide = new Slide();
		slide.show(sequence,slide1);
		common.stop();
		slide.flip(sequence,slide2);
		common.stop();
		slide.flip(sequence,slide3);
	}
	
}

package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Arrow;
import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.OvalText;
import org.geepawhill.contentment.actor.Slide;
import org.geepawhill.contentment.actors.ClipArt;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ResponsesToComplexity
{

	private Sequence sequence;

	private CommonSteps common;

	private Slide slideActor;

	String[][] geepaw =
	{
			{
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
			},
	};

	String[][] flockAndFlows =
	{
			{
					"The Flock & The Three Flows",
					"+Valued Results",
					"++where are we going?",
					"+Geek Joy",
					"++how will we get there?",
					"+Courageous Curiosity",
					"++where are we now?",
					"=Software Development Excellence",
					"=(Suspect All Systems)"
			},
	};

	String[][] complexity =
	{
			{
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
			},
			{
					"Suspect All Systems!",
					"++many forces compel us to systematize",
					"+++worked twice isn’t works",
					"+++worked for them isn’t works",
					"+++worked as acronym isn’t works",
					"+++worked on powerpoint isn’t works",
					"+++worked in resume isn’t works",
					"+++what is working is working",
					"++assume every practice must prove itself in practice",
					"+++assert standards as “problems to solve” not “solutions to solve them”",
					"+++change *anything*  that isn’t working, and some things that are",
					"+++argue a little, experiment a lot more",
					"+++plausibility -> practice -> success -> acceptance",
			},
			{
					"The Stock Metaphor: Plan This Trip",
					"+We have...",
					"+++... a US road atlas",
					"+++... a beater car",
					"+++... two mechanics",
					"+++... four drivers",
					"+++... three bosses",
					"+++... five hundred dollars",
					"+The Challenge",
					"++We need to go from SF to NY in 15 days",
					"++Plot a course",
					"++Estimate every waypoint within a day",
					"++Get to New York!",
			},
			{
					"The Map Exploded",
					"+A Hard Problem, But Not A Complex One",
					"++the resource list is stable",
					"++the locations are stable",
					"+++SF",
					"+++NY",
					"+++every stop in between",
					"++number & quality of roads",
					"+(Not Included In Photo)",
					"++that problem is too short",
					"++mechanics can't fix everything",
					"++the rules are often a huge problem",
			},
			{
					"The Broadest Pattern",
					"+Move Then Sense",
					"++you are in a wilderness",
					"++you simply cannot see far enough",
					"+++it’s not that it’s hard",
					"+++it’s not that you’re weak",
					"+++it’s not that you need more time",
					"++it is not possible",
					"++replace “try harder” with “try different”",
					"=The Only Possible Approach:",
					"=change something",
					"=see what happens",
			}
	};

	String[][] collaboration =
	{
			{
					"Optimize For Collaboration",
					"+We Need Ties For Stability",
					"++metaphor: star network vs distributed network",
					"++humans – alone – don’t handle this all that well",
					"++humans – together – provide stability & mutual support",
					"+We Need ‘Juice’",
					"++no one human is on all the time",
					"++accepting peaks and valleys work well for us",
					"++some synchronization happens, but the more we are, the less it stops the show",
					"+We Are In Constant Need Of Ideas",
					"++most ideas come from domain-bridging",
					"+++we map from some other domain into our system",
					"+++A is to B as A’ is to B’",
					"++more domains means more ideas",
					"++more people in play means more domains",
			},
			{
					"Collaboration: Praxis",
					"++frequent focused direct dialog (F2D2)",
					"++plenty & varied space, noisy & quiet, large & small",
					"++inclusion",
			},
			{
					"Collaboration: Contra",
					"++“standard” meetings: waiting to start, all-hands, status reporting",
					"++no unprotected hours",
					"++F2D2 substitutes: email, templated documents, tracking systems",
					"++conflict suppression",
					"++forced engagement",
			},
			{
					"Collaboration: Indicators",
					"++buzz",
					"++whiteboard churn",
					"++time to breathe",
					"++cheerful disputation",
			}
	};

	String[][] change =
	{
			{
					"Optimize For Change",
					"+The Change of Change-Then-See",
					"++*embrace* change, don't block it",
					"++emphasize maneuverability",
					"++emphasize constant reset",
					"+Avoid Committed Paths",
					"++how trustworthy is what i’m standing on?",
					"++how easy is it for me to stand somewhere else?",
					"++how many paths are there out of here?",
					"+Perpetual Freshness",
					"++in a greenfield world, we’re always free to act",
					"++in a pre-committed world, we are often self-constrained",
					"++local change-control structures slide easily towards global ones",
					"++change for change's sake? well. yes."
			},
			{
					"Change: Praxis",
					"++embrace rework",
					"++commit to experimentation",
					"++WIP policing – quick & easy blocking",
					"++multi-exit step choices",
					"++express standards as problems, not solutions",
			},
			{
					"Change: Contra",
					"++responsibility/authority mismatching",
					"++unanimity requirement",
					"++premature decision-making",
					"++parallel development as usually practiced today",
					"++missing decision-makers",

			},
			{
					"Change: Indications",
					"++the sense of interruptedness",
					"++frequency: pushes, deploys, releases",
					"++the geepaw guarantee",
			},
	};

	String[][] seeing1 =
	{
			{
					"Optimize For Seeing",
					"++even the cartoon understates this",
					"+++number of agents",
					"+++range of change",
					"++Obvious Areas",
					"+++the code",
					"+++the team",
					"+++the periphery",
					"+++the users",
					"+++the process",
			},
	};

	String[][] seeing2 =
	{
			{
					"Seeing: Praxis",
					"++retrospectives",
					"++actual users",
					"++continuous – integration, deployment, release",
					"++same room",

			},
			{
					"Seeing: Contra",
					"++preferencing numerics, especially velocity",
					"++“fat release” policy",
					"++conflict suppression",
					"++external QA",

			},
			{
					"Seeing: Indications",
					"++consensus in description",
					"++ferocious rigor against flickers & build breaks",
					"++surprise in relationships",
			}
	};

	String[][] conclusion =
	{
			{
					"It's Clear: The Way Forward Is Unclear",
					"++Change-Then-See Everywhere",
					"+++code, tests, process, relationships",
					"+++experiment as a way of life",
					"+++reset frequently & cheerfully",
					" ",
					"=optimize for collaboration",
					"=optimize for motion",
					"=optimize for sensing",
			}
	};

	private OvalText[] ovals;

	private Format lineFormat;

	public ResponsesToComplexity(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		Format labelFormat = new Format(TypeFace.mediumHand(), TypeFace.color("", Color.LIGHTBLUE, 1d));
		Format smallLabelFormat = new Format(TypeFace.smallHand(), TypeFace.color("", Color.GREEN, 1d));
		lineFormat = new Format(Frames.frame("", Color.AQUAMARINE, 2d, 1d), Dash.dash("", 3d));
		Format ovalFormat = new Format(TypeFace.largeHand(), TypeFace.color("", Color.RED, 1d), Dash.solid(),
				Frames.frame("", Color.RED, 3d, 1d));
		common.clear();
		Image image = new Image("/org/geepawhill/scripts/usOutline.png", 1500d, 800d, true, true);
		ClipArt art = new ClipArt(image, new PointPair(150d, 50d, 1550d, 850d));
		art.flip(sequence);
		Image redBall = new Image("/org/geepawhill/scripts/redBall.png", 50d, 50d, true, true);
		ClipArt sf = new ClipArt(redBall, new PointPair(200d, 400d, 0d, 0d));
		Letters sfLetters = new Letters("San Francisco", new Point(245d, 390d), labelFormat);
		sfLetters.sketch(sequence, FixedTiming.INSTANT);
		sf.flip(sequence);
		ClipArt ny = new ClipArt(redBall, new PointPair(1280d, 200d, 0d, 0d));
		Letters nyLetters = new Letters("New York", new Point(1300d, 200d), labelFormat);
		nyLetters.sketch(sequence, FixedTiming.INSTANT);
		ny.flip(sequence);
		common.stop();

		Image blueBall = new Image("/org/geepawhill/scripts/blueBall.png", 25d, 25d, true, true);
		ClipArt reno = new ClipArt(blueBall, new PointPair(320d, 360d, 0d, 0d));
		reno.flip(sequence);
		Letters renoLetters = new Letters("Reno", new Point(320d, 360d), smallLabelFormat);
		renoLetters.sketch(sequence, FixedTiming.INSTANT);
		reno.flip(sequence);

		ClipArt lasVegas = new ClipArt(blueBall, new PointPair(340d, 420d, 0d, 0d));
		lasVegas.flip(sequence);
		Letters lasVegasLetters = new Letters("Las Vegas", new Point(340, 420d), smallLabelFormat);
		lasVegasLetters.sketch(sequence, FixedTiming.INSTANT);
		lasVegas.flip(sequence);

		ClipArt saltLake = new ClipArt(blueBall, new PointPair(440d, 400d, 0d, 0d));
		saltLake.flip(sequence);
		Letters saltLakeLetters = new Letters("Salt Lake", new Point(440, 400d), smallLabelFormat);
		saltLakeLetters.sketch(sequence, FixedTiming.INSTANT);
		saltLake.flip(sequence);

		ClipArt grandCanyon = new ClipArt(blueBall, new PointPair(410d, 480d, 0d, 0d));
		grandCanyon.flip(sequence);
		Letters grandCanyonLetters = new Letters("Grand Canyon", new Point(410d, 480d), smallLabelFormat);
		grandCanyonLetters.sketch(sequence, FixedTiming.INSTANT);
		grandCanyon.flip(sequence);
		common.stop();

		Arrow sfReno = new Arrow(sf, false, reno, true, lineFormat);
		sfReno.sketch(sequence, 1d);

		Arrow sfLasVegas = new Arrow(sf, false, lasVegas, true, lineFormat);
		sfLasVegas.sketch(sequence, 1d);

		Arrow renoSaltLake = new Arrow(reno, false, saltLake, true, lineFormat);
		renoSaltLake.sketch(sequence, 1d);

		Arrow lasVegasGrandCanyon = new Arrow(lasVegas, false, grandCanyon, true, lineFormat);
		lasVegasGrandCanyon.sketch(sequence, 1d);

		Arrow lasVegasSaltLake = new Arrow(lasVegas, false, saltLake, true, lineFormat);
		lasVegasSaltLake.sketch(sequence, 1d);
		common.stop();
		common.clear();

		Point points[] =
		{
				new Point(220d, 400d),
				new Point(375d, 608d),
				new Point(510d, 208d),
				new Point(570d, 448d),
				new Point(810d, 224d),
				new Point(600d, 688d),
				new Point(860d, 668d),
				new Point(1020d, 400d),
				new Point(1275d, 256d),
				new Point(1120d, 608d),
				new Point(1370d, 588d),
				// new Point(1000d,200d),
				// new Point(910d,440d),
				// new Point(1100d,300d),
				// new Point(1130d,420d),
				new Point(1500d, 352d),
		};

		ovals = new OvalText[points.length];

		for (int i = 0; i < points.length; i++)
		{
			ovals[i] = new OvalText("" + i, points[i], ovalFormat);
			ovals[i].sketch(sequence, 1d);
		}

		arrow(0, 1);
		arrow(0, 2);
		Arrow a0_3 = arrow(0, 3);
		arrow(1, 5);
		arrow(2, 3);
		arrow(2, 4);
		Arrow a3_7 = arrow(3, 7);
		arrow(4, 7);
		arrow(6, 9);
		arrow(6, 7);
		arrow(5, 6);
		Arrow a7_8 = arrow(7, 8);
		arrow(9, 10);
		arrow(10, 11);
		Arrow a8_11 = arrow(8, 11);

		common.stop();
		Paint highlight = Color.GOLD;
		common.reColor(ovals[0], highlight);
		common.reColor(a0_3, highlight);
		common.reColor(ovals[3], highlight);
		common.reColor(a3_7, highlight);
		common.reColor(ovals[7], highlight);
		common.reColor(a7_8, highlight);
		common.reColor(ovals[8], highlight);
		common.reColor(a8_11, highlight);
		common.reColor(ovals[11], highlight);

		common.stop();

	}

	private Arrow arrow(int from, int to)
	{
		Arrow arrow = new Arrow(ovals[from], false, ovals[to], true, lineFormat);
		arrow.sketch(sequence, 1d);
		return arrow;
	}

	public void showBlock(String[][] blocks)
	{
		for (String[] slide : blocks)
		{
			slideActor.show(sequence, slide);
			common.stop();
		}
	}

}

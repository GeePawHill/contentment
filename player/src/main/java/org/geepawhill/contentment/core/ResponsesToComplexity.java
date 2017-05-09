package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Slide;
import org.geepawhill.contentment.step.CommonSteps;

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
	
	String[][] block1 = 
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
					"+And We Already Have Some",
					"++we can see the entire agile movement as a response",
					"++find the parts of agility that work",
					"++try to add new and different parts"
			},
			{
					"Three Hard Ideas",
					"+Intricacy Is Not Complexity",
					"++not just pool with more balls",
					"+++these balls have agency",
					"+++these pockets move unpredictably",
					"+++this table changes its shape",
					"+The Predictability Knee",
					"++linear vs non-linear",
					"++distance to horizon",
					"++eventual positive result isn’t guaranteed",
					"+Step Tree vs Step Sequence",
					"++the landscape moves *fast*",
					"++we must genuinely re-assess after every single step",
			},
			{
					"The Hardest Sale",
					"+Code Is Not Coding Is Not Software Development",
					"++code is intricate",
					"++coding is damned hard",
					"++s/w development is still *harder*",
					" ",
					"=in professional s/w development: ",
					"=all-in, all-the-time, all-around, complexity",
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
					"=take a step and look around you to see what changes",
			}
	};

	String[][] block2 =
	{
			{
					"Optimize For Collaboration",
					"+We Are In Constant Need Of Ideas",
					"++most ideas come from domain-bridging",
					"+++we map from some other domain into our system",
					"+++A is to B as A’ is to B’",
					"++more domains means more ideas",
					"++more people in play means more domains",
					"+We Need Ties For Stability",
					"++metaphor: star network vs distributed network",
					"++humans – alone – don’t handle this all that well",
					"++humans – together – provide stability & mutual support",
					"+We Need ‘Juice’",
					"++no one human is on all the time",
					"++accepting peaks and valleys work well for us",
					"++some synchronization happens, but the more we are, the less it stops the show",
			},
			{
					"Collaboration: Praxis",
					"++frequent focused direct dialog (F2D2)",
					"++plenty & varied space, noisy & quiet, large & small",
					"++inclusion",
			},
			{
					"Collaboration: Contra",
					"++/“standard/” meetings: waiting to start, all-hands, status reporting",
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

	String[][] block3 =
	{
			{
					"Optimize For Motion",
					"+The Move of ‘Move Then Sense’",
					"++one way to say it: maneuverability",
					"+++how quickly can we change our mind",
					"+++how committed are we to a given future",
					"+Factors In Motion",
					"++how trustworthy is what i’m standing on?",
					"++how easy is it for me to stand somewhere else?",
					"++how many paths are there out of here?",
					"+We Need Perpetual Freshness",
					"++in a greenfield world, we’re always free to act",
					"++in a pre-committed world, we are often self-constrained",
					"++local change-control structures slide easily towards global ones",
			},
			{
					"Motion: Praxis",
					"+embrace rework",
					"+commit to experimentation",
					"+WIP policing – quick & easy blocking",
					"+multi-exit step choices",
					"+express standards as problems, not solutions",
			},
			{
					"Motion: Contra",
					"++responsibility/authority mismatching",
					"++unanimity requirement",
					"++premature decision-making",

			},
			{
					"Motion: Indications",
					"++the sense of interruptedness",
					"++frequency: pushes, deploys, releases",
					"++the geepaw guarantee",
			},
	};

	String[][] block4 =
	{
			{
					"Optimize For Sensing",
					"+The Landscape Changes Under Us",
					"+even the cartoon understates this",
					"+we habitually understate: ",
					"+number of agents",
					"+range of change",
					"+Obvious Areas",
					"+the code",
					"+the team",
					"+the periphery",
					"+the users",
					"+the process",
			},
			{
					"Sensing: Praxis",
					"+retrospectives",
					"+actual users",
					"+continuous – integration, deployment, release",
					"+same room",

			},
			{
					"Sensing: Contra",
					"+preferencing numerics, especially velocity",
					"+“fat release” policy",
					"+conflict suppression",
					"+external QA",

			},
			{
					"Sensing: Indications",
					"+consensus in description",
					"+ferocious rigor against flickers & build breaks",
					"+surprise in relationships",
			}
	};

	String[][] conclusion =
	{
			{
					"It's Clear: The Way Forward Is Unclear",
					"+Move-Then-Sense Everywhere",
					"+code, tests, process, relationships",
					"+experiment as a way of life",
					"+change for the sake of change?",
					"=optimize for collaboration",
					"=optimize for motion",
					"=optimize for sensing",
			}
	};

	public ResponsesToComplexity(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		common.clear();
		slideActor = new Slide();
		showBlock(geepaw);
		showBlock(flockAndFlows);
		showBlock(block1);
		showBlock(block2);
		showBlock(block3);
		showBlock(block4);
		showBlock(conclusion);
		showBlock(flockAndFlows);
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

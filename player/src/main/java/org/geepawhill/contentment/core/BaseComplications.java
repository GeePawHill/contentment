package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.LabelBox;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;

public class BaseComplications
{
	
	private Sequence sequence;
	private CommonSteps common;
	SharedChangingThings shared;
	
	public BaseComplications(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.shared = new SharedChangingThings();
	}

	public void add()
	{
		Title tale = new Title();
		LabelBox agent = new LabelBox("Agent", new Point(800d, 450d), shared.agentFormat());
		OvalText practice = new OvalText("Practice", new Point(800d, .75 * 900d), shared.practiceFormat());
		Arrow change = new Arrow(agent, false, practice, true, shared.changeFormat());
		LabelBox coach = new LabelBox("Coach", new Point(800d, 200d), shared.coachFormat());
		Arrow poke = new Arrow(coach, false, agent, true, shared.pokeFormat());

		LabelBox a1 = new LabelBox("A", new Point(200d, 200d), shared.agentFormat());
		LabelBox a2 = new LabelBox("A", new Point(400d, 300d), shared.agentFormat());
		LabelBox a3 = new LabelBox("A", new Point(600d, 400d), shared.agentFormat());
		LabelBox a4 = new LabelBox("A", new Point(1000d, 400d), shared.agentFormat());
		LabelBox a5 = new LabelBox("A", new Point(1200d, 300d), shared.agentFormat());
		LabelBox a6 = new LabelBox("A", new Point(1400d, 200d), shared.agentFormat());

		Arrow poke1 = new Arrow(coach, false, a1, true, shared.pokeFormat());
		Arrow poke2 = new Arrow(coach, false, a2, true, shared.pokeFormat());
		Arrow poke3 = new Arrow(coach, false, a3, true, shared.pokeFormat());
		Arrow poke4 = new Arrow(coach, false, a4, true, shared.pokeFormat());
		Arrow poke5 = new Arrow(coach, false, a5, true, shared.pokeFormat());
		Arrow poke6 = new Arrow(coach, false, a6, true, shared.pokeFormat());

		OvalText p1 = new OvalText("P", new Point(200d, 400d), shared.practiceFormat());
		OvalText p2 = new OvalText("P", new Point(400d, 500d), shared.practiceFormat());
		OvalText p3 = new OvalText("P", new Point(600d, 600d), shared.practiceFormat());
		OvalText p4 = new OvalText("P", new Point(1000d, 600d), shared.practiceFormat());
		OvalText p5 = new OvalText("P", new Point(1200d, 500d), shared.practiceFormat());
		OvalText p6 = new OvalText("P", new Point(1400d, 400d), shared.practiceFormat());

		Arrow c1 = new Arrow(a1, false, p1, true, shared.changeFormat());
		Arrow c2 = new Arrow(a2, false, p2, true, shared.changeFormat());
		Arrow c3 = new Arrow(a3, false, p3, true, shared.changeFormat());
		Arrow c4 = new Arrow(a4, false, p4, true, shared.changeFormat());
		Arrow c5 = new Arrow(a5, false, p5, true, shared.changeFormat());
		Arrow c6 = new Arrow(a6, false, p6, true, shared.changeFormat());

		Arrow c11 = new Arrow(a1, false, p2, true, shared.changeFormat());
		Arrow c12 = new Arrow(a2, false, p3, true, shared.changeFormat());
		Arrow c13 = new Arrow(a3, false, practice, true, shared.changeFormat());
		Arrow c14 = new Arrow(a4, false, p5, true, shared.changeFormat());
		Arrow c15 = new Arrow(a5, false, p6, true, shared.changeFormat());

		Arrow c22 = new Arrow(a2, false, p1, true, shared.changeFormat());
		Arrow c23 = new Arrow(a3, false, p2, true, shared.changeFormat());
		Arrow c24 = new Arrow(a4, false, practice, true, shared.changeFormat());
		Arrow c25 = new Arrow(a5, false, p4, true, shared.changeFormat());
		Arrow c26 = new Arrow(a6, false, p5, true, shared.changeFormat());

		Arrow i1 = new Arrow(a1, false, a2, false, shared.relationFormat());
		Arrow i2 = new Arrow(a2, false, a3, false, shared.relationFormat());
		Arrow i3 = new Arrow(a3, false, agent, false, shared.relationFormat());
		Arrow i4 = new Arrow(agent, false, a4, false, shared.relationFormat());
		Arrow i5 = new Arrow(a4, false, a5, false, shared.relationFormat());
		Arrow i6 = new Arrow(a5, false, a6, false, shared.relationFormat());

		Arrow pi1 = new Arrow(p1, false, p2, false, shared.relationFormat());
		Arrow pi2 = new Arrow(p2, false, p3, false, shared.relationFormat());
		Arrow pi3 = new Arrow(p3, false, practice, false, shared.relationFormat());
		Arrow pi4 = new Arrow(practice, false, p4, false, shared.relationFormat());
		Arrow pi5 = new Arrow(p4, false, p5, false, shared.relationFormat());
		Arrow pi6 = new Arrow(p5, false, p6, false, shared.relationFormat());

		sequence.add(tale.flash());
		sequence.add(tale.change("A Simple Change Model"));
		agent.sketch(sequence, 1000d);
		coach.sketch(sequence, 1000d);
		common.stop();
		poke.sketch(sequence, 1000d);
		practice.sketch(sequence, 1000d);
		change.sketch(sequence, 1000d);
		common.stop();
		sequence.add(tale.change("Complication: There are always multiple agents."));
		a1.sketch(sequence, 1d);
		a2.sketch(sequence, 1d);
		a3.sketch(sequence, 1d);
		a4.sketch(sequence, 1d);
		a5.sketch(sequence, 1d);
		a6.sketch(sequence, 1d);
		poke1.sketch(sequence, 1d);
		poke2.sketch(sequence, 1d);
		poke3.sketch(sequence, 1d);
		poke4.sketch(sequence, 1d);
		poke5.sketch(sequence, 1d);
		poke6.sketch(sequence, 1d);
		common.stop();
		sequence.add(tale.change("Complication: There are always multiple practices."));
		p1.sketch(sequence, 1d);
		p2.sketch(sequence, 1d);
		p3.sketch(sequence, 1d);
		p4.sketch(sequence, 1d);
		p5.sketch(sequence, 1d);
		p6.sketch(sequence, 1d);
		c1.sketch(sequence, 1d);
		c2.sketch(sequence, 1d);
		c3.sketch(sequence, 1d);
		c4.sketch(sequence, 1d);
		c5.sketch(sequence, 1d);
		c6.sketch(sequence, 1d);
		common.stop();

		sequence.add(tale.change("Complication: Most agents change multiple practices."));
		c12.sketch(sequence, 200d);
		c22.sketch(sequence, 200d);
		common.stop();
		c11.sketch(sequence, 1d);
		c13.sketch(sequence, 1d);
		c14.sketch(sequence, 1d);
		c15.sketch(sequence, 1d);
		c23.sketch(sequence, 1d);
		c24.sketch(sequence, 1d);
		c25.sketch(sequence, 1d);
		c26.sketch(sequence, 1d);
		common.stop();

		sequence.add(tale.change("Complication: The agents are interrelated."));
		i1.sketch(sequence, 1000d);
		common.stop();
		i2.sketch(sequence, 1d);
		i3.sketch(sequence, 1d);
		i4.sketch(sequence, 1d);
		i5.sketch(sequence, 1d);
		i6.sketch(sequence, 1d);
		common.stop();
		sequence.add(tale.change("Complication: The practices are interrelated."));
		pi1.sketch(sequence, 1000d);
		common.stop();
		pi2.sketch(sequence, 1d);
		pi3.sketch(sequence, 1d);
		pi4.sketch(sequence, 1d);
		pi5.sketch(sequence, 1d);
		pi6.sketch(sequence, 1d);
		common.stop();
	}


}

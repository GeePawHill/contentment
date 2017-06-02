package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.LabelBox;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;

public class AgentAndPokes
{
	private Sequence sequence;
	private SharedChangingThings shared;
	private CommonSteps common;

	public AgentAndPokes(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.shared = new SharedChangingThings();
	}

	public void add()
	{
		Title tale = new Title();
		LabelBox agent = new LabelBox("Agent", new Point(800d, 520d), shared.agentFormat());

		Letters teammate = new Letters("Teammate", new Point(800d, 520d), shared.agentFormat());
		Letters practice = new Letters("Practice", new Point(800d, 800d), shared.agentFormat());
		Letters coach = new Letters("Coach", new Point(800d, 275d), shared.agentFormat());
		Letters software = new Letters("Software", new Point(500d, 300d), shared.agentFormat());
		Letters hardware = new Letters("Hardware", new Point(1300d, 600d), shared.agentFormat());
		Letters policy = new Letters("Policy", new Point(1100d, 300d), shared.agentFormat());
		Letters personnel = new Letters("Staff", new Point(500d, 750d), shared.agentFormat());
		Letters date = new Letters("Date", new Point(1300d, 450d), shared.agentFormat());
		Letters framework = new Letters("Framework", new Point(300d, 600d), shared.agentFormat());
		Letters tools = new Letters("Tools", new Point(300d, 450d), shared.agentFormat());
		Letters orgchart = new Letters("Org Chart", new Point(1100d, 750d), shared.agentFormat());

		common.clear();
		common.appear(tale);
		sequence.add(tale.change("Agent: Anything With Susceptability & Unpredictability"));
		common.appear(agent);
		common.cue();
		common.disappear(agent);
		common.fadeIn(500d, teammate);
		common.cue();
		sequence.add(tale.change("That Includes The Daily Practice"));
		common.fadeIn(300d, practice);
		common.cue();
		sequence.add(tale.change("Software We Use For Making"));
		common.fadeIn(300d, software);
		common.cue();
		sequence.add(tale.change("Or The Hardware We Use For Making"));
		common.fadeIn(300d, hardware);
		common.cue();
		sequence.add(tale.change("Corporate Policy Is An Agent"));
		common.fadeIn(300d, policy);
		common.cue();
		sequence.add(tale.change("The Staffing Demand & Supply"));
		common.fadeIn(300d, personnel);
		common.cue();
		sequence.add(tale.change("Everything About The Market, Like Shipping Date"));
		common.fadeIn(300d, date);
		common.cue();
		sequence.add(tale.change("The Conceptual Framework The Team Uses"));
		common.fadeIn(300d, framework);
		common.cue();
		sequence.add(tale.change("The Tools We Use"));
		common.fadeIn(300d, tools);
		common.cue();
		sequence.add(tale.change("The Hierarchy We Live In"));
		common.fadeIn(300d, orgchart);
		common.cue();
		sequence.add(tale.change("And, Yes, The Coach Is An Agent, Too"));
		common.fadeIn(300d, coach);
		common.cue();
		sequence.add(tale.change("Watch Just One Poke..."));
		common.cue();
		poke(coach, teammate, 850d, 570d);
		poke(teammate, practice, 750d, 820d);
		poke(practice, software, 480d, 310d);
		poke(teammate, practice, 820d, 840d);
		poke(practice, policy, 1110d, 320d);
		poke(policy, date, 1300d, 500d);
		poke(date, personnel, 450d, 770d);
		poke(personnel, orgchart, 1110d, 745d);
		poke(personnel, practice, 820d, 800d);
		poke(practice, framework, 250d, 620d);
		poke(framework, teammate, 830d, 550d);
		poke(teammate, date, 1300d, 450d);
	}

	private void poke(Letters from, Letters to, double newX, double newY)
	{
		Arrow arrow = new Arrow(from, false, to, true, shared.pokeFormat());
		common.sketch(400d, arrow);
		to.move(sequence, newX, newY);
		common.disappear(arrow);
	}

}

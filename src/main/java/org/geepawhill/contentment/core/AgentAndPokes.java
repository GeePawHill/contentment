package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Arrow;
import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.Title;
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
		Title tale = new Title("Agent: Anything With Susceptability & Unpredictability");
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
		common.show(tale);
		agent.sketch(sequence, 1d);
		common.stop();
		common.hide(agent);
		teammate.fadeIn(sequence, 500d);
		common.stop();
		tale.setText(sequence, "That Includes The Daily Practice");
		practice.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Software We Use For Making");
		software.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Or The Hardware We Use For Making");
		hardware.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Corporate Policy Is An Agent");
		policy.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Staffing Demand & Supply");
		personnel.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Everything About The Market, Like Shipping Date");
		date.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Conceptual Framework The Team Uses");
		framework.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Tools We Use");
		tools.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Hierarchy We Live In");
		orgchart.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "And, Yes, The Coach Is An Agent, Too");
		coach.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Watch Just One Poke...");
		common.stop();
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
		arrow.sketch(sequence, 400d);
		to.move(sequence, newX, newY);
		common.hide(arrow);
	}


}

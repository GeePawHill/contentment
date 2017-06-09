package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.LabelBox;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.TargetBox;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.step.MoveStep;

import javafx.scene.paint.Color;

public class InteractiveStabilization
{

	private Sequence sequence;
	private SharedChangingThings shared;
	private CommonSteps common;

	public InteractiveStabilization(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.shared = new SharedChangingThings();
	}
	
	public void add()
	{
		Title tale = new Title();
		LabelBox agent = new LabelBox("Agent", new Point(800d, 450d), shared.agentFormat());
		TargetBox target = new TargetBox("Target", new Point(1050d, 350d), shared.targetFormat());

		Spot poke1Source = new Spot(400d, 450d);
		Spot poke2Source = new Spot(800d, 200d);
		Spot poke3Source = new Spot(800d, 600d);

		Arrow poke1 = new Arrow(poke1Source, false, agent, true, shared.pokeFormat(Color.RED));
		Arrow poke2 = new Arrow(poke2Source, false, agent, true, shared.pokeFormat(Color.BLUE));
		Arrow poke3 = new Arrow(poke3Source, false, agent, true, shared.pokeFormat(Color.GREEN));

		common.clear();
		common.appear(tale);
		sequence.add(tale.change("Agents Are Susceptible To Pokes"));
		common.sketch( 1d,target);

		common.sketch( 1d,agent);
		common.cue();

		common.appear(poke1Source);
		common.sketch( 1000d,poke1);
		common.cue();
		sequence.add(tale.change("Agents Respond Unpredictably"));
		sequence.add(new MoveStep(1000d,agent,new Point(900d,400d)));

//		sequence.add(agent.move(900d, 400d));
		common.cue();
		sequence.add(tale.change("Whoops: Better Try Another Poke"));
		common.appear(poke2Source);
		common.sketch( 1000d,poke2);
		sequence.add(new MoveStep(1000d,agent,new Point(970d,500d)));
	
//		sequence.add(agent.move(1000d, 500d));
		common.cue();
		sequence.add(tale.change("Almost there!"));
		common.appear(poke3Source);
		common.sketch( 1000d,poke3);
		
		sequence.add(new MoveStep(1000d,agent,new Point(1100d,450d)));

//		sequence.add(agent.move(1100d, 450d));
		sequence.add(tale.change("Made it!!"));
		common.cue();

	}
}

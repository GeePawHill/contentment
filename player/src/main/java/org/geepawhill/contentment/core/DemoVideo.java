package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.ClipArt;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.step.MoveStep;

import javafx.scene.image.Image;

public class DemoVideo
{

	private CommonSteps common;

	private Sequence sequence;

	public DemoVideo(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		common.clear();
		
		Image redBall = new Image("/org/geepawhill/scripts/redBall.png", 50d, 50d, true, true);
		ClipArt ball = new ClipArt(redBall, new PointPair(50d, 50d, 0d, 0d));
		common.appear(ball);
		sequence.add(new MoveStep(1000d,ball,new Point(775d,50d)));
		common.cue();
		sequence.add(new MoveStep(1000d,ball,new Point(775d,425d)));
		common.cue();
		sequence.add(new MoveStep(1000d,ball,new Point(1525d,425d)));
		common.cue();
		sequence.add(new MoveStep(1000d,ball,new Point(1525d,850d)));
		common.cue();
		sequence.add(new MoveStep(1000d,ball,new Point(775d,850d)));
		common.cue();
	}
}

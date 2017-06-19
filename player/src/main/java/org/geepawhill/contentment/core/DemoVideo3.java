package org.geepawhill.contentment.core;

import java.net.URL;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.ViewPort;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.step.DelayStep;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;

public class DemoVideo3
{

	private CommonSteps common;

	private Sequence sequence;

	public DemoVideo3(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		Format labelFormat = new Format(TypeFace.mediumHand(), TypeFace.color(Color.LIGHTBLUE, 1d));
		common.clear();

		URL resource = getClass().getClassLoader().getResource("org/geepawhill/scripts/redball.mp4");
		sequence.add(new ChangeMediaStep(resource));
		Letters letters = new Letters("00000",ViewPort.CENTER,labelFormat);
		common.appear(letters);
		sequence.add(new DelayStep(3000d));
		URL resource2 = getClass().getClassLoader().getResource("org/geepawhill/scripts/blueball.mp4");
		sequence.add(new ChangeMediaStep(resource2));
		
	}
}

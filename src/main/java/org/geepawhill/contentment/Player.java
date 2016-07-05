package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

public class Player {
	
	Context context;
	
	public Player(Pane canvas)
	{
		context = new Context(canvas);
	}

	public void stepForward() {
		context.stepper.stepForward(context.canvas);
	}

	public void stepBackward() {
		context.stepper.stepBackward(context.canvas);
	}

	public void reset(Sequence sequence) {
		context.stepper.load(sequence, context.canvas);
	}

}

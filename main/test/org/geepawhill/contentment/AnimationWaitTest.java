package org.geepawhill.contentment;

import javafx.animation.Transition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import org.junit.Ignore;
import org.junit.Test;

public class AnimationWaitTest extends JavaFxTest
{
//	@Ignore
//	@Test
//	public void test()
//	{
//		Transition transition = new Transition()
//		{
//			{
//				setCycleDuration(Duration.millis(500d));
//			}
//
//			@Override
//			protected void interpolate(double frac)
//			{
//				AsNonApp.app.rectangle.setWidth(300d*frac);
//				AsNonApp.app.rectangle.setHeight(20d*frac);
//			}
//		};
//		transition.setOnFinished(event -> testFinished());
//		waitFor(transition);
//	}
	
	@Ignore
	@Test
	public void testBox()
	{
		EnterLabelledBox box = new EnterLabelledBox("Hi mom", 100d, 100d);
		waitFor(()->box.addNodes(AsNonApp.app.pane));
		System.out.println("Nodes added.");
		waitFor(box.transition);
		System.out.println("Finished Box.");
	}

	@Ignore
	@Test
	public void test2()
	{
		Transition transition = new Transition()
		{
			{
				setCycleDuration(Duration.millis(1d));
			}

			@Override
			protected void interpolate(double frac)
			{
				System.out.print(".");
			}
		};
		transition.setOnFinished(event -> testFinished());
		waitFor(transition);
		System.out.println("Finished 2.");
	}

	public void testFinished()
	{
		System.out.println("\nOnFinish called.\n");
	}
}

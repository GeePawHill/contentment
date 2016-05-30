package org.geepawhill.contentment;

import org.junit.Ignore;
import org.junit.Test;

import javafx.animation.Transition;
import javafx.util.Duration;

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

package org.geepawhill.contentment.step;

import org.geepawhill.contentment.test.JavaFxRunner;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public abstract class JavaFxTest extends ApplicationTest
{

	protected JavaFxRunner runner;

	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new JavaFxRunner();
		runner.prepareWindow(stage);
	}

}
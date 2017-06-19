package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
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

	public Context getContext()
	{
		return runner.context;
	}

}
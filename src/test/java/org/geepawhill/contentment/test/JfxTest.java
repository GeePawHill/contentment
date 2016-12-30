package org.geepawhill.contentment.test;

import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class JfxTest extends ApplicationTest
{
	
	public JfxTester tester;

	@Override
	public void start(Stage stage) throws Exception
	{
		tester = new JfxTester();
		tester.prepareWindow(stage);
	}

}

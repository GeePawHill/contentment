package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.junit.Before;

public class JfxTesterTest
{
	private JfxTester tester;
	private Context context;

	@Before
	public void before()
	{
		tester = new JfxTester();
		context = tester.prepareWindow();
	}

}

package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Context;
import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void virgin()
	{
		SnapShot snap = tester.snapshot(context);
		assertNotNull(snap.root);
		assertTrue(snap.root.getChildren().isEmpty());
	}

}

package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ScriptBuilderTest
{
	static class TestScriptBuilder extends ScriptBuilder<TestScriptBuilder>
	{

		@Override
		public TestScriptBuilder downcast()
		{
			return this;
		}
		
	}
	
	TestScriptBuilder builder;
	
	@Before
	public void before()
	{
		builder = new TestScriptBuilder();
	}
	
	@Test
	public void test()
	{
	}

}

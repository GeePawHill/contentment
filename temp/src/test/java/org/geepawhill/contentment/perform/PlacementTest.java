package org.geepawhill.contentment.perform;

import org.junit.Test;

public class PlacementTest
{
	
	static interface Interface<SUBCLASS> {
		SUBCLASS top();
		SUBCLASS downcast();
	}
	
	static abstract class Generic<SUBCLASS> implements Interface<SUBCLASS> {
		public SUBCLASS top()
		{
			return downcast();
		}

	}
	
	static class Custom extends Generic<Custom>{
		public Custom custom()
		{
			return this;
		}
		
		public Custom downcast()
		{
			return this;
		}
	}

	@Test
	public void test()
	{
		Custom c = new Custom();
		c.top().custom().top().custom();
	}

}

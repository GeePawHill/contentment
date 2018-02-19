package org.geepawhill.contentment.flow;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class FlowTest
{

	@Test
	public void linesHaveCorrectString()
	{
		Flow flow = new Flow();
		flow.load("pjThis is primary jumbo.\n"
				+ "snThis is secondary normal.\n"
				+ "pnThis is prinary norma.\n"
				+ "ejThis is emphatic jumbo.\n");
		assertThat(flow.lines().size()).isEqualTo(4);
		assertThat(flow.lines().get(0).text).isEqualTo("This is primary jumbo.");
		
	}

}

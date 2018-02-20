package org.geepawhill.contentment.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class FormatTableTest
{
	private FormatTable table;

	@Before
	public void before()
	{
		table = new FormatTable();
	}

	@Test
	public void findsAllNineValues()
	{
		for (Size size : Size.values())
		{
			if(size.equals(Size.None)) continue;
			for (Color color : Color.values())
			{
				if(color.equals(Color.None)) continue;
				assertThat(table.get(size, color)).isNotNull();
			}
		}
	}

	@Test
	public void missingSize()
	{
		try
		{
			table.get(Size.None, Color.Emphatic);
			fail("No throw on missing size.");
		}
		catch (FormatTable.EntryNotFoundException e)
		{
			assertThat(e.getMessage()).endsWith("Size not found.");
		}
		try
		{
			table.get(Size.Jumbo, Color.None);
			fail("No throw on missing color.");
		}
		catch (FormatTable.EntryNotFoundException e)
		{
			assertThat(e.getMessage()).endsWith("Color not found.");
		}

	}
}

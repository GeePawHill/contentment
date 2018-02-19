package org.geepawhill.contentment.flow;
import static org.assertj.core.api.Assertions.assertThat;

import static org.geepawhill.contentment.utility.JfxUtility.color;
import static org.junit.Assert.*;

import org.geepawhill.contentment.format.Format;
import org.junit.Test;

import javafx.scene.paint.Paint;

public class FormatTableTest
{

	private FormatTable table;

	@Test
	public void test()
	{
		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		Paint primary = color(119, 187, 65);
		Paint secondary = color(177, 140, 254);
		Paint emphatic = color(255, 255, 0);

		table = new FormatTable();
		Format format = table.get(Size.Jumbo,Color.Primary);
		for( Size size : Size.values())
		{
			for( Color color : Color.values())
			{
				assertThat(table.get(size, color)).isNotNull();
			}
		}
	}

}

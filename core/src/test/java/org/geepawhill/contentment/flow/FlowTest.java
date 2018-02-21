package org.geepawhill.contentment.flow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Vector;

import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

public class FlowTest extends JavaFxTest
{

	private Flow flow;
	private ScriptWorld world;
	
	@Before
	public void before()
	{
		world = new ScriptWorld();
		flow = new Flow(world);
	}

	@Test
	public void linesHaveCorrectString()
	{
		flow.load("pjThis is primary jumbo.\n"
				+ "snThis is secondary normal.\n"
				+ "xnThis is primary normal.\n"
				+ "esThis is emphatic small.\n");
		assertThat(flow.lines().size()).isEqualTo(4);
		assertLine(0,"This is primary jumbo.", Color.Primary, Size.Jumbo);
		assertLine(1,"This is secondary normal.", Color.Secondary, Size.Normal);
		assertLine(2,"This is primary normal.", Color.Primary, Size.Normal);
		assertLine(3,"This is emphatic small.", Color.Emphatic, Size.Small);
		assertCoordinates(flow.lines());
	}
	
	public void assertLine(int index,String text, Color color, Size size)
	{
		Flow.Line line = flow.lines().get(index);
		assertThat(line.text).isEqualTo(text);
		assertThat(line.color).isEqualTo(color);
		assertThat(line.size).isEqualTo(size);
	}
	
	public void assertCoordinates(Vector<Flow.Line> lines )
	{
		Double lastEndY = -1d;
		for(Flow.Line line : lines)
		{
			assertThat(line.layout.from.x).isEqualTo(0);
			assertThat(line.layout.from.y).isGreaterThan(lastEndY);
			lastEndY = line.layout.to.y;
		}
	}

}

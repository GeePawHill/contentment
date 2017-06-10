package org.geepawhill.contentment.test;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.util.DoubleComparator;
import org.geepawhill.contentment.geometry.Point;

public class PointAssert extends AbstractAssert<PointAssert, Point>
{

	public PointAssert(Point actual)
	{
		super(actual, PointAssert.class);
	}

	public static PointAssert assertThat(Point actual)
	{
		return new PointAssert(actual);
	}

	public PointAssert isGoodEnough(Point expected, Offset<Double> offset)
	{
		DoubleComparator comparator = new DoubleComparator(offset.value);
		if (comparator.compare(actual.x, expected.x) != 0)
			failWithMessage("X Not close enough. Expected: " + expected.x + " within " + offset + " but was: " + actual.x);
		if (comparator.compare(actual.y, expected.y) != 0)
			failWithMessage("y Not close enough. Expected: " + expected.y + " within " + offset + " but was: " + actual.y);
		return this;
	}
}

package org.geepawhill.contentment.test;

import org.assertj.core.api.AbstractAssert;
import static org.geepawhill.contentment.test.ContentmentAssertions.*;
import org.geepawhill.contentment.geometry.*;

public class PointPairAssert extends AbstractAssert<PointPairAssert, PointPair>
{
	public PointPairAssert(PointPair actual)
	{
		super(actual, PointPairAssert.class);
	}

	public PointPairAssert isEqualTo(Object expected)
	{
		if(!(expected instanceof PointPair)) failWithMessage("Not a PointPair");
		PointPair expectedPair = (PointPair) expected;
		assertThat(expectedPair.from).isEqualTo(actual.from);
		assertThat(expectedPair.to).isEqualTo(actual.to);
		return this;
	}
}

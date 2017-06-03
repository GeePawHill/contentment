package org.geepawhill.contentment.outline;

import java.util.ArrayList;
import java.util.List;

public class ValueTreeMatch
{
	public final List<ValueItem> both;
	public final List<ValueItem> left;
	public final List<ValueItem> right;

	public ValueTreeMatch()
	{
		both = new ArrayList<>();
		left = new ArrayList<>();
		right = new ArrayList<>();
	}

	public boolean isEqual()
	{
		return left.isEmpty() && right.isEmpty();
	}

}

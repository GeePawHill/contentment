package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;

@FunctionalInterface
public interface ContextInterpolator
{
	void accept(double fraction, Context context);
}
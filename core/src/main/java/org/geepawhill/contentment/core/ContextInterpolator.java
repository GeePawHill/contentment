package org.geepawhill.contentment.core;

@FunctionalInterface
public interface ContextInterpolator
{
	void interpolate(Context context, double fraction);
}
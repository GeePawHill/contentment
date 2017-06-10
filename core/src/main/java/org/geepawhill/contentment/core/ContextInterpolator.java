package org.geepawhill.contentment.core;

@FunctionalInterface
public interface ContextInterpolator
{
	static ContextInterpolator NONE = (context, fraction) -> {
	};

	void interpolate(Context context, double fraction);
}
package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public interface Step
{
	public void slow(Context context, OnFinished onFinished);

	public void fast(Context context);

}

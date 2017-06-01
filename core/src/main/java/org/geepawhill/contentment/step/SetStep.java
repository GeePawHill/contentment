package org.geepawhill.contentment.step;

import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class SetStep<T> implements Step
{
	
	private T literal;
	private Consumer<T> setter;

	public SetStep(T literal,Consumer<T> setter)
	{
		this.literal = literal;
		this.setter = setter;
		
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();

	}

	@Override
	public void fast(Context context)
	{
		setter.accept(literal);
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}
}

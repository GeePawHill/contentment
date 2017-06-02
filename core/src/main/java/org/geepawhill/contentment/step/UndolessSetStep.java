package org.geepawhill.contentment.step;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class UndolessSetStep<T> implements Step
{
	
	private Consumer<T> consumer;
	private Supplier<T> supplier;
	T oldValue;

	public UndolessSetStep(T literal,Consumer<T> supplier)
	{
		this( () -> literal, supplier);
	}
	
	public UndolessSetStep(Supplier<T> supplier, Consumer<T> setter)
	{
		this.supplier = supplier;
		this.consumer = setter;
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
		consumer.accept(supplier.get());
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}
}

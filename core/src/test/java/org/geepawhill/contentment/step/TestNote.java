package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.core.OnFinished;

public class TestNote implements Gesture
{
	static public enum State
	{
		Undone, Playing, Played
	};

	State state;
	private OnFinished onFinished;
	private long ms;
	private boolean autoFinish;

	public TestNote()
	{
		this(0, false);
	}

	public TestNote(long ms)
	{
		this(ms,false);
	}
	
	public TestNote(long ms,boolean autoFinish)
	{
		this.ms = ms;
		this.ms = ms;
		this.state = State.Undone;
		this.onFinished = null;
		this.autoFinish = autoFinish;
	}
	
	@Override
	public void fast(Context context)
	{
		switch (state)
		{
		case Undone:
			state = State.Played;
			break;
		default:
			badChange("Fast");
		}

	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		switch (state)
		{
		case Undone:
			state = State.Playing;
			if(autoFinish)
			{
				finish(context);
			}
			break;
		default:
			badChange("Slow");
		}
	}

	public void finish(Context context)
	{
		switch (state)
		{
		case Playing:
			if (onFinished == null) throw new RuntimeException("No onFinished handler for Step.");
			onFinished.run();
			state = State.Played;
			break;
		default:
			badChange("Finish");
		}
	}

	private void badChange(String change)
	{
		throw new RuntimeException(change + " called on " + state + " Step.");
	}

}

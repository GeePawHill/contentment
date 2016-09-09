package org.geepawhill.contentment.core;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class StageMaximizedListener implements ChangeListener<Boolean>
{
	private ScaleListener scaleListener;
	
	public StageMaximizedListener(ScaleListener sizeListener)
	{
		this.scaleListener = sizeListener;
	}
	
	private final class ScaleCallerThread implements Runnable
	{
		public void run()
		{
			scaleListener.changed();
		}
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
	{
		launchGuiLaterThread();
	}

	private void launchGuiLaterThread()
	{
		new Thread()
		{
			public void run()
			{
				Platform.runLater(new ScaleCallerThread());
			}
		}.start();
	}

}

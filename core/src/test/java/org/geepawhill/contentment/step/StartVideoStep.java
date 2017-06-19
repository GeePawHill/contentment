package org.geepawhill.contentment.step;

import java.io.File;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.media.Media;

public class StartVideoStep implements Step
{
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		context.rhythm.play();
		onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		context.rhythm.pause();
		Media media = new Media(new File("/01faceoverCut.mp4").toURI().toString());
		context.rhythm.loadMedia(media);
	}

	@Override
	public void undo(Context context)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

}

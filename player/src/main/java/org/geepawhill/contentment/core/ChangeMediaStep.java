package org.geepawhill.contentment.core;

import java.net.URL;

import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.media.Media;

public class ChangeMediaStep implements Step
{
	
	private Media media;

	public ChangeMediaStep(String resource)
	{
		this(ChangeMediaStep.class.getClassLoader().getResource(resource));
	}

	public ChangeMediaStep(URL resource)
	{
		media = new Media(resource.toString());
	}

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

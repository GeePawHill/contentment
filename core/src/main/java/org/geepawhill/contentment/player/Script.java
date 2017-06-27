package org.geepawhill.contentment.player;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Script
{
	ArrayList<Keyframe> steps;
	private Rhythm rhythm;

	public Script()
	{
		this(new SimpleRhythm());
	}
	
	public Script(Rhythm rhythm)
	{
		this.rhythm = rhythm;
		this.steps = new ArrayList<>();
	}

	public int size()
	{
		return steps.size();
	}

	public Keyframe get(int index)
	{
		return steps.get(index);
	}

	public Script add(Keyframe step)
	{
		steps.add(step);
		return this;
	}

	public Rhythm rhythm()
	{
		return rhythm;
	}

	public MediaPlayer getMediaPlayer()
	{
		Media m = new Media(new File("/01faceoverCut.mp4").toURI().toString());
		MediaPlayer p = new MediaPlayer(m);
		p.setCycleCount(Integer.MAX_VALUE);
		p.play();
		return p;
	}
}
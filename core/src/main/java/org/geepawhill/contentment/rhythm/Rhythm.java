package org.geepawhill.contentment.rhythm;

import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Rhythm
{

	private SimpleLongProperty beatProperty;
	private Text timing;
	
	
	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		timing = makeTimingText();
	}

	public LongProperty beatProperty()
	{
		return beatProperty;
	}
	
	public long beat()
	{
		return beatProperty.get();
	}

	public void seekHard(long ms)
	{
		beatProperty.set(ms);
	}
	
	public void seekSoft(long ms)
	{
		if(beat()<ms) seekHard(ms);
	}
	
	public void update()
	{
		beatProperty.set(getPlayerTime());
		Platform.runLater( () -> timing.setText(String.format("%8d", beat())));
	}

	private long getPlayerTime()
	{
		return 1L;
	}

	public Node timingView()
	{
		return timing;
	}
	
	private Text makeTimingText()
	{
		Text text = new Text("00000000");
		text.setFont(new Font("Consolas",30d));
		text.setStroke(Color.BLUE);
		text.setFill(Color.BLUE);
		return text; 
	}
}

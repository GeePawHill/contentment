package org.geepawhill.contentment.rhythm;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Rhythm
{

	private SimpleLongProperty beatProperty;
	private LocalDateTime then;
	private Text timing;
	private Media media;
	private MediaPlayer player;
	private MediaView mediaView;

	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		then = LocalDateTime.now();
		timing = new Text(400d,400d,"Hi There");
		timing.setFont(new Font("Consolas",40d));
		timing.setStroke(Color.BLUE);
		timing.setFill(Color.WHITE);
		media = new Media(new File("../core/src/main/resources/blackHandbraked.mp4").toURI().toString());
		player = new MediaPlayer(media);
		mediaView = new MediaView(player);
		player.setAutoPlay(true);
	}

	public boolean isMedia()
	{
		return false;
	}

	public LongProperty beatProperty()
	{
		return beatProperty;
	}

	public long beat()
	{
		return beatProperty.get();
	}

	public void seek(long ms)
	{
		beatProperty.set(ms);
	}

	public void start()
	{
		then = LocalDateTime.now();
	}

	public void update()
	{
		LocalDateTime now = LocalDateTime.now();
		beatProperty.set(Duration.between(then, now).toMillis());
		timing.setText(String.format("%8d", beat()));
	}

	public Node view(Pane owner)
	{
		
		mediaView.fitWidthProperty().bind(owner.widthProperty());
		mediaView.fitHeightProperty().bind(owner.heightProperty());
		return mediaView;
	}
	
	public Node timingView()
	{
		return timing;
	}

}

package org.geepawhill.contentment.rhythm;

import java.io.File;
import java.time.LocalDateTime;

import javafx.application.Platform;
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
import javafx.util.Duration;

public class Rhythm
{

	private SimpleLongProperty beatProperty;
	private Text timing;
	private Media media;
	private MediaPlayer player;
	private MediaView mediaView;

	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		timing = new Text("Timing");
		timing.setFont(new Font("Consolas",30d));
		timing.setStroke(Color.BLUE);
		timing.setFill(Color.BLUE);
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

	public void seekHard(long ms)
	{
		beatProperty.set(ms);
		player.seek(Duration.millis(ms));
	}
	
	public void seekSoft(long ms)
	{
		if(beat()<ms) seekHard(ms);
	}

	public void start()
	{
		seekHard(0L);
		player.play();
	}

	public void update()
	{
		beatProperty.set(getPlayerTime());
		Platform.runLater( () -> timing.setText(String.format("%8d", beat())));
	}

	private long getPlayerTime()
	{
		long playerOffset = (long)player.getCurrentTime().toMillis();
		long cycleCount = (long)player.getCurrentCount();
		long cycleTime = (long)player.getCurrentTime().toMillis();
		return playerOffset+cycleTime*cycleCount;
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

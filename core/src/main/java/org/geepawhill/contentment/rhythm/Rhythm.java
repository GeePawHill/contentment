package org.geepawhill.contentment.rhythm;

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
	private MediaPlayer player;
	private MediaView mediaView;
	private Media noMedia;
	
	static String noMediaUrl = Rhythm.class.getClassLoader().getResource("org/contentment/geepawhill/rhythm/blackHandbraked.mp4").toString();
	
	public Rhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		timing = makeTimingText();
		noMedia = new Media(noMediaUrl);
		mediaView = new MediaView();
		loadMedia(noMedia);
	}

	public void loadMedia(Media media)
	{
		MediaPlayer newPlayer = new MediaPlayer(media);
		newPlayer.setAutoPlay(true);
		newPlayer.setOnReady(()->videoReady(newPlayer));
	}

	
	public LongProperty beatProperty()
	{
		return beatProperty;
	}
	
	public void play()
	{
		player.play();
	}
	
	public void pause()
	{
		player.pause();
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
	
	private void videoReady(MediaPlayer newPlayer)
	{
		mediaView.setMediaPlayer(newPlayer);
		player = newPlayer;
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

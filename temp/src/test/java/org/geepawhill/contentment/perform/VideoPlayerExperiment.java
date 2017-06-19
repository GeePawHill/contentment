package org.geepawhill.contentment.perform;

import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoPlayerExperiment extends Application
{
	public static void main(String[] args)
	{
		launch(VideoPlayerExperiment.class);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Media media = new Media(new File("../core/src/main/resources/blackHandbraked.mp4").toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.pause();
		player.setCycleCount(Integer.MAX_VALUE);
		MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(1280d);
		BorderPane pane = new BorderPane();
		pane.setCenter(mediaView);
		ToolBar tools = new ToolBar();
		
		Text total = new Text("TOTAL");
		total.setStroke(Color.WHITE);
		total.setFill(Color.BLUE);
		total.setFont(new Font("Buxton Sketch",60d));
		tools.getItems().add(total);
		
		Text time = new Text("HI MOM!");
		time.setStroke(Color.WHITE);
		time.setFill(Color.BLUE);
		time.setFont(new Font("Buxton Sketch",60d));
		tools.getItems().add(time);

		
		pane.setTop(tools);
		player.currentTimeProperty().addListener(new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
			{
				double elapsed = ((double)player.getCurrentCount())*player.getCycleDuration().toMillis()+newValue.toMillis();
				time.setText(""+elapsed);
				total.setText(player.getCycleDuration().toString());
			}
		});
		
		player.setOnEndOfMedia(() -> System.out.println("End"));
		
		stage.setScene(new Scene(pane));
		stage.show();
		player.play();
		
	}
}

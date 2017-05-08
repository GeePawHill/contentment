package org.geepawhill.contentment.core;

import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application
{

	@Override
	public void start(Stage stage)
	{
		try
		{
			PlayerView mainView = new PlayerView(stage);
			Scene scene = new Scene(mainView.getNode());
			URL resource = Main.class.getResource("/org/geepawhill/scripts/something.ttf");
			System.out.println(resource);
			Font font = Font.loadFont(resource.toExternalForm(), 10);
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.setFullScreenExitHint("");
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

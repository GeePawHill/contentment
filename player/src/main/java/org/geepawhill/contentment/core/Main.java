package org.geepawhill.contentment.core;

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
			preloadFontFile("/org/geepawhill/scripts/SAMUELS.TTF");
			preloadFontFile("/org/geepawhill/scripts/CaveatBrush-Regular.TTF");
			preloadFontFile("/org/geepawhill/scripts/gloriahallelujah.TTF");
			preloadFontFile("/org/geepawhill/scripts/GoodDog.otf");
			preloadFontFile("/org/geepawhill/scripts/belligerent.ttf");
			preloadFontFile("/org/geepawhill/scripts/WCManoNegraBoldBta.otf");

			PlayerView mainView = new PlayerView(stage);
			Scene scene = new Scene(mainView.getNode());
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

	private void preloadFontFile(String fontfile)
	{
		Font.loadFont(Main.class.getResource(fontfile).toExternalForm(), 50);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

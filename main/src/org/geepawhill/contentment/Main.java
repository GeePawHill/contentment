package org.geepawhill.contentment;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			prepareStage(stage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void prepareStage(Stage stage)
	{
		stage.setScene(new Scene(new BorderPane()));
		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

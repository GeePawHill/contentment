package org.geepawhill.contentment;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	private Pane root;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			EnterBoxedText text = new EnterBoxedText("Hi Mom!", 400d, 400d);
			text.play(root, event -> this.play2(event));
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	public void play2(ActionEvent event)
	{
		EnterBoxedText text2 = new EnterBoxedText("Also, dad!!",400d,500d);
		text2.play(root,null);
	}


	private Pane prepareStage(Stage stage)
	{
		BorderPane root = new BorderPane();
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.show();
		return root;
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

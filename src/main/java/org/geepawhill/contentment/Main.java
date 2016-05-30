package org.geepawhill.contentment;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	private BorderPane root;
	private Player player;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			
			Pane canvas = new Pane();
			root.setCenter(canvas);

			ToolBar tools = new ToolBar();
			tools.setOrientation(Orientation.VERTICAL);
			Button play = new Button("-->");
			play.setOnAction(event -> player.stepForward());
			tools.getItems().add(play);
			
			Button pause = new Button("<--");
			pause.setOnAction(event -> player.stepBackward());
			tools.getItems().add(pause);
			
			root.setRight(tools);
			
			LabelBoxStep boxOne = new LabelBoxStep("Hi Mom!", 400d, 400d);
			Sequence sequence = new Sequence(boxOne);

			player = new Player();
			player.reset(sequence);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	private BorderPane prepareStage(Stage stage)
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

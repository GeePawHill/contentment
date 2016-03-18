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
	private ActionList actions;
	private OldPlayer player;

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
			Button play = new Button("Play");
			play.setOnAction(event -> player.play(canvas));
			tools.getItems().add(play);
			
			Button pause = new Button("Pause");
			pause.setOnAction(event -> player.pause());
			tools.getItems().add(pause);

			
			root.setRight(tools);
			
			
			actions = new ActionList();
			actions.add(new EnterLabelledBox("Hi Mom!", 400d, 400d));
			actions.add(new EnterLabelledBox("Also, dad!!",400d,500d));
			
			player = new OldPlayer();
			player.reset(actions);
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

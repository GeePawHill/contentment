package org.geepawhill.contentment;

import java.util.Iterator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
	private BorderPane root;
	private ActionList actions;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			
			actions = new ActionList();
			actions.add(new EnterLabelledBox("Hi Mom!", 400d, 400d));
			actions.add(new EnterLabelledBox("Also, dad!!",400d,500d));

			ToolBar tools = new ToolBar();
			tools.setOrientation(Orientation.VERTICAL);
			Button play = new Button("Play");
			play.setOnAction(event -> play(actions.iterator()));
			tools.getItems().add(play);
			
			root.setRight(tools);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	private void play(Iterator<Action> nextAction)
	{
		if(nextAction.hasNext())
		{
			nextAction.next().play(root, event -> play(nextAction));
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

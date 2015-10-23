package org.geepawhill.contentment;

import java.util.Iterator;

import javafx.application.Application;
import javafx.application.Platform;
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
			ActionList actions = new ActionList();
			actions.add(new EnterLabelledBox("Hi Mom!", 400d, 400d));
			actions.add(new EnterLabelledBox("Also, dad!!",400d,500d));
			Iterator<Action> nextAction = actions.iterator();
			play(nextAction);
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

package org.geepawhill.contentment;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
			Button forwards = new Button("-->");
			forwards.setOnAction(event -> player.forward());
			tools.getItems().add(forwards);
			
			Button backwards = new Button("<--");
			backwards.setOnAction(event -> player.backward());
			tools.getItems().add(backwards);
			
			Button play = new Button(">");
			play.setOnAction(event -> player.play());
			tools.getItems().add(play);
			
			Button playOne = new Button(">|");
			playOne.setOnAction(event -> player.playOne());
			tools.getItems().add(playOne);
			
			Button pause = new Button("||");
			pause.setOnAction(event -> player.pause());
			tools.getItems().add(pause);
			
			root.setRight(tools);
			
			LabelBoxStep boxOne = new LabelBoxStep("Hi Mom!", 400d, 400d);
			StylePush push = new StylePush();
			StyleStep redColor = new StyleStep(new Style(StyleId.LineColor,Color.RED));
			LabelBoxStep boxTwo = new LabelBoxStep("Also, Dad!", 500d, 500d);
			StylePop pop = new StylePop();
			LabelBoxStep boxThree = new LabelBoxStep("Etc.",600d,600d);
			Sequence sequence = new Sequence(boxOne,push,redColor,boxTwo,pop,boxThree);

			player = new Player(canvas);
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

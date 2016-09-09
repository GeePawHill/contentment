package org.geepawhill.contentment;

import org.geepawhill.contentment.core.LabelBox;
import org.geepawhill.contentment.core.ScaleListener;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.StageMaximizedListener;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.step.StylePop;
import org.geepawhill.contentment.step.StylePush;
import org.geepawhill.contentment.step.StyleStep;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Group;
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
			makeTools();
			
			Pane canvas = new Pane();
			root.setCenter(canvas);
			Group scaledCanvas = new Group();
			canvas.getChildren().add(scaledCanvas);
			forceLetterBox(stage,stage.getScene(),canvas,scaledCanvas);

			LabelBox hiMom = new LabelBox("Hi Mom!",400d,400d);
			LabelBox alsoDad = new LabelBox("Also, Dad!",500d,500d);
			LabelBox etc = new LabelBox("Etc.",600d,600d);
			Step boxOne = hiMom.sketch(1000d);
			StylePush push = new StylePush();
			StyleStep redColor = new StyleStep(Style.lineColor(Color.RED));
			Step boxTwo = alsoDad.sketch(3000d);
			StylePop pop = new StylePop();
			Step boxThree = etc.sketch(1000d);
			Sequence sequence = new Sequence(boxOne,push,redColor,boxTwo,pop,boxThree);

			player = new Player(scaledCanvas);
			player.reset(sequence);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void forceLetterBox(Stage stage,Scene scene,Pane canvas, Group scaledCanvas)
	{
		ScaleListener sizeListener = new ScaleListener(canvas, scaledCanvas);
		scene.widthProperty().addListener(sizeListener);
		scene.heightProperty().addListener(sizeListener);
		StageMaximizedListener maximizeListener = new StageMaximizedListener(sizeListener);
		stage.maximizedProperty().addListener(maximizeListener);
	}

	private void makeTools()
	{
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

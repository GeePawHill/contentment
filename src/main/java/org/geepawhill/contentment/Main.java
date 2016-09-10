package org.geepawhill.contentment;

import org.geepawhill.contentment.core.Arrow;
import org.geepawhill.contentment.core.LabelBox;
import org.geepawhill.contentment.core.OvalText;
import org.geepawhill.contentment.core.ScaleListener;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.StageMaximizedListener;
import org.geepawhill.contentment.core.Style;
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

			StyleStep redLine = new StyleStep(Style.lineColor(Color.RED));
			StyleStep blueLine = new StyleStep(Style.lineColor(Color.BLUE));
			StyleStep greenLine = new StyleStep(Style.lineColor(Color.GREEN));
			LabelBox agent1 = new LabelBox("Agent",800d,450d);
			OvalText before = new OvalText("Before",400d,700d);
			OvalText after = new OvalText("After",1200d,700d);
			Arrow oldWay = new Arrow(agent1,before);
			Arrow newWay = new Arrow(agent1,after);
			StyleStep dash = new StyleStep(Style.dash(8d));
			StyleStep noDash = new StyleStep(Style.nodash());
			LabelBox coach = new LabelBox("Coach",800d,200d);
			Arrow poke1 = new Arrow(coach,agent1);
			
			Sequence sequence = new Sequence();
			sequence.unmarked(redLine);
			sequence.marked(agent1.sketch(1000d));
			sequence.unmarked(greenLine);
			sequence.unmarked(dash);
			sequence.unmarked(before.sketch(1000d));
			sequence.marked(oldWay.sketch(1d));
			sequence.marked(after.sketch(1000d));
			sequence.unmarked(noDash);
			sequence.unmarked(blueLine);
			sequence.marked(coach.sketch(1000d));
			sequence.marked(poke1.sketch(1000d));
			sequence.unmarked(oldWay.hide());
			sequence.unmarked(dash);
			sequence.unmarked(greenLine);
			sequence.marked(newWay.sketch(1d));


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
		
		Button home = new Button("||<--");
		home.setOnAction(event -> player.home());
		tools.getItems().add(home);
		
		Button backwards = new Button("<--");
		backwards.setOnAction(event -> player.backward());
		tools.getItems().add(backwards);
		
		Button play = new Button(">");
		play.setOnAction(event -> player.play());
		tools.getItems().add(play);

		Button pause = new Button("||");
		pause.setOnAction(event -> player.pause());
		tools.getItems().add(pause);

		Button playOne = new Button(">|");
		playOne.setOnAction(event -> player.playOne());
		tools.getItems().add(playOne);
		
		Button forwards = new Button("-->");
		forwards.setOnAction(event -> player.forward());
		tools.getItems().add(forwards);
		
		Button end = new Button("-->||");
		end.setOnAction(event -> player.end());
		tools.getItems().add(end);

		Button allButEnd = new Button("-->.");
		allButEnd.setOnAction(event -> player.allButEnd());
		tools.getItems().add(allButEnd);
				
		
		
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

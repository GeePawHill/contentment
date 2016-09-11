package org.geepawhill.contentment;

import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.OvalText;
import org.geepawhill.contentment.actor.Tale;
import org.geepawhill.contentment.actor.arrow.Arrow;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.StyleStep;

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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application
{
	private BorderPane root;
	private Player player;
	private Group guides;
	private Group scaledCanvas;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			makeTools();

			Pane canvas = new Pane();
			root.setCenter(canvas);
			scaledCanvas = new Group();
			canvas.getChildren().add(scaledCanvas);
			forceLetterBox(stage, stage.getScene(), canvas, scaledCanvas);
			makeGuides();

			Style redLine = Style.lineColor(Color.RED);
			Style blueLine = Style.lineColor(Color.BLUE);
			Style greenLine = Style.lineColor(Color.GREEN);
			Tale tale = new Tale("A Simple Change Model", 30d);
			LabelBox agent1 = new LabelBox("Agent", 800d, 450d);
			OvalText before = new OvalText("Before", 400d, .75*900d);
			OvalText after = new OvalText("After", 1200d, .75*900d);
			Arrow oldWay = new Arrow(agent1, before);
			Arrow newWay = new Arrow(agent1, after);
			LabelBox coach = new LabelBox("Coach", 800d, 200d);
			Arrow poke1 = new Arrow(coach, agent1);

			Sequence sequence = new Sequence();
			sequence.marked(tale.show());
			sequence.unmarked(new StyleStep(redLine));
			sequence.unmarked(agent1.sketch(1000d));
			sequence.unmarked(new StyleStep(greenLine));
			sequence.unmarked(new StyleStep(Style.dash(10d)));
			sequence.unmarked(before.sketch(1000d));
			sequence.marked(oldWay.sketch(1d));
			sequence.marked(after.sketch(1000d));
			sequence.unmarked(new StyleStep(Style.nodash()));
			sequence.unmarked(new StyleStep(blueLine));
			sequence.marked(coach.sketch(1000d));
			sequence.marked(poke1.sketch(1000d));
			sequence.unmarked(oldWay.hide());
			sequence.unmarked(new StyleStep(Style.dash(10d)));
			sequence.unmarked(new StyleStep(greenLine));
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

	private void makeGuides()
	{
		guides = new Group();
		Line diagonalOne = new Line(0d,0d,1600d,900d);
		diagonalOne.getStrokeDashArray().clear();
		diagonalOne.getStrokeDashArray().add(8d);
		Line diagonalTwo = new Line(0d,900d,1600d,0d);
		diagonalTwo.getStrokeDashArray().clear();
		diagonalTwo.getStrokeDashArray().add(8d);
		guides.getChildren().addAll(diagonalOne,diagonalTwo);
	}

	private void forceLetterBox(Stage stage, Scene scene, Pane canvas, Group scaledCanvas)
	{
		ScaleListener sizeListener = new ScaleListener(canvas, scaledCanvas);
		scene.widthProperty().addListener(sizeListener);
		scene.heightProperty().addListener(sizeListener);
		StageMaximizedListener maximizeListener = new StageMaximizedListener(sizeListener);
		stage.maximizedProperty().addListener(maximizeListener);
		Platform.runLater(new Runnable()
		{

			@Override
			public void run()
			{
				sizeListener.changed();
			}
		});
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
		
		Button guideLines = new Button("#");
		guideLines.setOnAction(event -> toggleLines());
		tools.getItems().add(guideLines);

		root.setRight(tools);
	}
	
	public void toggleLines()
	{
		if(scaledCanvas.getChildren().contains(guides))
		{
			scaledCanvas.getChildren().remove(guides);
		}
		else scaledCanvas.getChildren().add(guides);
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

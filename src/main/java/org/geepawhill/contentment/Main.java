package org.geepawhill.contentment;

import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.OvalText;
import org.geepawhill.contentment.actor.Tale;
import org.geepawhill.contentment.actor.arrow.Arrow;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.step.InstantStep;
import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.StylePop;
import org.geepawhill.contentment.style.StylePush;
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
import javafx.scene.text.Font;
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
			LabelBox agent = new LabelBox("Agent", 800d, 450d);
			OvalText practice = new OvalText("Practice", 800d, .75*900d);
			Arrow change = new Arrow(agent, false, practice, true);
			LabelBox coach = new LabelBox("Coach", 800d, 200d);
			Arrow poke = new Arrow(coach, false, agent, true);
			
			LabelBox a1 = new LabelBox("A",200d,200d);
			LabelBox a2 = new LabelBox("A",400d,300d);
			LabelBox a3 = new LabelBox("A",600d,400d);
			LabelBox a4 = new LabelBox("A",1000d,400d);
			LabelBox a5 = new LabelBox("A",1200d,300d);
			LabelBox a6 = new LabelBox("A",1400d,200d);
			Arrow poke1 = new Arrow(coach, false, a1, true);
			Arrow poke2 = new Arrow(coach, false, a2, true);
			Arrow poke3 = new Arrow(coach, false, a3, true);
			Arrow poke4 = new Arrow(coach, false, a4, true);
			Arrow poke5 = new Arrow(coach, false, a5, true);
			Arrow poke6 = new Arrow(coach, false, a6, true);
			
			OvalText p1 = new OvalText("P",200d,400d);
			OvalText p2 = new OvalText("P",400d,500d);
			OvalText p3 = new OvalText("P",600d,600d);
			OvalText p4 = new OvalText("P",1000d,600d);
			OvalText p5 = new OvalText("P",1200d,500d);
			OvalText p6 = new OvalText("P",1400d,400d);

			Arrow c1 = new Arrow(a1,false, p1, true);
			Arrow c2 = new Arrow(a2,false, p2, true);
			Arrow c3 = new Arrow(a3,false, p3, true);
			Arrow c4 = new Arrow(a4,false, p4, true);
			Arrow c5 = new Arrow(a5,false, p5, true);
			Arrow c6 = new Arrow(a6,false, p6, true);

			Arrow i1 = new Arrow(a1,true, a2, true);
			Arrow i2 = new Arrow(a2,true, a3, true);
			Arrow i3 = new Arrow(a3,true, agent, true);
			Arrow i4 = new Arrow(agent,true, a4, true);
			Arrow i5 = new Arrow(a4,true, a5, true);
			Arrow i6 = new Arrow(a5,true, a6, true);

			Sequence sequence = new Sequence();
			sequence.marked(tale.show());
			sequence.unmarked(new StyleStep(redLine));
			sequence.unmarked(agent.sketch(1000d));
			sequence.unmarked(new StyleStep(blueLine));
			sequence.marked(coach.sketch(1000d));
			sequence.marked(poke.sketch(1000d));
			sequence.unmarked(new StylePush());
			sequence.unmarked(new StyleStep(Style.font(new Font("Buxton Sketch",40d))));
			sequence.unmarked(new StyleStep(greenLine));
			sequence.unmarked(new StyleStep(Style.dash(10d)));
			sequence.unmarked(practice.sketch(1000d));
			sequence.marked(change.sketch(1d));
			sequence.unmarked(new StylePop());
			sequence.marked(tale.setText("Complication: There are always multiple agents."));
			sequence.unmarked(new StyleStep(Style.font(new Font("Buxton Sketch",30d))));
			sequence.unmarked(new StyleStep(redLine));
			sequence.unmarked(new InstantStep(a1.sketch(1d)));
			sequence.unmarked(new InstantStep(a2.sketch(1d)));
			sequence.unmarked(new InstantStep(a3.sketch(1d)));
			sequence.unmarked(new InstantStep(a4.sketch(1d)));
			sequence.unmarked(new InstantStep(a5.sketch(1d)));
			sequence.unmarked(new InstantStep(a6.sketch(1d)));
			sequence.unmarked(new StyleStep(blueLine));
			sequence.unmarked(poke1.sketch(300d));
			sequence.unmarked(poke2.sketch(300d));
			sequence.unmarked(poke3.sketch(300d));
			sequence.unmarked(poke4.sketch(300d));
			sequence.unmarked(poke5.sketch(300d));
			sequence.unmarked(poke6.sketch(300d));
			sequence.marked(poke6.sketch(300d));
			sequence.marked(tale.setText("Complication: There are always multiple practices."));
			sequence.unmarked(new StyleStep(Style.dash(10d)));
			sequence.unmarked(new StyleStep(greenLine));
			sequence.unmarked(new InstantStep(p1.sketch(1d)));
			sequence.unmarked(new InstantStep(p2.sketch(1d)));
			sequence.unmarked(new InstantStep(p3.sketch(1d)));
			sequence.unmarked(new InstantStep(p4.sketch(1d)));
			sequence.unmarked(new InstantStep(p5.sketch(1d)));
			sequence.marked(new InstantStep(p6.sketch(1d)));
			sequence.unmarked(c1.sketch(200d));
			sequence.unmarked(c2.sketch(200d));
			sequence.unmarked(c3.sketch(200d));
			sequence.unmarked(c4.sketch(200d));
			sequence.unmarked(c5.sketch(200d));
			sequence.marked(c6.sketch(200d));
			
			sequence.marked(tale.setText("Complication: The agents are interrelated."));
			sequence.unmarked(new StyleStep(blueLine));
			sequence.unmarked(i1.sketch(300d));
			sequence.unmarked(i2.sketch(300d));
			sequence.unmarked(i3.sketch(300d));
			sequence.unmarked(i4.sketch(300d));
			sequence.unmarked(i5.sketch(300d));
			sequence.marked(i6.sketch(300d));
					
			sequence.unmarked(new StyleStep(Style.nodash()));
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

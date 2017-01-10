package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.OvalText;
import org.geepawhill.contentment.actor.Spot;
import org.geepawhill.contentment.actor.Tale;
import org.geepawhill.contentment.actor.TargetBox;
import org.geepawhill.contentment.actor.arrow.Arrow;
import org.geepawhill.contentment.jfx.ScaleListener;
import org.geepawhill.contentment.jfx.StageMaximizedListener;
import org.geepawhill.contentment.newstep.Stop;
import org.geepawhill.contentment.step.ClearStep;
import org.geepawhill.contentment.step.HideStep;
import org.geepawhill.contentment.step.InstantStep;
import org.geepawhill.contentment.step.styles.GetStyles;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.step.styles.SetStyles;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.ShapePen;

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
	private Sequence sequence;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			makeTools();

			Pane canvas = new Pane();
			String image = ClassLoader.getSystemResource("mottled2.jpg").toExternalForm();
//			root.setStyle("-fx-background-image: url('" + image + "'); " +
//			           "-fx-background-position: center center; " +
//			           "-fx-background-repeat: repeat;");
			root.setCenter(canvas);
			scaledCanvas = new Group();
			canvas.getChildren().add(scaledCanvas);
			forceLetterBox(stage, stage.getScene(), canvas, scaledCanvas);
			makeGuides();
			
//			Sequence sequence = new Sequence();
//			Stroke stroke = new Stroke(new PointPair(100d,200d,300d,400d));
//			stroke.sketch(sequence, new FixedTiming(2000d));

			sequence = new Sequence();
			addBaseComplications(sequence);
			interactiveStabilization(sequence);
			agentAndPokes();
			
			player = new Player(scaledCanvas);
			player.reset(sequence);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void agentAndPokes()
	{
		Style blueLine = ShapePen.second();
		Style greenLine = ShapePen.third();
		Tale tale = new Tale("Agent: Anything With Susceptability & Unpredictability", 30d);
		LabelBox agent = new LabelBox("Agent", 800d, 520d);
		Label teammate = new Label("Teammate",800d, 520d);
		Label practice = new Label("Practice", 800d, 800d);
		Label coach = new Label("Coach", 800d, 275d);
		Label software = new Label("Software",500d,300d);
		Label hardware = new Label("Hardware",1300d,600d);
		Label policy = new Label("Policy",1100d,300d);
		Label personnel = new Label("Staff",500d,750d);
		Label date = new Label("Date",1300d,450d);
		Label framework = new Label("Framework",300d,600d);
		Label tools = new Label("Tools",300d,450d);
		Label orgchart = new Label("Org Chart",1100d,750d);

		sequence.unmarked(new ClearStep());
		sequence.unmarked(tale.show());
		sequence.unmarked(new SetStyle(blueLine));
		sequence.unmarked(new SetStyle(Dash.solid()));
		sequence.unmarked(new SetStyle(org.geepawhill.contentment.style.Font.font(new Font("Buxton Sketch",60d))));
		sequence.add(agent.sketch(1d));
		sequence.add(new Stop());
		sequence.unmarked(new HideStep(agent.group()));
		sequence.unmarked(new SetStyle(greenLine));
		sequence.add(teammate.fadeIn(500d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("That Includes The Daily Practice"));
		sequence.add(practice.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Software We Use For Making"));
		sequence.add(software.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Or The Hardware We Use For Making"));
		sequence.add(hardware.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Corporate Policy Is An Agent"));
		sequence.add(policy.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("The Staffing Demand & Supply"));
		sequence.add(personnel.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Everything About The Market, Like Shipping Date"));
		sequence.add(date.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("The Conceptual Framework The Team Uses"));
		sequence.add(framework.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("The Tools We Use"));
		sequence.add(tools.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("The Hierarchy We Live In"));
		sequence.add(orgchart.fadeIn(300d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("And, Yes, The Coach Is An Agent, Too"));
		sequence.add(coach.fadeIn(300d));
		sequence.add(new Stop());
		sequence.add(tale.setText("Watch Just One Poke..."));
		sequence.add(new Stop());
		poke(coach,teammate,850d,570d);
		poke(teammate,practice,750d,820d);
		poke(practice,software,480d,310d);
		poke(teammate,practice,820d,840d);
		poke(practice,policy,1110d,320d);
		poke(policy,date,1300d,500d);
		poke(date,personnel,450d,770d);
		poke(personnel,orgchart,1110d,745d);
		poke(personnel,practice,820d,800d);
		poke(practice,framework,250d,620d);
		poke(framework,teammate,830d,550d);
		poke(teammate,date,1300d,450d);
	}

	private void poke(Label from, Label to, double newX, double newY)
	{
		Arrow arrow = new Arrow(from,false,to,true);
		sequence.unmarked(arrow.sketch(400d));
		sequence.unmarked(to.move(newX, newY));
		sequence.unmarked(new HideStep(arrow.group()));
		
	}

	private void interactiveStabilization(Sequence sequence)
	{
		Style redLine = ShapePen.first();
		Style blueLine = ShapePen.second();
		Style greenLine = ShapePen.third();
		
		Tale tale = new Tale("Agents Are Susceptible To Pokes", 30d);
		LabelBox agent = new LabelBox("Agent", 800d, 450d);
		TargetBox target = new TargetBox("Target",1000d,350d,300d,200d);
		
		Spot poke1Source = new Spot(400d,450d);
		Spot poke2Source = new Spot(800d,200d);
		Spot poke3Source = new Spot(800d,600d);

		Arrow poke1 = new Arrow(poke1Source,false,agent,true);
		Arrow poke2 = new Arrow(poke2Source,false,agent,true);
		Arrow poke3 = new Arrow(poke3Source,false,agent,true);
		
		sequence.unmarked(new ClearStep());
		sequence.unmarked(tale.show());
		sequence.unmarked(new SetStyle(blueLine));
		sequence.unmarked(new SetStyle(Dash.solid()));
		sequence.unmarked(new SetStyle(org.geepawhill.contentment.style.Font.font(new Font("Buxton Sketch",60d))));

		sequence.unmarked(new GetStyles());
		sequence.unmarked(new SetStyle(redLine));
		sequence.unmarked(new SetStyle(org.geepawhill.contentment.style.Font.font(new Font("Century Gothic",24d))));
		sequence.unmarked(new SetStyle(Dash.dash(4d)));
		sequence.unmarked(new SetStyle(ShapePen.thinFirst()));
		sequence.unmarked(target.sketch(1d));
		sequence.unmarked(new SetStyles());
		sequence.add(agent.sketch(1000d));
		sequence.add(new Stop());
		
		sequence.unmarked(poke1Source.place());
		sequence.add(poke1.sketch(1000d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Agents Respond Unpredictably"));
		sequence.add(agent.move(900d, 400d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Whoops: Better Try Another Poke"));
		sequence.unmarked(poke2Source.place());
		sequence.unmarked(new SetStyle(redLine));
		sequence.unmarked(poke2.sketch(1000d));
		sequence.add(agent.move(1000d, 500d));
		sequence.add(new Stop());
		sequence.unmarked(tale.setText("Almost there!"));
		sequence.unmarked(poke3Source.place());
		sequence.unmarked(new SetStyle(greenLine));
		sequence.unmarked(poke3.sketch(1000d));
		sequence.unmarked(agent.move(1100d, 450d));
		sequence.add(tale.setText("Made it!!"));
		sequence.add(new Stop());
		
	}

	private void addBaseComplications(Sequence sequence)
	{
		Style firstMarker = ShapePen.pen("First", Color.RED, Color.TRANSPARENT, 5d, .5d);
		Style redLine = ShapePen.first();
		Style blueLine = ShapePen.second();
		Style greenLine = ShapePen.third();
		Style blackLine = ShapePen.fourth();
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

		Arrow c11 = new Arrow(a1,false, p2, true);
		Arrow c12 = new Arrow(a2,false, p3, true);
		Arrow c13 = new Arrow(a3,false, practice, true);
		Arrow c14 = new Arrow(a4,false, p5, true);
		Arrow c15 = new Arrow(a5,false, p6, true);
		
		Arrow c22 = new Arrow(a2,false, p1, true);
		Arrow c23 = new Arrow(a3,false, p2, true);
		Arrow c24 = new Arrow(a4,false, practice, true);
		Arrow c25 = new Arrow(a5,false, p4, true);
		Arrow c26 = new Arrow(a6,false, p5, true);
		
		Arrow i1 = new Arrow(a1,true, a2, true);
		Arrow i2 = new Arrow(a2,true, a3, true);
		Arrow i3 = new Arrow(a3,true, agent, true);
		Arrow i4 = new Arrow(agent,true, a4, true);
		Arrow i5 = new Arrow(a4,true, a5, true);
		Arrow i6 = new Arrow(a5,true, a6, true);

		Arrow pi1 = new Arrow(p1,true, p2, true);
		Arrow pi2 = new Arrow(p2,true, p3, true);
		Arrow pi3 = new Arrow(p3,true, practice, true);
		Arrow pi4 = new Arrow(practice,true, p4, true);
		Arrow pi5 = new Arrow(p4,true, p5, true);
		Arrow pi6 = new Arrow(p5,true, p6, true);
		
		sequence.unmarked(tale.show());
		sequence.unmarked(new SetStyle(redLine));
		sequence.unmarked(new SetStyle(firstMarker));
		sequence.unmarked(agent.sketch(1000d));
		sequence.unmarked(new SetStyle(blueLine));
		sequence.unmarked(new SetStyle(ShapePen.second()));
		sequence.unmarked(coach.sketch(1000d));
		sequence.add(new Stop());
		sequence.add(poke.sketch(1000d));
		sequence.unmarked(new GetStyles());
		sequence.unmarked(new SetStyle(org.geepawhill.contentment.style.Font.font(new Font("Buxton Sketch",40d))));
		sequence.unmarked(new SetStyle(greenLine));
		sequence.unmarked(new SetStyle(Dash.dash(10d)));
		sequence.unmarked(practice.sketch(1000d));
		sequence.unmarked(change.sketch(1d));
		sequence.unmarked(new SetStyles());
		sequence.add(new Stop());
		sequence.add(tale.setText("Complication: There are always multiple agents."));
		sequence.unmarked(new SetStyle(org.geepawhill.contentment.style.Font.font(new Font("Buxton Sketch",30d))));
		sequence.unmarked(new SetStyle(redLine));
		sequence.unmarked(new SetStyle(firstMarker));
		sequence.unmarked(new InstantStep(a1.sketch(1d)));
		sequence.unmarked(new InstantStep(a2.sketch(1d)));
		sequence.unmarked(new InstantStep(a3.sketch(1d)));
		sequence.unmarked(new InstantStep(a4.sketch(1d)));
		sequence.unmarked(new InstantStep(a5.sketch(1d)));
		sequence.unmarked(new InstantStep(a6.sketch(1d)));
		sequence.unmarked(new SetStyle(blueLine));
		sequence.unmarked(poke1.sketch(300d));
		sequence.unmarked(poke2.sketch(300d));
		sequence.unmarked(poke3.sketch(300d));
		sequence.unmarked(poke4.sketch(300d));
		sequence.unmarked(poke5.sketch(300d));
		sequence.add(poke6.sketch(300d));
		sequence.add(new Stop());
		sequence.add(tale.setText("Complication: There are always multiple practices."));
		sequence.unmarked(new SetStyle(Dash.dash(10d)));
		sequence.unmarked(new SetStyle(greenLine));
		sequence.unmarked(new InstantStep(p1.sketch(1d)));
		sequence.unmarked(new InstantStep(p2.sketch(1d)));
		sequence.unmarked(new InstantStep(p3.sketch(1d)));
		sequence.unmarked(new InstantStep(p4.sketch(1d)));
		sequence.unmarked(new InstantStep(p5.sketch(1d)));
		sequence.unmarked(new InstantStep(p6.sketch(1d)));
		sequence.unmarked(c1.sketch(200d));
		sequence.unmarked(c2.sketch(200d));
		sequence.unmarked(c3.sketch(200d));
		sequence.unmarked(c4.sketch(200d));
		sequence.unmarked(c5.sketch(200d));
		sequence.add(c6.sketch(200d));
		sequence.add(new Stop());
		
		sequence.add(tale.setText("Complication: Most agents change multiple practices."));
		sequence.unmarked(new SetStyle(Dash.dash(10d)));
		sequence.unmarked(new SetStyle(greenLine));
		sequence.unmarked(c11.sketch(200d));
		sequence.unmarked(c12.sketch(200d));
		sequence.unmarked(c13.sketch(200d));
		sequence.unmarked(c14.sketch(200d));
		sequence.unmarked(c15.sketch(200d));
		sequence.unmarked(c22.sketch(200d));
		sequence.unmarked(c23.sketch(200d));
		sequence.unmarked(c24.sketch(200d));
		sequence.unmarked(c25.sketch(200d));
		sequence.add(c26.sketch(200d));
		sequence.add(new Stop());
		
		sequence.unmarked(new GetStyles());
		sequence.unmarked(new SetStyle(Dash.dash(3d)));
		sequence.unmarked(new SetStyle(ShapePen.thinFourth()));
		sequence.add(tale.setText("Complication: The agents are interrelated."));
		sequence.unmarked(i1.sketch(300d));
		sequence.unmarked(i2.sketch(300d));
		sequence.unmarked(i3.sketch(300d));
		sequence.unmarked(i4.sketch(300d));
		sequence.unmarked(i5.sketch(300d));
		sequence.add(i6.sketch(300d));
		sequence.add(new Stop());
		sequence.add(tale.setText("Complication: The practices are interrelated."));
		sequence.unmarked(pi1.sketch(300d));
		sequence.unmarked(pi2.sketch(300d));
		sequence.unmarked(pi3.sketch(300d));
		sequence.unmarked(pi4.sketch(300d));
		sequence.unmarked(pi5.sketch(300d));
		sequence.add(pi6.sketch(300d));
		sequence.add(new Stop());
		sequence.unmarked(new SetStyles());
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

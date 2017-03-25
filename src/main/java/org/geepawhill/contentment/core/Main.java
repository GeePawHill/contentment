package org.geepawhill.contentment.core;

import org.controlsfx.control.HiddenSidesPane;
import org.geepawhill.contentment.actor.Arrow;
import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.actor.Letters;
import org.geepawhill.contentment.actor.OvalText;
import org.geepawhill.contentment.actor.Spot;
import org.geepawhill.contentment.actor.TargetBox;
import org.geepawhill.contentment.actor.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.jfx.ScaleListener;
import org.geepawhill.contentment.jfx.StageMaximizedListener;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TextColors;
import org.geepawhill.contentment.style.TextFont;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application
{
	private HiddenSidesPane pane;
	private Player player;
	private Group guides;
	private Group page;
	private Sequence sequence;
	private CommonSteps common;

	@Override
	public void start(Stage stage)
	{
		try
		{
			pane = prepareStage(stage);
			makeTools();

			Pane canvas = new Pane();
			BackgroundFill fill = new BackgroundFill(Color.BLACK, null, null);
			canvas.setBackground(new Background(fill));
			pane.setContent(canvas);
			page = new Group();
			canvas.setOnMouseClicked((event) -> mouseClicked(event));
			canvas.getChildren().add(page);
			forceLetterBox(stage, stage.getScene(), canvas, page);
			makeGuides();
			toggleLines();

			sequence = new Sequence();
			common = new CommonSteps(sequence);
			makeScripts();

			player = new Player(page);
			player.reset(sequence);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void mouseClicked(MouseEvent event)
	{
		player.playOne();
	}

	private void makeScripts()
	{
		addBaseComplications(sequence);
		interactiveStabilization(sequence);
		agentAndPokes();
		new GeekNeeqOne(sequence).add();
	}

	private void agentAndPokes()
	{
		Title tale = new Title("Agent: Anything With Susceptability & Unpredictability");
		LabelBox agent = new LabelBox("Agent", new Point(800d, 520d), agentFormat());

		Letters teammate = new Letters("Teammate", new Point(800d, 520d), agentFormat());
		Letters practice = new Letters("Practice", new Point(800d, 800d), agentFormat());
		Letters coach = new Letters("Coach", new Point(800d, 275d), agentFormat());
		Letters software = new Letters("Software", new Point(500d, 300d), agentFormat());
		Letters hardware = new Letters("Hardware", new Point(1300d, 600d), agentFormat());
		Letters policy = new Letters("Policy", new Point(1100d, 300d), agentFormat());
		Letters personnel = new Letters("Staff", new Point(500d, 750d), agentFormat());
		Letters date = new Letters("Date", new Point(1300d, 450d), agentFormat());
		Letters framework = new Letters("Framework", new Point(300d, 600d), agentFormat());
		Letters tools = new Letters("Tools", new Point(300d, 450d), agentFormat());
		Letters orgchart = new Letters("Org Chart", new Point(1100d, 750d), agentFormat());

		common.clear();
		common.show(tale);
		agent.sketch(sequence, 1d);
		common.stop();
		common.hide(agent);
		teammate.fadeIn(sequence, 500d);
		common.stop();
		tale.setText(sequence, "That Includes The Daily Practice");
		practice.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Software We Use For Making");
		software.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Or The Hardware We Use For Making");
		hardware.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Corporate Policy Is An Agent");
		policy.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Staffing Demand & Supply");
		personnel.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Everything About The Market, Like Shipping Date");
		date.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Conceptual Framework The Team Uses");
		framework.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Tools We Use");
		tools.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "The Hierarchy We Live In");
		orgchart.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "And, Yes, The Coach Is An Agent, Too");
		coach.fadeIn(sequence, 300d);
		common.stop();
		tale.setText(sequence, "Watch Just One Poke...");
		common.stop();
		poke(coach, teammate, 850d, 570d);
		poke(teammate, practice, 750d, 820d);
		poke(practice, software, 480d, 310d);
		poke(teammate, practice, 820d, 840d);
		poke(practice, policy, 1110d, 320d);
		poke(policy, date, 1300d, 500d);
		poke(date, personnel, 450d, 770d);
		poke(personnel, orgchart, 1110d, 745d);
		poke(personnel, practice, 820d, 800d);
		poke(practice, framework, 250d, 620d);
		poke(framework, teammate, 830d, 550d);
		poke(teammate, date, 1300d, 450d);
	}

	private Format agentFormat()
	{
		Style agentColors = TextColors.color("Agent", Color.CHARTREUSE, .8d);
		Style agentFont = TextFont.largeHand();
		Style agentFrame = Frames.frame("Agent", Color.CHARTREUSE, 5d, .8d);
		Style agentDash = Dash.solid();
		Format agentFormat = new Format("Agent", agentColors, agentFont, agentFrame, agentDash);
		return agentFormat;
	}

	private void poke(Letters from, Letters to, double newX, double newY)
	{
		Arrow arrow = new Arrow(from, false, to, true, pokeFormat());
		arrow.sketch(sequence, 400d);
		to.move(sequence, newX, newY);
		common.hide(arrow);
	}

	private void interactiveStabilization(Sequence sequence)
	{
		Title tale = new Title("Agents Are Susceptible To Pokes");
		LabelBox agent = new LabelBox("Agent", new Point(800d, 450d), agentFormat());
		TargetBox target = new TargetBox("Target", new Point(1050d, 350d), targetFormat());

		Spot poke1Source = new Spot(400d, 450d);
		Spot poke2Source = new Spot(800d, 200d);
		Spot poke3Source = new Spot(800d, 600d);

		Arrow poke1 = new Arrow(poke1Source, false, agent, true, pokeFormat(Color.RED));
		Arrow poke2 = new Arrow(poke2Source, false, agent, true, pokeFormat(Color.BLUE));
		Arrow poke3 = new Arrow(poke3Source, false, agent, true, pokeFormat(Color.GREEN));

		common.clear();
		common.show(tale);
		target.sketch(sequence, 1d);

		agent.sketch(sequence, 1d);
		common.stop();

		poke1Source.place(sequence);
		poke1.sketch(sequence, 1000d);
		common.stop();
		tale.setText(sequence, "Agents Respond Unpredictably");
		sequence.add(agent.move(900d, 400d));
		common.stop();
		tale.setText(sequence, "Whoops: Better Try Another Poke");
		poke2Source.place(sequence);
		poke2.sketch(sequence, 1000d);
		sequence.add(agent.move(1000d, 500d));
		common.stop();
		tale.setText(sequence, "Almost there!");
		poke3Source.place(sequence);
		poke3.sketch(sequence, 1000d);
		sequence.unmarked(agent.move(1100d, 450d));
		tale.setText(sequence, "Made it!!");
		common.stop();
	}

	private void addBaseComplications(Sequence sequence)
	{
		Title tale = new Title("A Simple Change Model");
		LabelBox agent = new LabelBox("Agent", new Point(800d, 450d), agentFormat());
		OvalText practice = new OvalText("Practice", new Point(800d, .75 * 900d), practiceFormat());
		Arrow change = new Arrow(agent, false, practice, true, changeFormat());
		LabelBox coach = new LabelBox("Coach", new Point(800d, 200d), coachFormat());
		Arrow poke = new Arrow(coach, false, agent, true, pokeFormat());

		LabelBox a1 = new LabelBox("A", new Point(200d, 200d), agentFormat());
		LabelBox a2 = new LabelBox("A", new Point(400d, 300d), agentFormat());
		LabelBox a3 = new LabelBox("A", new Point(600d, 400d), agentFormat());
		LabelBox a4 = new LabelBox("A", new Point(1000d, 400d), agentFormat());
		LabelBox a5 = new LabelBox("A", new Point(1200d, 300d), agentFormat());
		LabelBox a6 = new LabelBox("A", new Point(1400d, 200d), agentFormat());

		Arrow poke1 = new Arrow(coach, false, a1, true, pokeFormat());
		Arrow poke2 = new Arrow(coach, false, a2, true, pokeFormat());
		Arrow poke3 = new Arrow(coach, false, a3, true, pokeFormat());
		Arrow poke4 = new Arrow(coach, false, a4, true, pokeFormat());
		Arrow poke5 = new Arrow(coach, false, a5, true, pokeFormat());
		Arrow poke6 = new Arrow(coach, false, a6, true, pokeFormat());

		OvalText p1 = new OvalText("P", new Point(200d, 400d), practiceFormat());
		OvalText p2 = new OvalText("P", new Point(400d, 500d), practiceFormat());
		OvalText p3 = new OvalText("P", new Point(600d, 600d), practiceFormat());
		OvalText p4 = new OvalText("P", new Point(1000d, 600d), practiceFormat());
		OvalText p5 = new OvalText("P", new Point(1200d, 500d), practiceFormat());
		OvalText p6 = new OvalText("P", new Point(1400d, 400d), practiceFormat());

		Arrow c1 = new Arrow(a1, false, p1, true, changeFormat());
		Arrow c2 = new Arrow(a2, false, p2, true, changeFormat());
		Arrow c3 = new Arrow(a3, false, p3, true, changeFormat());
		Arrow c4 = new Arrow(a4, false, p4, true, changeFormat());
		Arrow c5 = new Arrow(a5, false, p5, true, changeFormat());
		Arrow c6 = new Arrow(a6, false, p6, true, changeFormat());

		Arrow c11 = new Arrow(a1, false, p2, true, changeFormat());
		Arrow c12 = new Arrow(a2, false, p3, true, changeFormat());
		Arrow c13 = new Arrow(a3, false, practice, true, changeFormat());
		Arrow c14 = new Arrow(a4, false, p5, true, changeFormat());
		Arrow c15 = new Arrow(a5, false, p6, true, changeFormat());

		Arrow c22 = new Arrow(a2, false, p1, true, changeFormat());
		Arrow c23 = new Arrow(a3, false, p2, true, changeFormat());
		Arrow c24 = new Arrow(a4, false, practice, true, changeFormat());
		Arrow c25 = new Arrow(a5, false, p4, true, changeFormat());
		Arrow c26 = new Arrow(a6, false, p5, true, changeFormat());

		Arrow i1 = new Arrow(a1, false, a2, false, relationFormat());
		Arrow i2 = new Arrow(a2, false, a3, false, relationFormat());
		Arrow i3 = new Arrow(a3, false, agent, false, relationFormat());
		Arrow i4 = new Arrow(agent, false, a4, false, relationFormat());
		Arrow i5 = new Arrow(a4, false, a5, false, relationFormat());
		Arrow i6 = new Arrow(a5, false, a6, false, relationFormat());

		Arrow pi1 = new Arrow(p1, false, p2, false, relationFormat());
		Arrow pi2 = new Arrow(p2, false, p3, false, relationFormat());
		Arrow pi3 = new Arrow(p3, false, practice, false, relationFormat());
		Arrow pi4 = new Arrow(practice, false, p4, false, relationFormat());
		Arrow pi5 = new Arrow(p4, false, p5, false, relationFormat());
		Arrow pi6 = new Arrow(p5, false, p6, false, relationFormat());

		common.show(tale);
		agent.sketch(sequence, 1000d);
		coach.sketch(sequence, 1000d);
		common.stop();
		poke.sketch(sequence, 1000d);
		practice.sketch(sequence, 1000d);
		change.sketch(sequence, 1000d);
		common.stop();
		tale.setText(sequence, "Complication: There are always multiple agents.");
		a1.sketch(sequence, 1d);
		a2.sketch(sequence, 1d);
		a3.sketch(sequence, 1d);
		a4.sketch(sequence, 1d);
		a5.sketch(sequence, 1d);
		a6.sketch(sequence, 1d);
		poke1.sketch(sequence, 1d);
		poke2.sketch(sequence, 1d);
		poke3.sketch(sequence, 1d);
		poke4.sketch(sequence, 1d);
		poke5.sketch(sequence, 1d);
		poke6.sketch(sequence, 1d);
		common.stop();
		tale.setText(sequence, "Complication: There are always multiple practices.");
		p1.sketch(sequence, 1d);
		p2.sketch(sequence, 1d);
		p3.sketch(sequence, 1d);
		p4.sketch(sequence, 1d);
		p5.sketch(sequence, 1d);
		p6.sketch(sequence, 1d);
		c1.sketch(sequence, 1d);
		c2.sketch(sequence, 1d);
		c3.sketch(sequence, 1d);
		c4.sketch(sequence, 1d);
		c5.sketch(sequence, 1d);
		c6.sketch(sequence, 1d);
		common.stop();

		tale.setText(sequence, "Complication: Most agents change multiple practices.");
		c12.sketch(sequence, 200d);
		c22.sketch(sequence, 200d);
		common.stop();
		c11.sketch(sequence, 1d);
		c13.sketch(sequence, 1d);
		c14.sketch(sequence, 1d);
		c15.sketch(sequence, 1d);
		c23.sketch(sequence, 1d);
		c24.sketch(sequence, 1d);
		c25.sketch(sequence, 1d);
		c26.sketch(sequence, 1d);
		common.stop();

		tale.setText(sequence, "Complication: The agents are interrelated.");
		i1.sketch(sequence, 1000d);
		common.stop();
		i2.sketch(sequence, 1d);
		i3.sketch(sequence, 1d);
		i4.sketch(sequence, 1d);
		i5.sketch(sequence, 1d);
		i6.sketch(sequence, 1d);
		common.stop();
		tale.setText(sequence, "Complication: The practices are interrelated.");
		pi1.sketch(sequence, 1000d);
		common.stop();
		pi2.sketch(sequence, 1d);
		pi3.sketch(sequence, 1d);
		pi4.sketch(sequence, 1d);
		pi5.sketch(sequence, 1d);
		pi6.sketch(sequence, 1d);
		common.stop();
	}

	private Format targetFormat()
	{
		return new Format(Frames.frame("Target", Color.WHITE, 2d, .8d), TextFont.mediumSans(),
				TextColors.color("Target", Color.WHITE, .8d), Dash.dash("dash", 5d));
	}

	private Format coachFormat()
	{
		return new Format("Coach", agentFormat(), TextColors.color("Coach", Color.LIGHTSKYBLUE, .8d),
				Frames.frame("Coach", Color.LIGHTSKYBLUE, 5d, .8d));
	}

	private Format pokeFormat(Paint paint)
	{
		return new Format(pokeFormat(), Frames.frame("Poke", paint, 5d, .8d));
	}

	private Format pokeFormat()
	{
		return new Format("Poke", agentFormat(), Dash.solid(), Frames.frame("Coach", Color.SLATEBLUE, 5d, .8d));
	}

	private Format practiceFormat()
	{
		return new Format(Frames.frame("Practice", Color.TOMATO, 4d, .8d), TextColors.color("Practice", Color.TOMATO, .8d),
				Dash.dash("dash", 4d), TextFont.largeHand());
	}

	private Format relationFormat()
	{
		return new Format(Frames.frame("Change", Color.LIGHTCORAL, 2d, .8d), Dash.dash("Relationship", 10d));
	}

	private Format changeFormat()
	{
		return new Format(Frames.frame("Change", Color.ANTIQUEWHITE, 5d, .8d), Dash.dash("Change", 10d));
	}

	private void makeGuides()
	{
		guides = new Group();
		Line diagonalOne = new Line(0d, 0d, 1600d, 900d);
		diagonalOne.setFill(Color.WHITE);
		diagonalOne.getStrokeDashArray().clear();
		diagonalOne.getStrokeDashArray().add(8d);
		Line diagonalTwo = new Line(0d, 900d, 1600d, 0d);
		diagonalTwo.setFill(Color.WHITE);
		diagonalTwo.getStrokeDashArray().clear();
		diagonalTwo.getStrokeDashArray().add(8d);
		guides.getChildren().addAll(diagonalOne, diagonalTwo);
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
		
		Button pin = new Button("Pin");
		pin.setOnAction(event -> pinToolBar());
		tools.getItems().add(pin);

		Button test = new Button("**");
		test.setOnAction(event -> makeScripts());
		tools.getItems().add(test);

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

		pane.setRight(tools);
		pane.setPinnedSide(Side.RIGHT);
	}

	private void pinToolBar()
	{
		Side side = pane.getPinnedSide();
		pane.setPinnedSide(side==Side.RIGHT ? null : Side.RIGHT);
	}

	public void toggleLines()
	{
		if (page.getChildren().contains(guides))
		{
			page.getChildren().remove(guides);
		}
		else
			page.getChildren().add(guides);
	}

	private HiddenSidesPane prepareStage(Stage stage)
	{
		HiddenSidesPane root = new HiddenSidesPane();
//		BorderPane root = new BorderPane();
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

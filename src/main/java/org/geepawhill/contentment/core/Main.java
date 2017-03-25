package org.geepawhill.contentment.core;

import org.controlsfx.control.HiddenSidesPane;
import org.geepawhill.contentment.jfx.ScaleListener;
import org.geepawhill.contentment.jfx.StageMaximizedListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application
{
	private HiddenSidesPane pane;
	private Player player;
	private Group guides;
	private Group page;
	private Sequence sequence;

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
		new BaseComplications(sequence).add();
		new InteractiveStabilization(sequence).add();
		new AgentAndPokes(sequence).add();
		new GeekNeeqOne(sequence).add();
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

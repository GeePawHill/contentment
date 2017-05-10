package org.geepawhill.contentment.core;

import org.controlsfx.control.HiddenSidesPane;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.jfx.ScaleListener;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayerView
{
	private Player player;
	private Sequence sequence;
	private HiddenSidesPane root;
	private Stage stage;
	
	public PlayerView(Stage stage)
	{
		this.stage = stage;
	}

	public Parent getNode()
	{
		root = new HiddenSidesPane();
		root.setRight(makeTools());
		root.setContent(makeViewport());
		
		sequence = new Sequence();
		makeScripts(sequence);
		return root;
	}

	private AnchorPane makeViewport()
	{
		Pane scalingPane = new Pane();
		
		BackgroundFill fill = new BackgroundFill(Color.BLACK, null, null);
		scalingPane.setBackground(new Background(fill));
		
		Group canvas = new Group();
		ScaleListener listener = new ScaleListener(scalingPane,canvas);
		scalingPane.widthProperty().addListener(listener);
		scalingPane.heightProperty().addListener(listener);
		player = new Player(canvas);
		
		scalingPane.setOnMouseClicked((event) -> mouseClicked(event));
		scalingPane.getChildren().add(canvas);
		
		return JfxUtility.makeAnchorFor(scalingPane);
	}
	
	private void mouseClicked(MouseEvent event)
	{
		if(event.getButton()==MouseButton.SECONDARY) player.backward();
		else player.playOne();	
		}

	private void makeScripts(Sequence sequence)
	{
		new BaseComplications(sequence).add();
		new InteractiveStabilization(sequence).add();
		new AgentAndPokes(sequence).add();
//		new GeekNeeqOne(sequence).add();
//		new VisibleGeekLa1(sequence).add();
		new ResponsesToComplexity(sequence).add();
		player.reset(sequence);
	}
	
	private ToolBar makeTools()
	{
		ToolBar tools = new ToolBar();
		tools.setOrientation(Orientation.VERTICAL);
		
		Button full = new Button("Full");
		full.setOnAction(event -> stage.setFullScreen(true));
		tools.getItems().add(full);
		
		Button test = new Button("**");
		test.setOnAction(event -> makeScripts(sequence));
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

		return tools;
	}

}

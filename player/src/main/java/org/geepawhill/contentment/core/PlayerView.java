package org.geepawhill.contentment.core;

import org.controlsfx.control.HiddenSidesPane;
import org.geepawhill.contentment.jfx.ScaleListener;

import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

	private Pane makeViewport()
	{
		Pane owner = new Pane();
		owner.setPrefSize(1600d, 900d);
		
		Group canvas = new Group();
		player = new Player(canvas);
		
		ScaleListener listener = new ScaleListener(owner,canvas);
		owner.widthProperty().addListener(listener);
		owner.heightProperty().addListener(listener);
		
		owner.setOnMouseClicked((event) -> mouseClicked(event));
		
		Node media = player.context.rhythm.view(owner);
		owner.getChildren().add(media);
		owner.getChildren().add(canvas);
		owner.getChildren().add(player.context.rhythm.timingView());
		return owner;
	}
	
	private void mouseClicked(MouseEvent event)
	{
		if(event.isShiftDown() && event.getButton()==MouseButton.PRIMARY)
		{
			player.play();
			return;
		}
		if(event.getButton()==MouseButton.SECONDARY) player.backward();
		else player.playOne();	
		}

	private void makeScripts(Sequence sequence)
	{
//		new DemoScript(sequence).add();
//		new BaseComplications(sequence).add();
		new UnderplayedScript(sequence).add();
//		new InteractiveStabilization(sequence).add();
//		new AgentAndPokes(sequence).add();
//		new ResponsesToComplexity(sequence).add();
//		new VisibleGeekLa1(sequence).add();
//		new GeekNeeqOne(sequence).add();
		player.reset(sequence);
	}
	
	private ToolBar makeTools()
	{
		ToolBar tools = new ToolBar();
		tools.setOrientation(Orientation.VERTICAL);
		
		Button full = new Button("Full");
		full.setOnAction(event -> makeFullScreen());
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

	private void makeFullScreen()
	{
		stage.setFullScreen(true);
		stage.getScene().setCursor(Cursor.NONE);
	}

}

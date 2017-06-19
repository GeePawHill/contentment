package org.geepawhill.contentment.core;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayerView
{
	private Player player;
	private Sequence sequence;
	private BorderPane root;
	private Stage stage;
	private Group canvas;
	private ToolBar tools;
	
	public PlayerView(Stage stage)
	{
		this.stage = stage;
		canvas = new Group();
		player = new Player(canvas);
		tools = makeTools();
		stage.fullScreenProperty().addListener((source,o,n) -> undoFullScreen(n));
	}

	public Parent getNode()
	{
		root = new BorderPane();
		root.setTop(makeTools());
		root.setCenter(makeViewport());
		
		sequence = new Sequence();
		makeScripts(sequence);
		return root;
	}

	private Pane makeViewport()
	{
		Pane owner = new Pane();
		owner.setPrefSize(1600d, 900d);
		
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
//		new DemoVideo3(sequence).add();
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
		tools.setOrientation(Orientation.HORIZONTAL);
		
		tools.getItems().add(player.context.rhythm.timingView());
		
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

	private void undoFullScreen(Boolean newValue)
	{
		if(newValue==false)
		{
			root.setTop(tools);
			stage.getScene().setCursor(Cursor.DEFAULT);
		}
		else
		{
			root.setTop(null);
			stage.getScene().setCursor(Cursor.NONE);
		}	
	}

}

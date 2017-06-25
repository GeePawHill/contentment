package org.geepawhill.contentment.core;

import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.ScaleListener;
import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerView
{
	private SyncPlayer player;
	private Sequence sequence;
	private BorderPane root;
	private Stage stage;
	private Group canvas;
	private ToolBar tools;
	private Text timing;

	public PlayerView(Stage stage)
	{
		this.stage = stage;
		canvas = new Group();
		player = new SyncPlayer(canvas, new SimpleRhythm());
		tools = makeTools();
		stage.fullScreenProperty().addListener((source, o, n) -> undoFullScreen(n));
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

		// media background

		// non-media background

		ScaleListener listener = new ScaleListener(owner, canvas);
		owner.widthProperty().addListener(listener);
		owner.heightProperty().addListener(listener);
		listener.changed(null, 300, 300);

		owner.setOnMouseClicked((event) -> mouseClicked(event));

		owner.getChildren().add(canvas);
		return owner;
	}

	private void mouseClicked(MouseEvent event)
	{
		if (event.isShiftDown() && event.getButton() == MouseButton.PRIMARY)
		{
			player.play();
			return;
		}
		if (event.getButton() == MouseButton.SECONDARY) player.backward();
		else player.playOne();
	}

	private void makeScripts(Sequence sequence)
	{
		player.load(new UnderplayedScript().make());
		// new DemoVideo3(sequence).add();
		// new DemoScript(sequence).add();
		// new BaseComplications(sequence).add();
		// new InteractiveStabilization(sequence).add();
		// new AgentAndPokes(sequence).add();
		// new ResponsesToComplexity(sequence).add();
		// new VisibleGeekLa1(sequence).add();
		// new GeekNeeqOne(sequence).add();
		// player.reset(sequence);
	}

	private ToolBar makeTools()
	{
		ToolBar tools = new ToolBar();
		tools.setOrientation(Orientation.HORIZONTAL);

		timing = new Text("00000000");
		timing.setFont(new Font("Consolas", 30d));
		timing.setStroke(Color.BLUE);
		timing.setFill(Color.BLUE);
		player.getRhythm().beatProperty().addListener((p, o, n) -> beatChanged(n));
		tools.getItems().add(timing);
		beatChanged(0);

		Button full = new Button("Full");
		full.setOnAction(event -> stage.setFullScreen(true));
		tools.getItems().add(full);

		Button home = new Button("||<--");
		home.setOnAction(event -> player.start());
		tools.getItems().add(home);

		Button oneOff = new Button("OneOff");
		oneOff.setOnAction(event -> oneOff());
		tools.getItems().add(oneOff);

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
		allButEnd.setOnAction(event -> player.last());
		tools.getItems().add(allButEnd);

		return tools;
	}

	private void oneOff()
	{
		JfxUtility.capture(stage.getScene().getRoot());
		dumpNode(canvas, 0);
	}


	private void dumpNode(Node node, int indent)
	{
		String tabs = "";
		for (int i = 0; i < indent; i++)
			tabs += "\t";
		System.out.print(tabs + node.getClass().getSimpleName());
		System.out.print(" " + new PointPair(node.getBoundsInParent()));
		System.out.println();
		if (node instanceof Parent)
		{
			Parent parent = (Parent) node;
			for (Node child : parent.getChildrenUnmodifiable())
			{
				dumpNode(child, indent + 1);
			}
		}
	}

	private void beatChanged(Number beat)
	{
		String text = String.format("%8d", beat.longValue());
		if (beat.longValue() == 0) text = "   Start";
		if (beat.longValue() == Rhythm.MAX) text = "     End";
		final String newText = text;
		Platform.runLater(() -> timing.setText(newText));
	}

	private void undoFullScreen(Boolean newValue)
	{
		if (newValue == false)
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

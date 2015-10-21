package org.geepawhill.contentment;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application
{
	private Pane root;
	private Text hiMom;

	@Override
	public void start(Stage stage)
	{
		try
		{
			root = prepareStage(stage);
			String hiMomValue = "Hi Mom!";
			hiMom = new Text(400d, 400d, "");
			Transition transition = new Transition()
			{
				{
					setCycleDuration(Duration.millis(500));
				}

				@Override
				protected void interpolate(double frac)
				{
					String newText = hiMomValue.substring(0, (int)(frac * hiMomValue.length()));
					hiMom.setText(newText);

				}
			};
			root.getChildren().add(hiMom);
			transition.setOnFinished(event -> step2(event));
			transition.play();
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void step2(ActionEvent event)
	{
		Bounds hiMomBounds = hiMom.getBoundsInParent();
		
		Rectangle hiMomRectangle = new Rectangle();
		hiMomRectangle.setFill(Color.TRANSPARENT);
		hiMomRectangle.setStroke(Color.RED);
		hiMomRectangle.setX(hiMomBounds.getMinX());
		hiMomRectangle.setY(hiMomBounds.getMinY());
		Transition transition2 = new Transition() {
			{
				setCycleDuration(Duration.millis(500));
			}

			@Override
			protected void interpolate(double frac)
			{
				hiMomRectangle.setWidth(hiMomBounds.getWidth()*frac);
				hiMomRectangle.setHeight(hiMomBounds.getHeight()*frac);
			} };
		root.getChildren().add(hiMomRectangle);
		transition2.play();
	}

	private Pane prepareStage(Stage stage)
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

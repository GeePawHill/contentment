package org.geepawhill.contentment;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			Pane root = prepareStage(stage);
			Text hiMom = new Text(400d,400d,"Hi Mom!");
			Bounds hiMomBounds = hiMom.getBoundsInParent();
			Rectangle hiMomRectangle = new Rectangle();
			hiMomRectangle.setX(hiMomBounds.getMinX());
			hiMomRectangle.setY(hiMomBounds.getMinY());
			hiMomRectangle.setWidth(hiMomBounds.getWidth());
			hiMomRectangle.setHeight(hiMomBounds.getHeight());
			hiMomRectangle.setFill(Color.TRANSPARENT);
			hiMomRectangle.setStroke(Color.RED);
			root.getChildren().add(hiMom);
			root.getChildren().add(hiMomRectangle);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.exit();
		}
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

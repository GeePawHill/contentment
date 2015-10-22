package org.geepawhill.contentment;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EnterBoxedText implements Action
{

	private Text node;
	private String value;
	private Pane destination;
	private double centerY;
	private double centerX;
	private EventHandler<ActionEvent> onFinished;
	
	public EnterBoxedText(String value, double centerX, double centerY)
	{
		this.value = value;
		this.centerY = centerY;
		this.centerX = centerX;
	}

	@Override
	public void play(Pane destination, EventHandler<ActionEvent> onFinished)
	{
		this.destination = destination;
		this.onFinished = onFinished;
		node = new Text(centerX, centerY, "");
		Transition transition = new Transition()
		{
			{
				setCycleDuration(Duration.millis(500));
			}

			@Override
			protected void interpolate(double frac)
			{
				String newText = value.substring(0, (int)(frac * value.length()));
				node.setText(newText);

			}
		};
		destination.getChildren().add(node);
		transition.setOnFinished(event -> step2(event));
		transition.play();

	}
	
	private void step2(ActionEvent event)
	{
		Bounds hiMomBounds = node.getBoundsInParent();
		
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
		destination.getChildren().add(hiMomRectangle);
		transition2.setOnFinished(onFinished);
		transition2.play();
	}

}

package org.geepawhill.contentment;

import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EnterLabelledBox implements Action
{

	private Text label;
	private String value;
	private double centerY;
	private double centerX;
	private Rectangle rectangle;
	private Bounds bounds;
	private Pane destination;
	private SequentialTransition transition;

	public EnterLabelledBox(String value, double centerX, double centerY)
	{
		this.value = value;
		this.centerY = centerY;
		this.centerX = centerX;
		transition = new SequentialTransition();
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawText));
		transition.getChildren().add(new SimpleTransition(1, this::animateComputeBox));
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawBox));
	}

	@Override
	public void play(Pane destination, EventHandler<ActionEvent> onFinished)
	{
		if (transition.getStatus() != Status.PAUSED)
		{
			this.destination = destination;
			label = new Text(centerX, centerY, "");
			destination.getChildren().add(label);
			transition.setOnFinished(onFinished);
		}
		transition.play();
	}

	protected void animateDrawText(double frac)
	{
		String newText = value.substring(0, (int) (frac * value.length()));
		label.setText(newText);
	}

	protected void animateComputeBox(double frac)
	{
		bounds = label.getBoundsInParent();

		rectangle = new Rectangle();
		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setStroke(Color.RED);
		rectangle.setX(bounds.getMinX());
		rectangle.setY(bounds.getMinY());
		destination.getChildren().add(rectangle);
	}

	protected void animateDrawBox(double frac)
	{
		if (frac == 0d) return;
		rectangle.setWidth(bounds.getWidth() * frac);
		rectangle.setHeight(bounds.getHeight() * frac);
	}

	@Override
	public void pause()
	{
		if (transition.getStatus() == Status.RUNNING) transition.pause();
	}

}

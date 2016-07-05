package org.geepawhill.contentment;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LabelBoxStep implements Step {

	private String text;
	private double xCenter;
	private double yCenter;
	private Text label;
	private Rectangle rectangle;
	private Bounds bounds;
	private final List<Node> children;
	public SequentialTransition transition;

	public LabelBoxStep(String text, double xCenter, double yCenter) {
		children = new ArrayList<Node>();
		this.text = text;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		transition = new SequentialTransition();
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawText));
		transition.getChildren().add(new SimpleTransition(1, this::animateComputeBox));
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawBox));
	}

	@Override
	public void jumpAfter(Pane canvas) {
		label = new Text(xCenter, yCenter, "");
		rectangle = new Rectangle();
		ObservableList<Node> canvasChildren = canvas.getChildren();
		canvasChildren.add(label);
		canvasChildren.add(rectangle);
		children.add(label);
		children.add(rectangle);
		animateDrawText(1.0);
		animateComputeBox(1.0);
		animateDrawBox(1.0);
	}

	@Override
	public void jumpBefore(Pane canvas) {
		ObservableList<Node> canvasChildren = canvas.getChildren();
		for(Node node : children)
		{
			canvasChildren.remove(node);
		}
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
	
	protected void animateDrawText(double frac)
	{
		String newText = text.substring(0, (int) (frac * text.length()));
		label.setText(newText);
	}

	protected void animateComputeBox(double frac)
	{
		bounds = label.getBoundsInParent();

		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setStroke(Color.RED);
		rectangle.setX(bounds.getMinX());
		rectangle.setY(bounds.getMinY());
	}

	protected void animateDrawBox(double frac)
	{
		if (frac == 0d) return;
		rectangle.setWidth(bounds.getWidth() * frac);
		rectangle.setHeight(bounds.getHeight() * frac);
	}

}

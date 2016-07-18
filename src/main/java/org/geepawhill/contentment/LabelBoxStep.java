package org.geepawhill.contentment;

import javafx.animation.SequentialTransition;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LabelBoxStep implements Step {

	private String text;
	private Text label;
	private Rectangle rectangle;
	private Bounds bounds;
	public SequentialTransition transition;
	private final NodeKeeper keeper;

	public LabelBoxStep(String text, double xCenter, double yCenter) {
		keeper = new NodeKeeper();
		label = new Text(xCenter, yCenter, "");
		rectangle = new Rectangle();
		keeper.keep(label,rectangle);
		bounds = label.getBoundsInParent();
		this.text = text;
		transition = new SequentialTransition();
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawText));
		transition.getChildren().add(new SimpleTransition(1, this::animateComputeBox));
		transition.getChildren().add(new SimpleTransition(500, this::animateDrawBox));
	}

	@Override
	public void after(Context context) {
		transition.stop();
		keeper.addTo(context.canvas);
		animateDrawText(1.0);
		animateComputeBox(1.0);
		animateDrawBox(1.0);
	}

	@Override
	public void before(Context context) {
		transition.stop();
		keeper.removeFrom(context.canvas);
	}

	@Override
	public void play(Context context) {
		// TODO Auto-generated method stub
		keeper.addTo(context.canvas);
		transition.setOnFinished(context.onFinished);
		transition.playFromStart();
	}

	@Override
	public void pause(Context context) {
		transition.pause();

	}

	@Override
	public void resume(Context context) {
		transition.play();
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
		rectangle.setWidth(0d);
		rectangle.setHeight(0d);
	}

	protected void animateDrawBox(double frac)
	{
		rectangle.setWidth(bounds.getWidth() * frac);
		rectangle.setHeight(bounds.getHeight() * frac);
	}

}

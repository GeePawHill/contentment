package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.core.StyleId;

import javafx.animation.SequentialTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LabelBoxStep implements Step {

	private static final double VMARGIN = 8d;
	private static final double HMARGIN = 8d;
	private String text;
	private Text label;
	private Rectangle rectangle;
	private Bounds bounds;
	private final SequentialTransition transition;
	private final NodeKeeper keeper;

	public LabelBoxStep(String text, double xCenter, double yCenter) {
		keeper = new NodeKeeper();
		label = new Text(xCenter, yCenter, "");
		rectangle = new Rectangle();
		keeper.keep(label,rectangle);
		bounds = label.getBoundsInParent();
		this.text = text;
		transition = new SequentialTransition();
	}

	@Override
	public void after(Context context) {
		transition.stop();
		keeper.addTo(context.canvas);
		animateDrawText(1.0, context);
		animateComputeBox(1.0, context);
		animateDrawBox(1.0, context);
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
		transition.getChildren().clear();
		transition.getChildren().add(new ContextTransition(context,500, this::animateDrawText));
		transition.getChildren().add(new ContextTransition(context,1, this::animateComputeBox));
		transition.getChildren().add(new ContextTransition(context,500, this::animateDrawBox));
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

	protected void animateDrawText(double frac, Context context)
	{
		context.styles.get(StyleId.Font).apply(label);
		context.styles.get(StyleId.LineColor).apply(label);
		String newText = text.substring(0, (int) (frac * text.length()));
		label.setText(newText);
	}

	protected void animateComputeBox(double frac, Context context)
	{
		bounds = label.getBoundsInParent();
		bounds = new BoundingBox(bounds.getMinX()-HMARGIN,bounds.getMinY()-VMARGIN,bounds.getWidth()+2*HMARGIN,bounds.getHeight()+2*VMARGIN);
		rectangle.setFill(Color.TRANSPARENT);
		context.styles.get(StyleId.LineColor).apply(rectangle);
		context.styles.get(StyleId.PenWidth).apply(rectangle);
		rectangle.setX(bounds.getMinX());
		rectangle.setY(bounds.getMinY());
		rectangle.setWidth(0d);
		rectangle.setHeight(0d);
		rectangle.setVisible(false);
	}

	protected void animateDrawBox(double frac, Context context)
	{
		rectangle.setWidth(bounds.getWidth() * frac);
		rectangle.setHeight(bounds.getHeight() * frac);
		if(frac!=0d) rectangle.setVisible(true);
	}

	@Override
	public boolean isMarked()
	{
		return true;
	}

}

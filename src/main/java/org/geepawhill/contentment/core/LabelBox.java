package org.geepawhill.contentment.core;

import org.geepawhill.contentment.step.InterpolatedStep;
import org.geepawhill.contentment.step.NodeKeeper;
import org.geepawhill.contentment.step.SubStep;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LabelBox implements Actor
{
	final String text;

	private static final double VMARGIN = 8d;
	private static final double HMARGIN = 8d;
	private Text label;
	private Rectangle rectangle;
	private Bounds bounds;
	private final NodeKeeper keeper;

	public LabelBox(String text, double xCenter, double yCenter)
	{
		this.text = text;
		keeper = new NodeKeeper();
		label = new Text(xCenter, yCenter, "");
		rectangle = new Rectangle();
		keeper.keep(label, rectangle);
		bounds = label.getBoundsInParent();
	}

	public Step sketch(double ms)
	{
		SubStep[] interpolators = new SubStep[]
		{
				new SubStep(500d,this::animateDrawText),
				new SubStep(1d,this::animateComputeBox), 
				new SubStep(200d,this::animateDrawBox)
		};
		return new InterpolatedStep(keeper, ms, interpolators);
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
		bounds = new BoundingBox(bounds.getMinX() - HMARGIN, bounds.getMinY() - VMARGIN, bounds.getWidth() + 2 * HMARGIN,
				bounds.getHeight() + 2 * VMARGIN);
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
		if (frac != 0d) rectangle.setVisible(true);
	}

}

package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.geometry.PointPair;

import javafx.scene.shape.Line;

public class StrokeHelper
{

	public static class Stroker
	{
		private Line line;
		private BoundsComputer computer;

		public Stroker(Line line, BoundsComputer computer)
		{
			this.line = line;
			this.line.setVisible(false);
			this.computer = computer;
		}

		protected void animateDrawLine(double fraction, Context context)
		{
			if (fraction == 0d) return;
			PointPair points = computer.compute();
			context.styles.get(StyleId.LineColor).apply(line);
			context.styles.get(StyleId.PenWidth).apply(line);
			context.styles.get(StyleId.Opacity).apply(line);
			line.setStartX(points.from.x);
			line.setStartY(points.from.y);
			line.setEndX(points.partialX(fraction));
			line.setEndY(points.partialY(fraction));
			line.setVisible(true);
		}
	}

	@FunctionalInterface
	public interface BoundsComputer
	{
		PointPair compute();
	}

	static public SubStep makeSubStep(Line line, double ratio, BoundsComputer computer)
	{
		Stroker stroker = new Stroker(line, computer);
		return new SubStep(ratio, stroker::animateDrawLine);
	}

}

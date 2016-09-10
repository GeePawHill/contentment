package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.StyleId;

import javafx.geometry.Bounds;
import javafx.scene.shape.Line;

public class StrokeHelper
{

	static private class Stroker
	{
		private double fromX;
		private double fromY;
		private double toX;
		private double toY;
		private Line line;
		private BoundsComputer computer;

		public Stroker(Line line, BoundsComputer computer)
		{
			this.line = line;
			this.computer = computer;
		}

		public Stroker(Line line, double fromX, double fromY, double toX, double toY)
		{
			this.line = line;
			this.fromX = fromX;
			this.fromY = fromY;
			this.toX = toX;
			this.toY = toY;

		}

		protected void animateDrawLine(double frac, Context context)
		{
			if (frac == 0d)
			{
				line.setVisible(false);
				return;
			}

			if (computer != null)
			{
				Bounds bounds = computer.compute();
				fromX = bounds.getMinX();
				fromY = bounds.getMinY();
				toX = bounds.getMaxX();
				toY = bounds.getMaxY();
			}
			context.styles.get(StyleId.LineColor).apply(line);
			context.styles.get(StyleId.PenWidth).apply(line);
			context.styles.get(StyleId.Opacity).apply(line);
			double newX = fromX + (toX - fromX) * frac;
			double newY = fromY + (toY - fromY) * frac;
			line.setStartX(fromX);
			line.setStartY(fromY);
			line.setEndX(newX);
			line.setEndY(newY);
			if (frac == 0d) return;
			line.setVisible(true);
		}
	}

	static public SubStep makeSubStep(Line line, double ratio, double fromX, double fromY, double toX, double toY)
	{
		Stroker stroker = new Stroker(line, fromX, fromY, toX, toY);
		return new SubStep(ratio, stroker::animateDrawLine);
	}

	@FunctionalInterface
	public interface BoundsComputer
	{
		Bounds compute();
	}

	static public SubStep makeSubStep(Line line, double ratio, BoundsComputer computer)
	{
		Stroker stroker = new Stroker(line, computer);
		return new SubStep(ratio, stroker::animateDrawLine);
	}

}

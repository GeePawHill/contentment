package org.geepawhill.contentment.jfx;

import java.util.ArrayList;

import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class BezierInterpolator
{
	public static class Segment
	{
		public Point move;
		public boolean finished;

		public Segment(Point move)
		{
			this.move = move;
			this.finished = false;
		}
	}

	private Path path;
	public ArrayList<Segment> segments;
	private double fractionPerStep;

	public BezierInterpolator(Path path)
	{
		this.path = path;
	}

	public void clear(Point start)
	{
		path.getElements().clear();
		path.getElements().add(new MoveTo(start.x, start.y));
		segments = new ArrayList<>();
	}

	public void addCurve(Point[] controls, int count)
	{
		addCurve(controls, count, new Jiggler());
	}

	public void addCurve(Point[] controls, int count, Jiggler jiggler)
	{
		/* Compute polynomial coefficients from Bezier points */

		double ax = -controls[0].x + 3 * controls[1].x + -3 * controls[2].x + controls[3].x;
		double ay = -controls[0].y + 3 * controls[1].y + -3 * controls[2].y + controls[3].y;

		double bx = 3 * controls[0].x + -6 * controls[1].x + 3 * controls[2].x;
		double by = 3 * controls[0].y + -6 * controls[1].y + 3 * controls[2].y;

		double cx = -3 * controls[0].x + 3 * controls[1].x;
		double cy = -3 * controls[0].y + 3 * controls[1].y;

		double dx = controls[0].x;
		double dy = controls[0].y;

		/* Set up the number of steps and step size */

		int numSteps = count - 1; // arbitrary choice
		double h = 1.0 / (double) numSteps; // compute our step size

		/* Compute forward differences from Bezier points and "h" */

		Point cubic = new Point(dx,dy);

		double firstFDX = ax * (h * h * h) + bx * (h * h) + cx * h;
		double firstFDY = ay * (h * h * h) + by * (h * h) + cy * h;
		Point cubicDifference = new Point(firstFDX,firstFDY);

		double secondFDX = 6 * ax * (h * h * h) + 2 * bx * (h * h);
		double secondFDY = 6 * ay * (h * h * h) + 2 * by * (h * h);
		Point quadraticDifference = new Point(secondFDX,secondFDY);

		double thirdFDX = 6 * ax * (h * h * h);
		double thirdFDY = 6 * ay * (h * h * h);
		Point linearDifference = new Point(thirdFDX,thirdFDY);

		for (int i = 0; i < numSteps; i++)
		{
			cubic = cubic.add(cubicDifference);
			cubicDifference = cubicDifference.add(quadraticDifference);
			quadraticDifference = quadraticDifference.add(linearDifference);
			segments.add(new Segment(jiggler.jiggle(cubic)));
		}
		fractionPerStep = 1d / (double) segments.size();
	}

	public void interpolate(double fraction)
	{
		for (int i = 0; i < segments.size(); i++)
		{
			Segment segment = segments.get(i);
			if (segment.finished) continue;
			double segmentFraction = ((double) i) * fractionPerStep;
			path.getElements().add(new LineTo(segment.move.x, segment.move.y));
			segment.finished = true;
			if (segmentFraction > fraction) break;
		}
	}
	
	public void interpolate2(double fraction)
	{
		
	}
}

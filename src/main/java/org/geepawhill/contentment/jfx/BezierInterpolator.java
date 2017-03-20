package org.geepawhill.contentment.jfx;

import java.util.ArrayList;

import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class BezierInterpolator
{
	public static class Segment
	{
		public Point move;
		public double fraction;
		public boolean finished;

		public Segment(Point move,double fraction)
		{
			this.move = move;
			this.fraction = fraction;
			this.finished=false;
		}
	}

	private Path path;
	private ArrayList<Segment> segments;

	public BezierInterpolator(Path path)
	{
		this.path = path;
	}

	public void clear(PointPair points)
	{
		path.getElements().clear();
		path.getElements().add(new MoveTo(points.from.x,points.from.y));
		segments = new ArrayList<>();
	}
	
	public void addCurve(Point[] controls, int count)
	{
		addCurve(controls,count,new Jiggler());
	}


	public void addCurve(Point[] controls, int count, Jiggler jiggler)
	{
		double ax, ay, bx, by, cx, cy, dx, dy;
	    int numSteps, i;
	    double h;
	    double pointX, pointY;
	    double firstFDX, firstFDY;
	    double secondFDX, secondFDY;
	    double thirdFDX, thirdFDY;
	
	        /* Compute polynomial coefficients from Bezier points */
	
	    ax = -controls[0].x + 3 * controls[1].x + -3 * controls[2].x + controls[3].x;
	    ay = -controls[0].y + 3 * controls[1].y + -3 * controls[2].y + controls[3].y;
	
	    bx = 3 * controls[0].x + -6 * controls[1].x + 3 * controls[2].x;
	    by = 3 * controls[0].y + -6 * controls[1].y + 3 * controls[2].y;
	
	    cx = -3 * controls[0].x + 3 * controls[1].x;
	    cy = -3 * controls[0].y + 3 * controls[1].y;
	
	    dx = controls[0].x;
	    dy = controls[0].y;
	
	        /* Set up the number of steps and step size */
	
	    numSteps = count - 1;        //    arbitrary choice
	    h = 1.0 / (double) numSteps;    //    compute our step size
	
	        /* Compute forward differences from Bezier points and "h" */
	
	    pointX = dx;
	    pointY = dy;
	
	    firstFDX = ax * (h * h * h) + bx * (h * h) + cx * h;
	    firstFDY = ay * (h * h * h) + by * (h * h) + cy * h;
	
	
	    secondFDX = 6 * ax * (h * h * h) + 2 * bx * (h * h);
	    secondFDY = 6 * ay * (h * h * h) + 2 * by * (h * h);
	
	    thirdFDX = 6 * ax * (h * h * h);
	    thirdFDY = 6 * ay * (h * h * h);    
	
	
	    for (i = 0; i < numSteps; i++) {
	
	        pointX += firstFDX;
	        pointY += firstFDY;
	
	        firstFDX += secondFDX;
	        firstFDY += secondFDY;
	
	        secondFDX += thirdFDX;
	        secondFDY += thirdFDY;
	        segments.add(new Segment(jiggler.jiggle(new Point(pointX,pointY)),segments.size()*h));
	    }
	}

	public void interpolate(double fraction)
	{
		for(Segment segment : segments)
		{
			if(segment.finished) continue;
			path.getElements().add(new LineTo(segment.move.x,segment.move.y));
			segment.finished=true;
			if(segment.fraction>fraction) break;
		}
	}

}

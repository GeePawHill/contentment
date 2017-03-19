package org.geepawhill.contentment.step;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.ShapeStep;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class HandStep implements ShapeStep
{
	
	static class Segment
	{
		public Point move;
		public double fraction;
		boolean finished;

		public Segment(Point move,double fraction)
		{
			this.move = move;
			this.fraction = fraction;
			this.finished=false;
		}
	}
	
	private Timing timing;
	private PointPair points;
	private final Path line;
	private Transition transition;
	private Format format;
	private ArrayList<Segment> segments;
	private Random random;
	
	public HandStep(Timing timing, PointPair points, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.line = new Path();
		this.format = format;
		this.random = new Random();
	}
	
	public void setPoints(PointPair points)
	{
		this.points = points;
		format.apply(Frames.KEY, line);
		format.apply(Dash.KEY, line);
		line.getElements().clear();
		line.getElements().add(new MoveTo(points.from.x,points.from.y));
		Point[] controls = chooseControlPoints();
		segments = makeSegments(controls,(int)(points.distance()*.2d));
	}
	
	@Override
	public Shape shape()
	{
		return line;
	}

	@Override
	public void after(Context context)
	{
		interpolate(1d,context);
	}

	@Override
	public void before(Context context)
	{
		interpolate(0d,context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition( context,this::interpolate,timing().getAbsolute());
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public void pause(Context context)
	{
		transition.pause();
	}

	@Override
	public void resume(Context context)
	{
		transition.play();
	}

	@Override
	public Timing timing()
	{
		return timing;
	}
	
	private void interpolate(double fraction, Context context)
	{
		if(fraction==0d)
		{
			line.setVisible(false);
		}
		else
			line.setVisible(true);
		
		for(Segment segment : segments)
		{
			if(segment.finished) continue;
			line.getElements().add(new LineTo(segment.move.x,segment.move.y));
			segment.finished=true;
			if(segment.fraction>fraction) break;
		}
	}
	
	private ArrayList<Segment> makeSegments(Point[] controls, int numPoints)
	{
		ArrayList<Segment> result = new ArrayList<>();
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

	    numSteps = numPoints - 1;        //    arbitrary choice
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

	        /* Compute points at each step */

//	    vertices[0].x = (int)pointX;
//	    vertices[0].y = (int)pointY;

	    for (i = 0; i < numSteps; i++) {

	        pointX += firstFDX;
	        pointY += firstFDY;

	        firstFDX += secondFDX;
	        firstFDY += secondFDY;

	        secondFDX += thirdFDX;
	        secondFDY += thirdFDY;
	        result.add(new Segment(new Point(pointX,pointY),numSteps*h));

	    }
	    return result;
	}


	public Point[] chooseControlPoints()
	{
		Point[] result = new Point[] {
				points.from,
				points.partial(random.nextDouble()).jiggle(random, 1d, 10),
				points.partial(random.nextDouble()).jiggle(random, 1d, 10),
				points.to
		};
		return result;
	}

	public Node node()
	{
		return line;
	}
}

package org.geepawhill.contentment.core;

import org.geepawhill.contentment.step.StrokeHelper;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Arrow implements Actor
{
	private Group group;
	private double fromX;
	private double fromY;
	private double toX;
	private double toY;
	private Line line;
	private Line arrowLeft;
	private Line arrowRight;
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private double xm;
	private double xn;
	private double ym;
	private double yn;

	public Arrow(double fromX,double fromY,double toX,double toY)
	{
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.group = new Group();
		this.line = new Line(fromX,fromY,fromX,fromY);
		this.arrowLeft = new Line(toX,toY,toX,toY);
		this.arrowRight = new Line(toX,toY,toX,toY);
		group.getChildren().addAll(line,arrowLeft,arrowRight);
	}
	
	public Step sketch(double ms)
	{
		double x1=fromX;
		double x2=toX;
		double y1=fromY;
		double y2=toY;
		double d = 14;
		double h = 10;
		double dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		SubStep[] substeps = new SubStep[]
		{
				new SubStep(1d,this::animateComputePoints),
				StrokeHelper.makeSubStep(line,80d,this::computeMain),
				StrokeHelper.makeSubStep(arrowLeft, 10d,this::computeFirst),
				StrokeHelper.makeSubStep(arrowRight, 10d, this::computeSecond)
		};
		return new TimedSequence(ms, group, substeps);
	}
	
	private void animateComputePoints(double frac,Context context)
	{
		if(frac==0d) return;
		x1 = fromX;
		x2 = toX;
		y1 = fromY;
		y2 = toY;
		double d = 14;
		double h = 10;
		double dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		xm = D - d;
		xn = xm;
		ym = h;
		yn = -h;
		double x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
	}
	
	public Bounds computeMain()
	{
		return new BoundingBox(fromX,fromY,toX-fromX,toY-fromY);
	}
	
	public Bounds computeFirst()
	{
		return new BoundingBox(toX,toY,xm-toX,ym-toY);
	}
	
	public Bounds computeSecond()
	{
		return new BoundingBox(toX,toY,xn-toX,yn-toY);
	}

	@Override
	public Group group()
	{
		return group;
	}
}

package org.geepawhill.contentment.core;

import org.geepawhill.contentment.step.StrokeHelper;
import org.geepawhill.contentment.step.SubStep;
import org.geepawhill.contentment.step.TimedSequence;

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

	public Arrow(double fromX,double fromY,double toX,double toY)
	{
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.group = new Group();
		this.line = new Line(fromX,fromY,fromX,fromY);
		this.arrowLeft = new Line(toX,toY,toX,toY);
//		arrowLeft.setBlendMode(BlendMode.SRC_OVER);
//		arrowRight.setBlendMode(BlendMode.SRC_OVER);
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
				StrokeHelper.makeSubStep(line,80d,x1,y1,x2, y2),
				StrokeHelper.makeSubStep(arrowLeft, 10d,x2,y2,xm, ym),
				StrokeHelper.makeSubStep(arrowRight, 10d, x2, y2, xn, yn)
		};
		return new TimedSequence(ms, group, substeps);
	}
}

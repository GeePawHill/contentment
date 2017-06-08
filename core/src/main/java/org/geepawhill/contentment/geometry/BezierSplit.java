package org.geepawhill.contentment.geometry;

public class BezierSplit
{
	public final double fraction;
	public final Bezier bezier;
	public final Bezier before;
	public final Bezier after;

	public BezierSplit(double t, Bezier bezier)
	{
		this.fraction = t;
		this.bezier = bezier;
		double oneMinusT = 1d - t;
		// P0_1 = (1-t)*P0 + t*P1
		// P1_2 = (1-t)*P1 + t*P2
		// P2_3 = (1-t)*P2 + t*P3
		Point P0_1 = multiplyOneMinusTAndT(bezier.start,bezier.handle1);
		Point P1_2 = multiplyOneMinusTAndT(bezier.handle1, bezier.handle2);
		Point P2_3 = multiplyOneMinusTAndT(bezier.handle2, bezier.end);

		// P01_12 = (1-t)*P0_1 + t*P1_2
		Point P01_12 = multiplyOneMinusTAndT(P0_1, P1_2);
		// P12_23 = (1-t)*P1_2 + t*P2_3
		Point P12_23 = multiplyOneMinusTAndT(P1_2, P2_3);

		// P0112_1223 = (1-t)*P01_12 + t*P12_23
		Point P0112_1223 = multiplyOneMinusTAndT(P01_12,P12_23);

		this.before = new Bezier(bezier.start, P0_1, P01_12, P0112_1223);
		this.after = new Bezier(P0112_1223, P12_23, P2_3, bezier.end);
	}
	
	private Point multiplyOneMinusTAndT(Point first, Point second)
	{
		double oneMinusT = 1d-fraction;
		return new Point(
				oneMinusT * first.x + fraction * second.x,
				oneMinusT * first.y + fraction * second.y
				);

	}
}
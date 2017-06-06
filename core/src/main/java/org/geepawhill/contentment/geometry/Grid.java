package org.geepawhill.contentment.geometry;

public class Grid
{
	
	private double xCells;
	private double yCells;
	private double xInset;
	private double yInset;
	private double xWidth;
	private double yHeight;

	public Grid(int xCells, int yCells)
	{
		this(xCells,yCells,0,0);
	}

	public Grid(int xCells, int yCells, double xInset, double yInset)
	{
		this.xCells = xCells;
		this.yCells = yCells;
		this.xInset = xInset;
		this.yInset = yInset;
		this.xWidth = (ViewPort.WIDTH - 2*xInset)/xCells;
		this.yHeight = (ViewPort.HEIGHT-2*yInset)/yCells;
	}

	public int xCells()
	{
		return (int)xCells;
	}
	
	public int yCells()
	{
		return (int)yCells;
	}

	public double xWidth()
	{
		return xWidth;
	}

	public double yHeight()
	{
		return yHeight;
	}

	public Point center(int x, int y)
	{
		return area(x,y).center();
	}
	
	public PointPair area(int x,int y,int width,int height)
	{
		Point from = new Point(xInset+xWidth*x,yInset+yHeight*y);
		Point to = new Point(xInset+
				xWidth*x+
				xWidth*width,
				yInset+yHeight*y+
				yHeight*height);
		return new PointPair(from,to);
	}

	public PointPair area(int x,int y)
	{
		return area(x,y,1,1);
	}

}

package org.geepawhill.contentment.geometry;

public class Grid
{

	private int columns;
	private int rows;
	private double cellWidth;
	private double rowHeight;
	private PointPair area;
	
	public Grid(int columns,int rows,PointPair area)
	{
		this.columns=columns;
		this.rows=rows;
		this.cellWidth = area.width()/columns;
		this.rowHeight = area.height()/rows;
		this.area = area;
	}

	public Grid(int columns, int rows)
	{
		this(columns,rows,new PointPair(0,0,ViewPort.WIDTH,ViewPort.HEIGHT));
	}

	public Grid(int columns, int rows, double xInset, double yInset)
	{
		this(columns,rows,new PointPair(xInset,yInset,ViewPort.WIDTH-xInset,ViewPort.HEIGHT-yInset));
	}

	public int columns()
	{
		return columns;
	}

	public int rows()
	{
		return rows;
	}

	public double cellWidth()
	{
		return cellWidth;
	}

	public double cellHeight()
	{
		return rowHeight;
	}

	public Point center(int x, int y)
	{
		return area(x, y).center();
	}

	public PointPair area(int x, int y, int width, int height)
	{
		Point from = new Point(area.from.x + cellWidth * x, area.from.y + rowHeight * y);
		Point to = new Point(area.from.x + cellWidth * x + cellWidth * width, area.from.y + rowHeight * y + rowHeight * height);
		return new PointPair(from, to);
	}

	public PointPair area(int x, int y)
	{
		return area(x, y, 1, 1);
	}

}

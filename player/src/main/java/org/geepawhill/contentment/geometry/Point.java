package org.geepawhill.contentment.geometry;

import java.util.Random;

public class Point 
{
	public final double x;
	public final double y;
	
	public Point(double x,double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "(%1$.1f, %2$.1f)", x, y);
	}

	public double xDistance(Point to)
	{
		return to.x - x;
	}

	public double yDistance(Point to)
	{
		return to.y - y;
	}	
	
	public Point jiggle(Random random, double probability, double variance)
	{
		double newX = x;
		double newY = y;
		if(random.nextDouble()<probability)
		{
			double sign = random.nextDouble()>.5 ? -1 : +1;
			double change = random.nextDouble()*variance; 
			newX += sign*change;
		}
		if(random.nextDouble()<probability)
		{
			double sign = random.nextDouble()>.5 ? -1 : +1;
			double change = random.nextDouble()*variance; 
			newY += sign*change;
		}
		return new Point(newX,newY);
	}

	public Point add(double xmove, double ymove)
	{
		return new Point(x+xmove,y+ymove);
	}

	public Point add(Point add)
	{
		return add(add.x,add.y);
	}

}

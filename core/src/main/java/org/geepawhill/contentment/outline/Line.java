package org.geepawhill.contentment.outline;

public class Line<T>
{
	public final int indent;
	public final T data;
	
	public Line(int indent,T data)
	{
		this.indent = indent;
		this.data = data;
	}
}
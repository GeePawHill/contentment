package org.geepawhill.contentment.tree;

public class Appendee<T>
{
	public final int indent;
	public final T data;
	
	public Appendee(int indent,T data)
	{
		this.indent = indent;
		this.data = data;
	}
}
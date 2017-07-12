package org.geepawhill.contentment.core;

public interface Atom
{
	public void setup(Context context);
	public void partial(Context context, double fraction);
}
package org.geepawhill.contentment.core;

public interface Atom
{
	public void setup(Context context);
	public boolean partial(Context context, double fraction);
}
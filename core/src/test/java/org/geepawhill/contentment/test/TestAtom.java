package org.geepawhill.contentment.test;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class TestAtom implements Atom {
	public ArrayList<Double> fractions;
	public TestAtom()
	{
		fractions = new ArrayList<>();
	}

	@Override
	public void setup(Context context)
	{
		fractions.add(0d);
	}
	
	@Override
	public boolean partial(Context context, double fraction)
	{
		fractions.add(fraction);
		return true;
	}
}
package org.geepawhill.contentment.style;

import java.util.Arrays;
import java.util.List;

public class Dash
{
	public static final String KEY = "Dash";
	
	public final List<Double> array;
	
	private Dash(Double... array)
	{
		this.array = Arrays.asList(array);
	}
	
	public static Dash solid()
	{
		return new Dash(new Double[0]);
	}
	
	public static Dash dash(Double... dash)
	{
		return new Dash(dash);
	}

}

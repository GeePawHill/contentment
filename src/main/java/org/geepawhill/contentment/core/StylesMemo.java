package org.geepawhill.contentment.core;

import java.util.HashMap;

public class StylesMemo
{
	HashMap<StyleId, Style> stash;

	public StylesMemo(HashMap<StyleId, Style> styles)
	{
		stash = styles;
	}
}
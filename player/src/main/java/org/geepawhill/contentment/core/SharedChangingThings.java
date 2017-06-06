package org.geepawhill.contentment.core;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SharedChangingThings
{

	Format agentFormat()
	{
		Style agentColors = TypeFace.color("Agent", Color.CHARTREUSE, .8d);
		Style agentFont = TypeFace.largeHand();
		Style agentFrame = Frames.frame(Color.CHARTREUSE, 5d, .8d);
		Format agentFormat = new Format("Agent", agentColors, agentFont, agentFrame);
		return agentFormat;
	}

	Format targetFormat()
	{
		return new Format(Frames.frame(Color.WHITE, 2d, .8d, Dash.dash(4d,4d)), TypeFace.mediumSans(), TypeFace.color("Target", Color.WHITE, .8d));
	}

	Format coachFormat()
	{
		return new Format("Coach", agentFormat(), TypeFace.color("Coach", Color.LIGHTSKYBLUE, .8d),
				Frames.frame(Color.LIGHTSKYBLUE, 5d, .8d));
	}

	Format pokeFormat(Paint paint)
	{
		return new Format(pokeFormat(), Frames.frame(paint, 5d, .8d));
	}

	Format pokeFormat()
	{
		return new Format("Poke", agentFormat(), Frames.frame(Color.SLATEBLUE, 5d, .8d));
	}

	Format practiceFormat()
	{
		return new Format(Frames.frame(Color.TOMATO, 4d, .8d, Dash.dash(4d)), TypeFace.color("Practice", Color.TOMATO, .8d),
				TypeFace.largeHand());
	}

	Format relationFormat()
	{
		return new Format(Frames.frame(Color.LIGHTCORAL, 2d, .8d, Dash.dash(10d)));
	}

	Format changeFormat()
	{
		return new Format(Frames.frame(Color.ANTIQUEWHITE, 5d, .8d, Dash.dash(10d)));
	}

}

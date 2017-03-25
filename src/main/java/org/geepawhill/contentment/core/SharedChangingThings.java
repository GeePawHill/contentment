package org.geepawhill.contentment.core;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TextColors;
import org.geepawhill.contentment.style.TextFont;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SharedChangingThings
{

	Format agentFormat()
	{
		Style agentColors = TextColors.color("Agent", Color.CHARTREUSE, .8d);
		Style agentFont = TextFont.largeHand();
		Style agentFrame = Frames.frame("Agent", Color.CHARTREUSE, 5d, .8d);
		Style agentDash = Dash.solid();
		Format agentFormat = new Format("Agent", agentColors, agentFont, agentFrame, agentDash);
		return agentFormat;
	}

	Format targetFormat()
	{
		return new Format(Frames.frame("Target", Color.WHITE, 2d, .8d), TextFont.mediumSans(),
				TextColors.color("Target", Color.WHITE, .8d), Dash.dash("dash", 5d));
	}

	Format coachFormat()
	{
		return new Format("Coach", agentFormat(), TextColors.color("Coach", Color.LIGHTSKYBLUE, .8d),
				Frames.frame("Coach", Color.LIGHTSKYBLUE, 5d, .8d));
	}

	Format pokeFormat(Paint paint)
	{
		return new Format(pokeFormat(), Frames.frame("Poke", paint, 5d, .8d));
	}

	Format pokeFormat()
	{
		return new Format("Poke", agentFormat(), Dash.solid(), Frames.frame("Coach", Color.SLATEBLUE, 5d, .8d));
	}

	Format practiceFormat()
	{
		return new Format(Frames.frame("Practice", Color.TOMATO, 4d, .8d), TextColors.color("Practice", Color.TOMATO, .8d),
				Dash.dash("dash", 4d), TextFont.largeHand());
	}

	Format relationFormat()
	{
		return new Format(Frames.frame("Change", Color.LIGHTCORAL, 2d, .8d), Dash.dash("Relationship", 10d));
	}

	Format changeFormat()
	{
		return new Format(Frames.frame("Change", Color.ANTIQUEWHITE, 5d, .8d), Dash.dash("Change", 10d));
	}

}

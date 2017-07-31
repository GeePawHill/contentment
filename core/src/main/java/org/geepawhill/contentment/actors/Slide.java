package org.geepawhill.contentment.actors;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.util.ArrayList;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.atom.RemoveAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.position.BelowRight;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.position.TopRight;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Slide extends GenericActor
{

	private final Format majorFormat;
	private final Format subFormat;
	private final Format minorFormat;
	private final Group group;
	private final ArrayList<LettersAtom> lines;

	public Slide(ScriptWorld world)
	{
		super(world);
		this.group = new Group();
		this.lines = new ArrayList<>();

		Paint majorColor = color(13, 165, 15);
		Font majorHand = Font.font("Chewed Pen BB", FontPosture.ITALIC, 90d);
		majorFormat = new Format(TypeFace.font(majorHand, 3d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));

		Paint subColor = color(163, 232, 78);
		Font subHand = Font.font("Chewed Pen BB", FontPosture.ITALIC, 68d);
		subFormat = new Format(majorFormat, TypeFace.font(subHand, 3d, 1d), TypeFace.color(subColor, 1d),
				Frames.frame(subColor, 5d, 1d));

		Paint minorColor = color(240, 255, 30);
		Font minorFont = Font.font("Chewed Pen BB", FontPosture.ITALIC, 50d);
		minorFormat = new Format(TypeFace.font(minorFont, 1d, 1d), TypeFace.color(minorColor, 1d),
				Frames.frame(minorColor, 5d, 1d));

	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		throw new RuntimeException("Attempt to draw Slide actor.");
	}

	public Slide head(String source)
	{
		wipe();
		line(source, majorFormat);
		return this;
	}

	public Slide sub(String source)
	{
		line(source, subFormat);
		return this;
	}

	public Slide lead(String source)
	{
		line(source, majorFormat);
		return this;
	}

	public Slide minor(String source)
	{
		line(source, minorFormat);
		return this;
	}

	void line(String text, Format format)
	{
		Position position = null;
		if (!lines.isEmpty()) position = new BelowRight(groupSource());
		else position = new TopRight(1550d, 50d);
		LettersAtom line = new LettersAtom(groupSource(), text, format, position);
		world.add(new AtomStep(Timing.ms(500), line));
		lines.add(line);
	}

	public Slide wipe()
	{
		for (LettersAtom line : lines)
		{
			world.add(new AtomStep(Timing.instant(), new RemoveAtom(groupSource(), () -> line.text())));
		}
		lines.clear();
		return this;
	}

	public Slide enter()
	{
		world.add(new AtomStep(Timing.instant(), new EntranceAtom(groupSource())));
		return this;
	}

}

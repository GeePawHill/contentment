package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.ActorBuilderBase;
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

import static org.geepawhill.contentment.utility.JfxUtility.*;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Slide implements Actor<Slide.Builder>
{
	
	private final Format majorFormat;
	private final Format subFormat;
	private final Format minorFormat;
	private final Group group;
	private final ArrayList<LettersAtom> lines;

	public Slide()
	{
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
	public String nickname()
	{
		return "Slide";
	}

	@Override
	public Step draw(double ms)
	{
		throw new RuntimeException("Attempt to draw Slide actor.");
	}

	@Override
	public Builder builder(ScriptWorld world)
	{
		return new Builder(world);
	}

	public class Builder extends ActorBuilderBase<Slide,Builder>
	{
		private ScriptWorld world;

		public Builder(ScriptWorld world)
		{
			super(world, Slide.this);
			this.world = world;
		}
		
		public Builder head(String source)
		{
			wipe();
			line(source,majorFormat);
			return this;
		}
		
		public Builder sub(String source)
		{
			line(source,subFormat);
			return this;
		}
		
		public Builder lead(String source)
		{
			line(source,majorFormat);
			return this;
		}
		
		public Builder minor(String source)
		{
			line(source,minorFormat);
			return this;
		}
		
		void line(String text, Format format)
		{
			Position position = null;
			if(!lines.isEmpty()) position=new BelowRight(group);
			else position = new TopRight(1550d,50d);
			LettersAtom line = new LettersAtom(group,text,format,position);
			world.add(new AtomStep(Timing.ms(500),line));
			lines.add(line);
		}
		
		public Builder wipe()
		{
			for(LettersAtom line : lines)
			{
				world.add(new AtomStep(Timing.instant(),new RemoveAtom(group, () -> line.text())));
			}
			lines.clear();
			return this;
		}

		
		@Override
		public Builder downcast()
		{
			return this;
		}

		public Builder enter()
		{
			world.add(new AtomStep(Timing.instant(),new EntranceAtom(Slide.this)));
			return this;
		}
	}



}

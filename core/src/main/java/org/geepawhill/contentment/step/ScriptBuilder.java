package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actor.CueBuilder;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.ChangeColorAtom;
import org.geepawhill.contentment.atom.ClearAtom;
import org.geepawhill.contentment.atom.EntranceAtom;
import org.geepawhill.contentment.atom.ExitAtom;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.atom.OpacityAtom;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.paint.Paint;

public class ScriptBuilder
{
	
	protected ScriptWorld world;
	private CueBuilder cueBuilder;

	public ScriptBuilder()
	{
		world = new ScriptWorld();
	}
	
	public CueBuilder cue(long beat)
	{
		addToWorking(new AtomStep(Timing.ms(5000),new MarkAtom(beat*1000)));
//		addToWorking(new MarkStep(beat));
		cueBuilder = new CueBuilder(world,beat);
		return and();
	}
	
	public CueBuilder and()
	{
		return cueBuilder;
	}
	
	public Step clear()
	{
		AtomStep step = new AtomStep(Timing.instant(),new ClearAtom());
		addToWorking(step);
		return step;
	}
	
	public Step reColor(Actor actor, Paint paint)
	{
		AtomStep step = new AtomStep(Timing.instant(),new ChangeColorAtom(actor,paint));
		addToWorking(step);
		return step;
	}

	public Step sketch(double ms, Actor drawable)
	{
		Phrase phrase = new Phrase();
		phrase.add(new AtomStep(Timing.instant(),new EntranceAtom(drawable)));
		phrase.add(drawable.draw(ms));
		addToWorking(phrase);
		return phrase;
	}

	public Step appear(Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new AtomStep(Timing.instant(),new EntranceAtom(drawable)));
		result.add(drawable.draw(1d));
		addToWorking(result);
		return result;
	}

	public Step disappear(Actor drawable)
	{
		AtomStep step = new AtomStep(Timing.instant(),new ExitAtom(drawable));
		addToWorking(step);
		return step;
	}

	public Step fadeIn(double ms, Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new AtomStep(Timing.instant(),new EntranceAtom(drawable)));
		result.add(new AtomStep(Timing.ms(1), new OpacityAtom(drawable,1,0)));
		result.add(drawable.draw(1d));
		result.add(new AtomStep(Timing.ms(ms), new OpacityAtom(drawable,0,1)));
		addToWorking(result);
		return result;
	}
	
	public Step fadeOut(double ms, Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new AtomStep(Timing.ms(ms), new OpacityAtom(drawable,1,0)));
		result.add(new AtomStep(Timing.instant(),new ExitAtom(drawable)));
		addToWorking(result);
		return result;
	}

	
	public void mark(long ms)
	{
		addToWorking(new AtomStep(Timing.ms(5000),new MarkAtom(ms*1000)));
	}
	
	public void addToWorking(Step step)
	{
		world.add(step);
	}
	
	public Addable buildPhrase()
	{
		return world.buildPhrase();
	}
	
	public Addable buildChord()
	{
		return world.buildChord();
	}
	
	public Addable buildMore(Addable addable)
	{
		return world.buildMore(addable);
	}
	
	public Addable endBuild()
	{
		return world.endBuild();
	}
	
	public void fadeIn(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeIn(ms, actor);
			}
		}
		Addable chord = endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

	public void fadeOut(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeOut(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}


	public ScriptBuilder fadeDown(double ms, Actor actor)
	{
		addToWorking(new AtomStep(Timing.ms(ms), new OpacityAtom(actor,1,0)));
		return this;
	}
	
	public ScriptBuilder fadeUp(double ms, Actor actor)
	{
		addToWorking(new AtomStep(Timing.ms(ms), new OpacityAtom(actor,0,1)));
		return this;
	}
	
	public void fadeDown(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeDown(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

	public void fadeUp(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeUp(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

}

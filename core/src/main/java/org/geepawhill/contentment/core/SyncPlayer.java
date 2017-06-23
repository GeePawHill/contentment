package org.geepawhill.contentment.core;

import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.SyncStep;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;

public class SyncPlayer
{
	public enum State
	{
		Stepping, Playing
	}

	private Script script;
	private int next;
	private Rhythm rhythm;
	private final SimpleObjectProperty<State> stateProperty;
	private Context context;

	public SyncPlayer(Group canvas, Rhythm defaultRhythm)
	{
		this.rhythm = defaultRhythm;
		this.stateProperty = new SimpleObjectProperty<>(State.Stepping);
		this.context = new Context(canvas, defaultRhythm);
	}

	public void load(Script script)
	{
		this.script = script;
		this.next = 0;
		rhythm.seekHard((long) 0);
		stateProperty.set(State.Stepping);
	}

	public int getNext()
	{
		return next;
	}

	public long beat()
	{
		return getBeat();
	}

	public void forward()
	{
		mustBeStepping();
		next += 1;
		if (next == script.size())
		{
			SyncStep previous = getSync(script.size() - 1);
			rhythm.seekHard(previous.target() + (long) previous.timing().ms());
			return;
		}
		rhythm.seekHard(nextSync().target());
	}

	private SyncStep nextSync()
	{
		return getSync(next);
	}

	private SyncStep getSync(int sync)
	{
		return (SyncStep) script.get(sync);
	}

	public void backward()
	{
		mustBeStepping();
		if (next == 0)
		{
			rhythm.seekHard((long) 0);
			next = 0;
			return;
		}
		next -= 1;
		rhythm.seekHard(nextSync().target());
	}

	public long getBeat()
	{
		return rhythm.beat();
	}

	public State getState()
	{
		return stateProperty.get();
	}

	public void playOne()
	{
		mustBeStepping();
		stateProperty.set(State.Playing);
		rhythm.play();
		nextSync().slow(context, this::onPlayOneFinished);
	}

	public void onPlayOneFinished()
	{
		rhythm.pause();
		stateProperty.set(State.Stepping);
		next += 1;
	}

	private void mustBeStepping()
	{
		if (getState() != State.Stepping) throw new RuntimeException("Playing when should be Stepping.");
	}

	public void play()
	{
		mustBeStepping();
		rhythm.play();
		stateProperty.set(State.Playing);
		nextSync().slow(context, this::onPlayFinished);
	}

	public void onPlayFinished()
	{
		stateProperty.set(State.Stepping);
		next += 1;
		if (getNext() == script.size())
		{
			rhythm.pause();
			return;
		}
		nextSync().slow(context, this::onPlayFinished);
	}

	public void end()
	{
		mustBeStepping();
		while (getNext() != script.size())
		{
			nextSync().fast(context);
			next += 1;
		}
	}

	public void start()
	{
		mustBeStepping();
		while (getNext() != 0)
		{
			next -= 1;
			nextSync().undo(context);
		}
	}

	public void last()
	{
		mustBeStepping();
		end();
		backward();
	}

	public Rhythm getRhythm()
	{
		return rhythm;
	}
}

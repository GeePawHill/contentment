package org.geepawhill.contentment.core;

import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.step.SyncStep;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;

public class SyncPlayer
{
	public enum State
	{
		Stepping, Playing
	}

	private Script script;
	private int position;
	private Rhythm rhythm;
	private Context context;
	private final SimpleObjectProperty<State> stateProperty;
	private final SimpleBooleanProperty atStartProperty;
	private final SimpleBooleanProperty atEndProperty;

	public SyncPlayer(Group canvas, Rhythm defaultRhythm)
	{
		this.rhythm = defaultRhythm;
		this.stateProperty = new SimpleObjectProperty<>(State.Stepping);
		this.context = new Context(canvas, defaultRhythm);
		this.atStartProperty = new SimpleBooleanProperty(true);
		this.atEndProperty = new SimpleBooleanProperty(false);
		this.position=0;
	}

	public void load(Script script)
	{
		this.script = script;
		this.setPosition(0);
		rhythm.seekHard((long) 0);
		stateProperty.set(State.Stepping);
	}
	
	public int position()
	{
		return position;
	}

	public long beat()
	{
		return getBeat();
	}
	
	public BooleanProperty atStartProperty()
	{
		return atStartProperty;
	}
	
	public BooleanProperty atEndProperty()
	{
		return atEndProperty;
	}

	public void forward()
	{
		mustBeStepping();
		if(!atEnd())
		{
			nextSync().fast(context);
			setPosition(position() + 1);
			if(atEnd()) rhythm.seekHard(Rhythm.MAX);
			else rhythm.seekHard(nextSync().target());
		}
	}

	private SyncStep nextSync()
	{
		return getSync(position());
	}

	private SyncStep getSync(int sync)
	{
		return script.get(sync);
	}

	public void backward()
	{
		mustBeStepping();
		if (!atStart())
		{
			setPosition(position() - 1);
			nextSync().undo(context);
			rhythm.seekHard(nextSync().target());
		}
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
		if(!atEnd())
		{
			stateProperty.set(State.Playing);
			rhythm.play();
			nextSync().slow(context, this::onPlayOneFinished);
		}
	}

	public void onPlayOneFinished()
	{
		rhythm.pause();
		setPosition(position() + 1);
		if(atEnd()) rhythm.seekHard(Rhythm.MAX);
		else rhythm.seekHard(nextSync().target());
		stateProperty.set(State.Stepping);
	}

	private void mustBeStepping()
	{
		if (getState() != State.Stepping) throw new RuntimeException("Playing when should be Stepping.");
	}

	public void play()
	{
		mustBeStepping();
		if(!atEnd())
		{
			stateProperty.set(State.Playing);
			rhythm.play();
			nextSync().slow(context, this::onPlayFinished);
		}
	}

	public void onPlayFinished()
	{
		setPosition(position() + 1);
		if(!atEnd())
		{
			nextSync().slow(context, this::onPlayFinished);
		}
		else
		{
			rhythm.pause();
			rhythm.seekHard(Rhythm.MAX);
			stateProperty.set(State.Stepping);
		}
	}

	public void end()
	{
		mustBeStepping();
		while (position() < script.size())
		{
			forward();
		}
	}

	public void start()
	{
		mustBeStepping();
		while (position() != 0)
		{
			backward();
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

	public void setPosition(int position)
	{
		this.position = position;
		atStartProperty.set(position==0);
		atEndProperty.set(position==script.size());
	}
	
	public boolean atStart()
	{
		return atStartProperty.get();
	}
	
	public boolean atEnd()
	{
		return atEndProperty.get();
	}
}

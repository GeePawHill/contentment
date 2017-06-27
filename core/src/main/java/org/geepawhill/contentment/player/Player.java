package org.geepawhill.contentment.player;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;

public class Player
{
	public enum State
	{
		Stepping, Playing
	}

	private int position;
	private Context context;
	
	
	private final SimpleObjectProperty<Script> scriptProperty;
	private final SimpleObjectProperty<State> stateProperty;
	private final SimpleBooleanProperty atStartProperty;
	private final SimpleBooleanProperty atEndProperty;

	public Player(Group canvas)
	{
		this.atStartProperty = new SimpleBooleanProperty(true);
		this.atEndProperty = new SimpleBooleanProperty(false);
		this.stateProperty = new SimpleObjectProperty<>(State.Stepping);
		this.scriptProperty = new SimpleObjectProperty<>(new Script());
		this.context = new Context(canvas);
		this.position = 0;
	}
	
	public BooleanProperty atStartProperty()
	{
		return atStartProperty;
	}

	public BooleanProperty atEndProperty()
	{
		return atEndProperty;
	}

	public boolean atStart()
	{
		return atStartProperty.get();
	}

	public boolean atEnd()
	{
		return atEndProperty.get();
	}

	public void load(Script script)
	{
		this.scriptProperty.set(script);
		this.setPosition(0);
		getRhythm().seekHard((long) 0);
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

	public void forward()
	{
		mustBeStepping();
		if (!atEnd())
		{
			nextSync().phrase.fast(context);
			setPosition(position() + 1);
			if (atEnd()) getRhythm().seekHard(Rhythm.MAX);
			else getRhythm().seekHard(nextSync().target);
		}
	}

	private Keyframe nextSync()
	{
		return getSync(position());
	}

	private Keyframe getSync(int sync)
	{
		return getScript().get(sync);
	}

	public void backward()
	{
		mustBeStepping();
		if (!atStart())
		{
			setPosition(position() - 1);
			nextSync().phrase.undo(context);
			getRhythm().seekHard(nextSync().target);
		}
	}

	public long getBeat()
	{
		return getRhythm().beat();
	}

	public State getState()
	{
		return stateProperty.get();
	}

	public void playOne()
	{
		mustBeStepping();
		if (!atEnd())
		{
			stateProperty.set(State.Playing);
			getRhythm().play();
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayOneFinished).play();
		}
	}

	public Boolean isPlayOneDone()
	{
		if (position < getScript().size() - 1)
		{
			if (getRhythm().beat() >= getSync(position + 1).target) return true;
			else return false;
		}
		else
		{
			return getRhythm().isAtEnd();
		}
	}

	public void newPlayOneFinished()
	{
		getRhythm().pause();
		stateProperty.set(State.Stepping);
		setPosition(position() + 1);
	}
	
	public void newPlayFinished()
	{
		setPosition(position()+1);
		if(!atEnd())
		{
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayFinished).play();
		}
		else
		{
			getRhythm().pause();
			stateProperty.set(State.Stepping);
		}
	}

	private void mustBeStepping()
	{
		if (getState() != State.Stepping) throw new RuntimeException("Playing when should be Stepping.");
	}

	public void play()
	{
		mustBeStepping();
		if (!atEnd())
		{
			stateProperty.set(State.Playing);
			getRhythm().play();
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayFinished).play();
		}
	}

	public void end()
	{
		mustBeStepping();
		while (position() < getScript().size())
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
		return getScript().rhythm();
	}

	public void setPosition(int position)
	{
		this.position = position;
		atStartProperty.set(position == 0);
		atEndProperty.set(position == getScript().size());
	}


	public ObjectProperty<Player.State> stateProperty()
	{
		return stateProperty;
	}
	
	public ObjectProperty<Script> scriptProperty()
	{
		return scriptProperty;
	}

	public Script getScript()
	{
		return scriptProperty.get();
	}
}
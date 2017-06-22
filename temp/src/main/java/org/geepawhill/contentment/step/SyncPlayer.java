package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;

public class SyncPlayer
{
	public enum State { Stepping, Playing }
	
	private Sequence sequence;
	private int next;
	private Rhythm rhythm;
	private final SimpleObjectProperty<State> stateProperty;
	private Context context;
	
	public SyncPlayer(Group canvas, Rhythm rhythm)
	{
		this.rhythm = rhythm;
		this.stateProperty = new SimpleObjectProperty<>(State.Stepping);
		this.context = new Context(canvas,rhythm);
	}

	public void load(Sequence sequence)
	{
		this.sequence = sequence;
		this.next = 0;
		setBeat(0);
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
		if(next==sequence.size()-1)
		{
			SyncStep previous = getSync(sequence.size()-1);
			setBeat(previous.target()+(long)previous.timing().ms());
			next+=1;
			return;
		}
		next+=1;
		setBeat(nextSync().target());
	}

	private SyncStep nextSync()
	{
		return getSync(next);
	}
	
	private SyncStep getSync(int sync)
	{
		return (SyncStep)sequence.get(sync);
	}

	public void backward()
	{
		mustBeStepping();
		if(next==0)
		{
			setBeat(0);
			next=0;
			return;
		}
		next-=1;
		setBeat(nextSync().target());
	}

	public long getBeat()
	{
		return rhythm.beat();
	}

	public void setBeat(long beat)
	{
		rhythm.seekHard(beat);
	}

	public State getState()
	{
		return stateProperty.get();
	}

	public void playOne()
	{
		mustBeStepping();
		stateProperty.set(State.Playing);
		nextSync().slow(context, this::onPlayOneFinished);
	}

	public void onPlayOneFinished()
	{
		stateProperty.set(State.Stepping);
		next+=1;
	}
	
	private void mustBeStepping()
	{
		if(getState()!=State.Stepping) throw new RuntimeException("Playing when should be Stepping.");
	}

	public void play()
	{
		mustBeStepping();
		stateProperty.set(State.Playing);
		nextSync().slow(context, this::onPlayFinished);
	}
	
	public void onPlayFinished()
	{
		stateProperty.set(State.Stepping);
		next+=1;
		if(getNext()==sequence.size()) return;
		nextSync().slow(context, this::onPlayFinished);
	}

}

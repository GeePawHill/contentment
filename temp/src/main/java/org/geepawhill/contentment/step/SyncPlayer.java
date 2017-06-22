package org.geepawhill.contentment.step;

import java.util.function.IntPredicate;

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
	
	public SyncPlayer(Group canvas, Rhythm rhythm)
	{
		this.rhythm = rhythm;
		this.stateProperty = new SimpleObjectProperty<>(State.Stepping);
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
	
}

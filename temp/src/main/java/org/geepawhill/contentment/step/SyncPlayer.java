package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Sequence;

import javafx.beans.property.SimpleLongProperty;

public class SyncPlayer
{
	private Sequence sequence;
	private int next;
	private final SimpleLongProperty beatProperty;
	
	public SyncPlayer()
	{
		this.beatProperty = new SimpleLongProperty(0);
	}

	public void load(Sequence sequence)
	{
		this.sequence = sequence;
		this.next = 0;
		setBeat(0);
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
		return beatProperty.get();
	}

	public void setBeat(long beat)
	{
		beatProperty.set(beat);
	}


}

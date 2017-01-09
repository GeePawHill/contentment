package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.scene.Group;

public class Stroke implements Actor
{
	
	final String nickname;
	
	public Stroke()
	{
		this.nickname = Names.make(getClass());
	}
	
	public String nickname()
	{
		return nickname;
	}


	@Override
	public void outline(KvOutline output)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Group group()
	{
		// TODO Auto-generated method stub
		return null;
	}

}

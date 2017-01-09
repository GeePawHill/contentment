package org.geepawhill.contentment.test;

import org.geepawhill.contentment.actor.Names;
import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.scene.Group;
import javafx.scene.Node;

public class TestActor implements Actor
{
	private final String nickname;
	public Node[] nodes;
	public Group group;
	
	public TestActor(Node... nodes)
	{
		this.nickname = Names.make(getClass());
		this.nodes=nodes;
		this.group = new Group(nodes);
	}
	
	public String nickname()
	{
		return nickname;
	}

	@Override
	public void outline(KvOutline output)
	{
		output.append("TestActor");
		output.indent();
		for(Node node : nodes)
		{
			output.append(node.getClass().getSimpleName());
			output.indent();
			boolean visible = node.isVisible();
			output.append("Visible",Boolean.toString(visible));
			if(visible)
			{
				node.getBoundsInLocal();
			}
			output.dedent();
		}
		output.dedent();
	}

	@Override
	public Group group()
	{
		return group;
	}

}

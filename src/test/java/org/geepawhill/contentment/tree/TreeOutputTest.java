package org.geepawhill.contentment.tree;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.TreeItem;


public class TreeOutputTest
{

	private TreeOutput<String> tree;
	
	@Before
	public void before()
	{
		tree = new TreeOutput<String>();
	}
	
	@Test
	public void emptyIsEmpty()
	{
		assertTrue(tree.asList().isEmpty());
	}

	@Test
	public void asListSingleItem()
	{
		tree.append("Item 1");
		List<Appendee<String>> list = tree.asList();
		assertEquals(1,list.size());
		assertEquals("Item 1",list.get(0).data);
		assertEquals(1,list.get(0).indent);
	}
	
	@Test
	public void asListIndent()
	{
		tree.append("Item 1");
		tree.indent();
		tree.append("Item 1.1");
		List<Appendee<String>> list = tree.asList();
		assertEquals(2,list.size());
		assertEquals("Item 1",list.get(0).data);
		assertEquals(1,list.get(0).indent);
		assertEquals("Item 1.1",list.get(1).data);
		assertEquals(2,list.get(1).indent);	
	}
	
	@Test
	public void indentDedent()
	{
		tree.append("Item 1");
		tree.indent();
		tree.append("Item 1.1");
		tree.append("Item 1.2");
		tree.dedent();
		tree.append("Item 2");
		List<Appendee<String>> list = tree.asList();
		assertEquals(4,list.size());
		assertEquals("Item 1",list.get(0).data);
		assertEquals(1,list.get(0).indent);
		assertEquals("Item 1.1",list.get(1).data);
		assertEquals(2,list.get(1).indent);	
		assertEquals("Item 1.2",list.get(2).data);
		assertEquals(2,list.get(2).indent);
		assertEquals("Item 2",list.get(3).data);
		assertEquals(1,list.get(3).indent);	
	}
	
	@Test
	public void asText()
	{
		tree.append("Item 1");
		tree.indent();
		tree.append("Item 1.1");
		tree.dedent();
		String actual = tree.asText("Root");
		assertEquals("Root\n"
				+ "   Item 1\n"
				+ "      Item 1.1\n",actual);
	}
	
	@Test
	public void asTree()
	{
		tree.append("Item 1");
		tree.indent();
		tree.append("Item 1.1");
		tree.append("Item 1.2");
		tree.dedent();
		tree.append("Item 2");
		TreeItem<String> root = new TreeItem<String>("Root");
		tree.asTree(root);
	}

	@Test(expected=RuntimeException.class)
	public void tooManyDedents()
	{
		TreeOutput<String> tree = new TreeOutput<String>();
		tree.dedent();
	}

	@SuppressWarnings("unused")
	private void dumpTree(int indent,TreeItem<String> root)
	{
		tabs(indent);
		System.out.println(root.getValue());
		for(TreeItem<String> child : root.getChildren())
		{
			dumpTree(indent+1,child);
		}
		
	}
	
	private void tabs(int indent)
	{
		while(indent-- >0)
		{
			System.out.print("\t");
		}
	}
}

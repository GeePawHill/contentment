package org.geepawhill.contentment.tree;

import java.util.List;

import javafx.scene.control.TreeItem;

public interface Tree<T>
{

	void indent();

	void append(T data);

	void dedent();

	List<Appendee<T>> asList();

	List<T> asLeafList();

	String asText(String root);

	TreeItem<T> asTree(TreeItem<T> root);

}
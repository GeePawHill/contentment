package org.geepawhill.contentment.outline;

import java.util.List;

import javafx.scene.control.TreeItem;

public interface Outline<T>
{

	void indent();

	void append(T data);

	void dedent();

	List<Line<T>> asList();

	List<T> asLeafList();

	String asText(String root);

	TreeItem<T> asTree(TreeItem<T> root);

	void clear();

}
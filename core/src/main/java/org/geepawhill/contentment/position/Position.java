package org.geepawhill.contentment.position;

import org.geepawhill.contentment.geometry.PointPair;

import javafx.scene.Node;

public interface Position
{

	void position(Node node, PointPair dimensions);
	
}
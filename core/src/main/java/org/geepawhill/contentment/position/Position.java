package org.geepawhill.contentment.position;

import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.ViewPort;

import javafx.scene.Node;

public interface Position
{

	Position DEFAULT = new Centered(ViewPort.CENTER);

	void position(Node node, PointPair dimensions);
	
}
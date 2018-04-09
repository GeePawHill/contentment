package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Entrance;
import org.geepawhill.contentment.position.Position;

import javafx.scene.paint.Paint;

public interface Actor
{
	Actor called(String name);
	Actor in(String name);
	
	Actor sketch();
	Actor fadeUp();
	Actor fadeDown();
	Actor fadeOut();
	Actor fadeIn();
	Actor reColor(Paint paint);

	Actor appear();
	Actor disappear();

	Actor draw(double ms);
	Entrance entrance();
	
	void format(Format format);
	void at(Position position);
	
}
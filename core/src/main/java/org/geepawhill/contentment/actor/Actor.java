package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.fragments.GroupSource;

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
	GroupSource groupSource();
}
package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.step.Step;

import javafx.scene.paint.Paint;

public interface Actor
{

	Step draw(double ms);

	Actor sketch();

	Actor called(String name);

	Actor in(String name);
	
	Actor fadeUp();
	Actor fadeDown();
	Actor fadeOut();
	Actor fadeIn();
	Actor reColor(Paint paint);

	Actor appear();
	Actor disappear();

	GroupSource groupSource();


}
package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.step.Step;

public interface Commandable
{

	Step draw(double ms);

	Actor sketch();

	Actor called(String name);

	Actor in(String name);

}
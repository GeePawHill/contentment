package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.step.Step;

public interface Commandable
{

	Step draw(double ms);

	Commandable sketch();

	Commandable called(String name);

	Commandable in(String name);
	
	Commandable fadeDown();


}
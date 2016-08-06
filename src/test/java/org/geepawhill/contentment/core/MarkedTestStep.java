package org.geepawhill.contentment.core;

import org.geepawhill.contentment.core.MarkedStep;
import org.geepawhill.contentment.core.Sequence;

public class MarkedTestStep extends UnmarkedTestStep implements MarkedStep
{
	static public MarkedTestStep oneStep = new MarkedTestStep();;
	static public MarkedTestStep twoStep = new MarkedTestStep();;
	static public Sequence twoStepSequence = new Sequence(oneStep,twoStep);
	static public Sequence oneStepSequence = new Sequence(oneStep);

}
package org.geepawhill.contentment.core;

public class MarkedTestStep extends UnmarkedTestStep
{
	static public MarkedTestStep oneStep = new MarkedTestStep();;
	static public MarkedTestStep twoStep = new MarkedTestStep();;
	static public Sequence twoStepSequence = new Sequence(oneStep,twoStep);
	static public Sequence oneStepSequence = new Sequence(oneStep);

}
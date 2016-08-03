package org.geepawhill.contentment;

public class MarkedTestStep extends UnmarkedTestStep implements MarkedStep
{
	static public MarkedTestStep oneStep = new MarkedTestStep();;
	static public MarkedTestStep twoStep = new MarkedTestStep();;
	static Sequence twoStepSequence = new Sequence(oneStep,twoStep);
	static Sequence oneStepSequence = new Sequence(oneStep);

}
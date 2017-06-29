package org.geepawhill.contentment.step;

import org.geepawhill.contentment.fast.Fast;

public interface Addable extends Step
{

	Addable add(Step Step);

	Addable add(Fast fast);

}
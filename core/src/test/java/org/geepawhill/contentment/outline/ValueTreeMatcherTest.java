package org.geepawhill.contentment.outline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

public class ValueTreeMatcherTest
{

	@Test
	public void emptiesAreEqual()
	{
		ValueTreeMatcher matcher = new ValueTreeMatcher();
		ValueTree left = new ValueTree();
		ValueTree right = new ValueTree();
		ValueTreeMatch match = matcher.match(left,right);
		assertThat(match.isEqual()).isTrue();
	}
	
	@Test
	public void equalsAreEqual()
	{
		ValueTreeMatcher matcher = new ValueTreeMatcher();
		ValueTree left = new ValueTree();
		left.add("Root");
		ValueTree right = new ValueTree();
		right.add("Root");
		ValueTreeMatch match = matcher.match(left,right);
		assertThat(match.isEqual()).isTrue();
	}

	@Test
	public void leftOnlysPresent()
	{
		ValueTreeMatcher matcher = new ValueTreeMatcher();
		ValueTree left = new ValueTree();
		left.add("Root");
		left.add("Second");
		ValueTree right = new ValueTree();
		right.add("Root");
		ValueTreeMatch match = matcher.match(left,right);
		assertThat(match.isEqual()).isFalse();
		assertThat(match.both.size()).isEqualTo(1);
		assertThat(match.both.get(0).value).isEqualTo("Root");
		assertThat(match.left.size()).isEqualTo(1);
		assertThat(match.left.get(0).value).isEqualTo("Second");
	}

	@Test
	public void rightOnlysPresent()
	{
		ValueTreeMatcher matcher = new ValueTreeMatcher();
		ValueTree left = new ValueTree();
		left.add("Root");
		ValueTree right = new ValueTree();
		right.add("Root");
		right.add("Second");
		ValueTreeMatch match = matcher.match(left,right);
		assertThat(match.isEqual()).isFalse();
		assertThat(match.both.size()).isEqualTo(1);
		assertThat(match.both.get(0).value).isEqualTo("Root");
		assertThat(match.right.size()).isEqualTo(1);
		assertThat(match.right.get(0).value).isEqualTo("Second");
	}

}

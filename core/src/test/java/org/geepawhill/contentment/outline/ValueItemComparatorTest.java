package org.geepawhill.contentment.outline;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ValueItemComparatorTest
{

	private ValueItemComparator comparator;
	
	private final static String[] NO_KEYS = ValueItem.NO_KEYS;
	private final static String[] PARENT_KEY = new String[] { "Parent" };
	private final static String[] ROOT_KEY = new String[] { "Root" };
	
	@Before
	public void before()
	{
		comparator = new ValueItemComparator();
	}

	@Test
	public void shorterLengthIsLesser()
	{
		ValueItem noKeys = new ValueItem(NO_KEYS,"child");
		ValueItem oneKey = new ValueItem( PARENT_KEY, "child");
		assertThat(comparator.compare(noKeys,oneKey)).isLessThan(0);
		assertThat(comparator.compare(oneKey, noKeys)).isGreaterThan(0);
	}
	
	@Test
	public void sameLengthDifferentKeys()
	{
		ValueItem parent = new ValueItem(PARENT_KEY,"child");
		ValueItem root = new ValueItem( ROOT_KEY, "child");
		assertThat(comparator.compare(parent,root)).isLessThan(0);
		assertThat(comparator.compare(root, parent)).isGreaterThan(0);
	}
	
	@Test
	public void sameKeysDifferentValue()
	{
		ValueItem parent = new ValueItem(PARENT_KEY,"child");
		ValueItem root = new ValueItem( PARENT_KEY, "child1");
		assertThat(comparator.compare(parent,root)).isLessThan(0);
		assertThat(comparator.compare(root, parent)).isGreaterThan(0);
	}
	
	@Test
	public void sameKeysSameValue()
	{
		ValueItem parent = new ValueItem(PARENT_KEY,"child");
		ValueItem root = new ValueItem( PARENT_KEY, "child");
		assertThat(comparator.compare(parent,root)).isEqualTo(0);
	}

}

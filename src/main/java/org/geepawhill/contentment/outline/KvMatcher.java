package org.geepawhill.contentment.outline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.geepawhill.contentment.outline.BasicOutline;
import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.Outline;

import javafx.scene.control.TreeItem;

public class KvMatcher
{
	private ArrayList<KvDifference> wrongValues;
	private ArrayList<KvDifference> missingActuals;
	private ArrayList<KvDifference> extraActuals;
	private ArrayList<String> matchingKeys;
	private MatchResult result;

	static public class MatchResult
	{
		public boolean match;
		public BasicOutline<KvDifference> details;

		public MatchResult()
		{
			details = new BasicOutline<>();
		}
		
		public void clear()
		{
			details.clear();
		}
	}
	
	public KvMatcher()
	{
		wrongValues = new ArrayList<>();
		missingActuals = new ArrayList<>();
		extraActuals = new ArrayList<>();
		matchingKeys = new ArrayList<>();
		result = new MatchResult();
	}
	
	public MatchResult match(KvOutline expected, KvOutline actual)
	{
		MatchResult result = new MatchResult();
		result.match = match(expected,actual,result.details);
		return result;
	}

	public boolean match(KvOutline expected, KvOutline actual, BasicOutline<KvDifference> details)
	{
		return match(expected, actual, details, false);
	}

	public boolean match(KvOutline expected, KvOutline actual, BasicOutline<KvDifference> details, boolean noisy)
	{
		Map<String, String> expectedMap = flatMap(expected);
		Map<String, String> actualMap = flatMap(actual);
		printIfNoisy(noisy, expectedMap, actualMap);
		resetDetailArrays();
		matchKeys(expectedMap, actualMap);
		collectRemainingActuals(actualMap);
		finalizeDetails(details);
		return result.match;
	}

	private void finalizeDetails(BasicOutline<KvDifference> details)
	{
		addDetails(details, "Missing Actuals", missingActuals);
		addDetails(details, "Wrong Values", wrongValues);
		addDetails(details, "Extra Actuals", extraActuals);
	}

	private void collectRemainingActuals(Map<String, String> actualMap)
	{
		for (Map.Entry<String, String> extra : actualMap.entrySet())
		{
			extraActuals.add(new KvDifference(extra.getKey(), "", extra.getValue()));
			result.match = false;
		}
	}

	private void matchKeys(Map<String, String> expected, Map<String, String> actual)
	{
		result.match=true;
		for (Map.Entry<String, String> entry : expected.entrySet())
		{
			matchOneKey(entry.getKey(), actual.get(entry.getKey()), entry.getValue());
		}
		for (String foundKey : matchingKeys)
		{
			actual.remove(foundKey);
		}
	}

	private void matchOneKey(String key, String actualValue, String expectedValue)
	{
		if (actualValue == null)
		{
			missingActuals.add(new KvDifference(key, expectedValue, ""));
			result.match = false;
		}
		else
		{
			matchingKeys.add(key);
			if (!expectedValue.equals(actualValue))
			{
				wrongValues.add(new KvDifference(key, expectedValue, actualValue));
				result.match = false;
			}
		}
	}

	private void printIfNoisy(boolean noisy, Map<String, String> expectedMap, Map<String, String> actualMap)
	{
		if (noisy)
		{
			dumpMap("expected", expectedMap);
			dumpMap("actual", actualMap);
		}
	}

	private void resetDetailArrays()
	{
		result.clear();
		wrongValues.clear();
		missingActuals.clear();
		extraActuals.clear();
		matchingKeys.clear();
	}

	private void addDetails(Outline<KvDifference> details, String type, ArrayList<KvDifference> messages)
	{
		if (!messages.isEmpty())
		{
			details.append(new KvDifference(type, "", ""));
			details.indent();
			for (KvDifference message : messages)
				details.append(message);
			details.dedent();
		}
	}

	private void dumpMap(String label, Map<String, String> map)
	{
		System.out.println(label);
		SortedSet<String> result = new TreeSet<String>();
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			result.add("   Key [" + entry.getKey() + "] = [" + entry.getValue() + "]");
		}
		for (String line : result)
		{
			System.out.println(line);
		}
	}

	private Map<String, String> flatMap(KvOutline source)
	{
		TreeItem<KeyValue> root = new TreeItem<>(new KeyValue("Root", ""));
		source.asTree(root);
		HashMap<String, String> destination = new HashMap<>();
		for (TreeItem<KeyValue> child : root.getChildren())
		{
			flatMap(destination, "", child);
		}
		return destination;
	}

	private void flatMap(HashMap<String, String> destination, String prefix, TreeItem<KeyValue> parent)
	{
		String key = prefix + parent.getValue().getKey();
		destination.put(key, parent.getValue().getValue());
		for (TreeItem<KeyValue> child : parent.getChildren())
		{
			flatMap(destination, key + ".", child);
		}
	}

}

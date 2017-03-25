package org.geepawhill.contentment.outline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.geepawhill.contentment.model.Outline;

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
	
	public MatchResult match(Outline<KeyValue> expected, Outline<KeyValue> actual)
	{
		match(expected,actual,result.details,false);
		return result;
	}

	public MatchResult match(Outline<KeyValue> expected, Outline<KeyValue> actual, BasicOutline<KvDifference> details, boolean noisy)
	{
		resetDetailArrays();
		Map<String, String> expectedMap = flatMap(expected);
		Map<String, String> actualMap = flatMap(actual);
		printIfNoisy(noisy, expectedMap, actualMap);
		matchKeys(expectedMap, actualMap);
		collectRemainingActuals(actualMap);
		finalizeDetails();
		return result;
	}

	private void finalizeDetails()
	{
		addDetails("Missing Actuals", missingActuals);
		addDetails("Wrong Values", wrongValues);
		addDetails("Extra Actuals", extraActuals);
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

	private void addDetails(String type, ArrayList<KvDifference> messages)
	{
		if (!messages.isEmpty())
		{
			result.details.append(new KvDifference(type, "", ""));
			result.details.indent();
			for (KvDifference message : messages)
				result.details.append(message);
			result.details.dedent();
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

	private Map<String, String> flatMap(Outline<KeyValue> source)
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

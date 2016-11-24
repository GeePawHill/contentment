package org.geepawhill.contentment.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.scene.control.TreeItem;

public class KeyValueTreeComparator
{

	public boolean match(TreeOutput<KeyValue> expected, TreeOutput<KeyValue> actual, TreeOutput<KeyValueTreeMessage> details)
	{
		return match(expected,actual,details,false);
	}

	public boolean match(TreeOutput<KeyValue> expected, TreeOutput<KeyValue> actual, TreeOutput<KeyValueTreeMessage> details,boolean noisy)
	{
		Map<String,String> expectedMap = flatMap(expected);
		if(noisy) dumpMap("expected", expectedMap);
		Map<String,String> actualMap = flatMap(actual);
		if(noisy) dumpMap("actual",actualMap);
		
		ArrayList<KeyValueTreeMessage> wrongValues = new ArrayList<>();
		ArrayList<KeyValueTreeMessage> missingActuals = new ArrayList<>();
		ArrayList<KeyValueTreeMessage> extraActuals = new ArrayList<>();
		ArrayList<String> matchingKeys = new ArrayList<>();
		
		boolean result = true;
		for(Map.Entry<String,String> entry : expectedMap.entrySet())
		{
			String actualValue = actualMap.get(entry.getKey());
			String expectedValue = entry.getValue();
			if(actualValue==null)
			{
				missingActuals.add(new KeyValueTreeMessage(entry.getKey(),expectedValue,""));
				result = false;
			}
			else
			{
				matchingKeys.add(entry.getKey());
				if(!expectedValue.equals(actualValue))
				{
					wrongValues.add(new KeyValueTreeMessage(entry.getKey(),entry.getValue(),actualValue));
					result = false;
				}
			}
		}
		for(String foundKey : matchingKeys)
		{
			actualMap.remove(foundKey);
		}
		for(Map.Entry<String, String> extra : actualMap.entrySet())
		{
			extraActuals.add(new KeyValueTreeMessage(extra.getKey(),"",extra.getValue()));
			result=false;
		}
		addDetails(details, "Missing Actuals", missingActuals);
		addDetails(details, "Wrong Values",wrongValues);
		addDetails(details, "Extra Actuals",extraActuals);
		return result;

	}

	private void addDetails(TreeOutput<KeyValueTreeMessage> details, String type, ArrayList<KeyValueTreeMessage> messages)
	{
		if(!messages.isEmpty())
		{
			details.append(new KeyValueTreeMessage(type,"",""));
			details.indent();
			for(KeyValueTreeMessage message : messages) details.append(message);
			details.dedent();
		}
	}

	private void dumpMap(String label,Map<String, String> map)
	{
		System.out.println(label);
		SortedSet<String> result = new TreeSet<String>();
		for(Map.Entry<String, String> entry : map.entrySet())
		{
			result.add("   Key ["+entry.getKey()+"] = ["+entry.getValue()+"]");
		}
		for(String line : result)
		{
			System.out.println(line);
		}
	}

	private Map<String, String> flatMap(TreeOutput<KeyValue> source)
	{
		TreeItem<KeyValue> root = new TreeItem<>(new KeyValue("Root", ""));
		source.asTree(root);
		HashMap<String, String> destination = new HashMap<>();
		for(TreeItem<KeyValue> child : root.getChildren())
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

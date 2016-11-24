package org.geepawhill.contentment.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TreeItem;

public class KeyValueTreeComparator
{

	public boolean match(TreeOutput<KeyValue> expected, TreeOutput<KeyValue> actual, TreeOutput<String> details)
	{
		Map<String,String> expectedMap = flatMap(expected);
		Map<String,String> actualMap = flatMap(actual);
		
		ArrayList<String> wrongValues = new ArrayList<>();
		ArrayList<String> missingActuals = new ArrayList<>();
		ArrayList<String> extraActuals = new ArrayList<>();
		ArrayList<String> matchingKeys = new ArrayList<>();
		
		boolean result = true;
		for(Map.Entry<String,String> entry : expectedMap.entrySet())
		{
			String actualValue = actualMap.get(entry.getKey());
			if(actualValue==null)
			{
				missingActuals.add("Key: ["+entry.getKey()+"] Value: ["+entry.getValue()+"]");
				result = false;
			}
			else
			{
				matchingKeys.add(entry.getKey());
				String expectedValue = entry.getValue();
				if(!expectedValue.equals(actualValue))
				{
					wrongValues.add("Key: ["+entry.getKey()+"] Expected: ["+expectedValue+"] Actual: ["+actualValue+"]");
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
			extraActuals.add("Key: ["+extra.getKey()+ "] Value: ["+extra.getValue()+"]");
			result=false;
		}
		addDetails(details, "Missing Actuals", missingActuals);
		addDetails(details, "Wrong Values",wrongValues);
		addDetails(details, "Extra Actuals",extraActuals);
		return result;

	}

	private void addDetails(TreeOutput<String> details, String type, ArrayList<String> messages)
	{
		if(!messages.isEmpty())
		{
			details.append(type);
			details.indent();
			for(String message : messages) details.append(message);
			details.dedent();
		}
	}

	@SuppressWarnings("unused")
	private void dumpMap(String label,Map<String, String> map)
	{
		System.out.println(label);
		for(Map.Entry<String, String> entry : map.entrySet())
		{
			System.out.println("   Key ["+entry.getKey()+"] = ["+entry.getValue()+"]");
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

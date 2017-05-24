package org.geepawhill.contentment.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.Line;
import org.geepawhill.contentment.outline.Outline;

import javafx.scene.control.TreeItem;

public class ContextOutline implements Outline<KeyValue>
{
	
	private KvOutline source;
	private String baseKey;

	public ContextOutline(KvOutline source)
	{
		this.source = source;
	}

	public List<Line<KeyValue>> asList()
	{
		return source.asList(); 
	}

	public List<KeyValue> asLeafList()
	{
		return source.asLeafList();
	}

	public String asText()
	{
		return source.asText("Context");
	}
	
	public void baseKey(String key)
	{
		baseKey = key;
	}
	
	public KeyValue assertBase(String part)
	{
		return assertKey(baseKey+"."+part);
	}
	
	public KeyValue assertBase(String part,String value)
	{
		return assertKey(baseKey+"."+part,value);
	}
	
	public KeyValue assertKey(String key)
	{
		KeyValue found = source.find(key);
		if(found==null)
		{
			source.dump("Outline");
			System.out.println("Missing key: "+key);
			fail("Key not found: ["+key+"]");
		}
		return found;
	}

	public KeyValue assertKey(String key,String expected)
	{
		KeyValue found = source.find(key);
		if(found==null)
		{
			source.dump("Outline");
			System.out.println("Missing key: "+key);
			fail("Key not found: ["+key+"]");
		}
		if(!expected.equals(found.getValue()))
		{
			source.dump("Outline");
			String message = "Bad Value for "+key+": ["+found.getValue()+"] instead of ["+expected+"]";
			System.out.println(message);
			fail(message);
		}
		return found;
	}

	@Override
	public void indent()
	{
		source.indent();
	}

	@Override
	public void append(KeyValue data)
	{
		source.append(data);
		
	}

	@Override
	public void dedent()
	{
		source.dedent();
	}

	@Override
	public String asText(String root)
	{
		return source.asText(root);
	}

	@Override
	public TreeItem<KeyValue> asTree(TreeItem<KeyValue> root)
	{
		return source.asTree(root);
	}

	@Override
	public void clear()
	{
		source.clear();
	}
	
	public KeyValue find(String fullKey)
	{
		return source.find(fullKey);
	}

	public void assertKeyAbsent(String key)
	{
		KeyValue found = source.find(key);
		if(found==null) return;
		source.dump("Outline");
		String message = "Key prsent should be absent: "+key+": ["+found.getValue()+"]";
		System.out.println(message);
		fail(message);
	}

}

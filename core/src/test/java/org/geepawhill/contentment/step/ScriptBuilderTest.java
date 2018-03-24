package org.geepawhill.contentment.step;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.assertj.core.api.Fail;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.fragments.Sync;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.timing.Timing;

public class ScriptBuilderTest
{
	static class ExposedPhrase extends Phrase
	{
		public ArrayList<Gesture> gestures()
		{
			return playables;
		}
	}
	
	static class TestScriptBuilder extends ScriptBuilder<TestScriptBuilder>
	{
		@Override
		public TestScriptBuilder downcast()
		{
			return this;
		}
		
		@Override
		public Phrase makePhrase()
		{
			return new ExposedPhrase();
		}
		
		public long lastStall()
		{
			return lastStall;
		}
		
		public long lastScene()
		{
			return lastScene;
		}
		
	}
	
	TestScriptBuilder builder;
	
	@Before
	public void before()
	{
		builder = new TestScriptBuilder();
	}
	
	@Test
	public void endWithoutSceneThrows()
	{
		try {
			builder.end();
			Fail.fail("Didn't throw!");
		}
		catch(Exception expected)
		{
		}
	}
	
	@Test
	public void sceneAddsScene()
	{
		builder.scene(0);
		assertThat(builder.lastScene()).isEqualTo(0);
		assertThat(builder.lastStall()).isEqualTo(0);
		builder.end();
		assertThat(builder.script.size()).isEqualTo(1);
		ExposedPhrase phrase = (ExposedPhrase)builder.script.get(0).phrase;
		assertThat(phrase.gestures().size()).isEqualTo(1);
	}
	
	@Test
	public void scenesStartAnywhere()
	{
		builder.scene(20);
		assertThat(builder.lastScene()).isEqualTo(20);
		assertThat(builder.lastStall()).isEqualTo(20);
		builder.end();
		assertThat(builder.script.size()).isEqualTo(1);
		ExposedPhrase phrase = (ExposedPhrase)builder.script.get(0).phrase;
		assertThat(phrase.gestures().size()).isEqualTo(1);
	}
	
	@Test
	public void normalFunctionsWork()
	{
		builder.scene(0);
		builder.wipe();
		builder.end();
		assertThat(builder.script.size()).isEqualTo(1);
		ExposedPhrase phrase = (ExposedPhrase)builder.script.get(0).phrase;
		assertThat(phrase.gestures().size()).isEqualTo(2);
	}
	
	@Test
	public void scenesChain()
	{
		builder.scene(0);
		builder.scene(5);
		assertThat(builder.lastScene()).isEqualTo(5);
		builder.end();
		assertThat(builder.script.size()).isEqualTo(2);
	}

	@Test
	public void stallsChain()
	{
		builder.scene(0);
		builder.sync(5);
		builder.sync(5);
		assertThat(builder.lastStall()).isEqualTo(10);
		builder.end();
		assertThat(builder.script.size()).isEqualTo(1);
	}

}

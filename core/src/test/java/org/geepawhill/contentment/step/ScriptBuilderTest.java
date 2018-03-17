package org.geepawhill.contentment.step;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.assertj.core.api.Fail;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.core.Gesture;
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
		private long sceneStart;
		
		public TestScriptBuilder()
		{
			sceneStart = -1;
		}

		@Override
		public TestScriptBuilder downcast()
		{
			return this;
		}
		

		public void scene(long beat)
		{
			if(sceneStart!=-1)
			{
				script.add(new Keyframe(sceneStart,endBuild()));
			}
			sceneStart = beat;
			buildPhrase();
			addToWorking(new AtomStep(Timing.ms(30000),new MarkAtom(beat*1000)));
		}

		public void end()
		{
			if(sceneStart==-1) throw new RuntimeException("end() called with no scene.");
			script.add(new Keyframe(sceneStart,endBuild()));
		}
		
		@Override
		public Phrase makePhrase()
		{
			return new ExposedPhrase();
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
	

}

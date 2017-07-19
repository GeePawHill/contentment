package org.geepawhill.contentment.script;

public class FluentScriptBuilder
{
	///////////////////////////////////////////////////////////////////////////////
	// This class and following function just get a line started
	static class MarkContext
	{
		
		// pretend convenience method for super-common agent-type 'Letters'.
		public Letters.LettersBuilder letters(String source)
		{
			return actor(new Letters());
		}
		
		// generically pass through to avoid fluency break for new
		// without this, we'd have to change MarkContext when we added a new kind of Agent
		public <AGENT extends Agent<BUILDER>, BUILDER> BUILDER actor(AGENT actor)
		{
			return actor.builder();
		}
	}

	public MarkContext mark(long beat)
	{
		return new MarkContext();
	}

	///////////////////////////////////////////////////////////////////////////////
	// base class of all actors
	public static interface Agent<BUILDER>
	{
		// return a builder for you
		BUILDER builder();
		
		// there will be other API here for stuff needed by the generic builders
		// for instance, group(), draw().
	}
	
	// base class of all AgentBuilders
	// the very weird SUBCLASS parameter is the guy implementing us
	// we need the downcast() so we can maintain the fluency chain
	public static interface AgentBuilder<SUBCLASS>
	{
		// pretend build/command that is agnostic about the Agent it's building
		public SUBCLASS agnostic();
		
		// subclass will provide its properly-cast this
		public SUBCLASS downcast();	
	}
	
	// this guy will implement all the generic commands
	// generic means agnostic about type of Agent, as long as it has the Agent interface
	public static abstract class GenericAgentBuilder<AGENT extends Agent<BUILDER>,BUILDER> implements AgentBuilder<BUILDER>
	{
		protected AGENT actor;
		
		public GenericAgentBuilder(AGENT actor)
		{
			this.actor = actor;
		}

		// pretend method that is agnostic about what type of Agent
		// this keeps us from duping code in each agent-specific builder.
		public BUILDER agnostic()
		{
			// do something to the the agent using only Agent API's
			return downcast();
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// Letters needs a builder-command that no other agent can do, namely 'lettersOnly()' below
	// he uses an internal class as his builder so it can get to the fields/privates freely.
	public static class Letters implements Agent<Letters.LettersBuilder>
	{
		public class LettersBuilder extends GenericAgentBuilder<Letters,LettersBuilder>
		{
			public LettersBuilder()
			{
				super(Letters.this);
			}
			
			// this action can only be done to actors of type MyAgent
			public LettersBuilder lettersOnly()
			{
				// no setter required on Letters
				lettersOnlyField = "From builder";
				// do something only this kind of agent can do
				return this;
			}
			
			// super uses this to keep the fluency-chain still cast to this builder instead of itself
			@Override
			public LettersBuilder downcast()
			{
				return this;
			}
		}
		
		public Letters.LettersBuilder builder()
		{
			return new LettersBuilder();
		}
		
		private String lettersOnlyField;
		
		public Letters()
		{
			lettersOnlyField="default";
		}

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// What if you don't need any custom builder API, the generic will do?
	// This is nasty but it's also library code
	public static class ExternalBuilder<AGENT extends Agent<ExternalBuilder<AGENT>>> extends GenericAgentBuilder<AGENT,ExternalBuilder<AGENT>>
	{
		
		public ExternalBuilder(AGENT agent)
		{
			super(agent);
		}

		@Override
		public ExternalBuilder<AGENT> downcast()
		{
			return this;
		}
	}
	
	// Strokes have no custom builder API
	public static class Stroke implements Agent<ExternalBuilder<Stroke>>
	{

		@Override
		public ExternalBuilder<Stroke> builder()
		{
			return new ExternalBuilder<>(this);
		}
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	// finally, what if you need custom API and you hate inner classes?
	// many right-thinking people despise inner classes because of complexity
	
	public static class ArrowBuilder extends GenericAgentBuilder<Arrow,ArrowBuilder>
	{
		
		public ArrowBuilder(Arrow agent)
		{
			super(agent);
		}

		@Override
		public ArrowBuilder downcast()
		{
			return this;
		}
		
		public ArrowBuilder arrowsOnly()
		{
			actor.setSomething();
			return this;
		}
	}
	
	public static class Arrow implements Agent<ArrowBuilder>
	{

		@Override
		public ArrowBuilder builder()
		{
			return new ArrowBuilder(this);
		}
		
		public void setSomething()
		{
			// the builder's not inner, so he'll need a setter
		}
		
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Script to prove this shit works.
	
	public void script()
	{
		// this format works for "built-in" types
		mark(20).letters("Hi mom!").agnostic().lettersOnly();
		
		// we can also use this if we make new agents
		mark(20).actor(new Letters()).agnostic().lettersOnly();
		
		// note that custom and generic can be freely intermixed
		mark(20).letters("Hi mom!").lettersOnly().agnostic().lettersOnly().agnostic();
		
		mark(20).actor(new Stroke()).agnostic();
		
		mark(20).actor(new Arrow()).arrowsOnly().agnostic();
	}
}

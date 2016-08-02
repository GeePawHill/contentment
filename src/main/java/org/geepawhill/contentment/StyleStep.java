package org.geepawhill.contentment;

public class StyleStep implements Step
{
	
	private Style style;
	private Style previous;

	public StyleStep(Style style)
	{
		this.style = style;
		this.previous = null;
	}

	@Override
	public void after(Context context)
	{
		if(previous==null) previous = context.styles.get(style.id);
		context.styles.set(style);
	}

	@Override
	public void before(Context context)
	{
		if(previous!=null) context.styles.set(previous);
		previous=null;
	}

	@Override
	public void play(Context context)
	{
		after(context);
		context.onFinished.handle(null);
	}

	@Override
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

}

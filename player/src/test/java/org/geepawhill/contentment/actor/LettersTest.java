package org.geepawhill.contentment.actor;


import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.style.TypeFace;
import org.junit.Before;

public class LettersTest
{
	private Letters stroke;
	
	@Before
	public void before()
	{
		Format format = new Format(TypeFace.largeHand(),TypeFace.white());
		stroke = new Letters("Some text.", new Point(300d,200d), format);
	}

}

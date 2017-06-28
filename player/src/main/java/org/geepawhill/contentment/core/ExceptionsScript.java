package org.geepawhill.contentment.core;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.fast.ChangeColor;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.FastStep;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import static org.geepawhill.contentment.step.Universals.*;

import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ExceptionsScript
{
	private Format columnFormat;
	private Format subFormat;
	private Format moralFormat;

	private ArrayList<Letters> lines;
	private double lastLineY;
	private Format commentFormat;
	private double left;
	private double bottom;
	private Letters doChores;
	private Letters openCan;

	public ExceptionsScript()
	{
		this.lines = new ArrayList<>();
		Font largeFont = new Font("GoodDog", 100d);
		Font mediumFont = new Font("GoodDog", 80d);
		Font moralFont = new Font("GoodDog", 120d);
		columnFormat = new Format(TypeFace.font(largeFont, 3d, 1d), TypeFace.color(Color.RED, 1d),
				Frames.frame(Color.RED, 5d, 1d));
		subFormat = new Format(columnFormat, TypeFace.font(mediumFont, 2d, 1d), TypeFace.color(Color.GREENYELLOW, 1d),
				Frames.frame(Color.GREEN, 5d, 1d));
		moralFormat = new Format(TypeFace.font(moralFont, 5d, 1d), TypeFace.color(Color.LIGHTBLUE, Color.BLUE, 1d),
				Frames.frame(Color.BLUE, 5d, 1d));
	}

	public Script make()
	{
		Script script = new Script(new MediaRhythm(new File("/exceptionsTrialBase.mp4")));
		script.add(new Keyframe(0,opening()));
		script.add(new Keyframe(16000, stack()));
		script.add(new Keyframe(50000,special()));
		return script;
	}
	
	private Phrase opening()
	{
		Phrase phrase = new Phrase();
		setWorking(phrase);
		head("GeePaw Quickie: Testing Exceptions");
		return phrase;
	}

	private Phrase stack()
	{
		Phrase phrase = newWorking();
		left = 1000d;
		double top = 250d;
		bottom = 850d;
		double right = 1580d;
		Format stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		Format normalFormat = new Format(TypeFace.font(new Font("Consolas",60d), 2d, 1d),TypeFace.color(Color.BLUE,1d));
		commentFormat = new Format(TypeFace.largeHand(),TypeFace.color(Color.WHITE,1d),Frames.frame(Color.WHITE, 2d, 1d));
		
		head("A Program's Stack");
		Stroke guideStroke = new Stroke(new PointPair(left,top,left,bottom),stackFormat);
		
		sketch(500d,guideStroke);
		for(int i=0;i<7;i++)
		{
			double localBottom = bottom-(i*100);
			Stroke topStroke = new Stroke(new PointPair(left,localBottom,right,localBottom),stackFormat);
			sketch(500d,topStroke);
		}
		
		mark(22000);
		head("The Household Program");

		mark(26000);
		Letters main = new Letters("main()",new Point(left+20d,bottom-50d),normalFormat,HPos.LEFT);
		sketch(500d,main);
		
		mark(30000);
		doChores = new Letters("doChores()",new Point(left+20d,bottom-150d),normalFormat,HPos.LEFT);
		sketch(500d,doChores);

		mark(33000);
		Letters takeOutTrash = new Letters("takeOutTrash()",new Point(left+20d,bottom-250d),normalFormat,HPos.LEFT);
		sketch(500d,takeOutTrash);
		mark(34000);

		mark(37500);
		Letters putBagsInCan = new Letters("putBagsInCans()",new Point(left+20d,bottom-350d),normalFormat,HPos.LEFT);
		sketch(500d,putBagsInCan);

		mark(40500);
		Letters putOneBagInCan = new Letters("putOneBagInCan()",new Point(left+20d,bottom-450d),normalFormat,HPos.LEFT);
		sketch(500d,putOneBagInCan);

		mark(44000);
		openCan = new Letters("openCan()",new Point(left+20d,bottom-550d),normalFormat,HPos.LEFT);
		sketch(500d,openCan);

		
			return phrase;
	}
	
	private Phrase special()
	{
		Phrase phrase = newWorking();
		addToWorking(new ChangeColor(openCan, Color.VIOLET));
		addToWorking(new ChangeColor(doChores,Color.VIOLET));
		
		mark(55000);
		Letters thrower = new Letters("Thrower",new Point(left-40d,bottom-550d),commentFormat,HPos.RIGHT);
		sketch(500d,thrower);
		mark(57500);
		Letters catcher = new Letters("Catcher",new Point(left-40d,bottom-150d),commentFormat,HPos.RIGHT);
		sketch(500d,catcher);
		
		mark(105000);
		sketch(1000d,new Arrow(thrower,false,catcher,true,commentFormat));
		return endWorking();

	}

	private void line(String text, Format format)
	{
		Letters line = new Letters(text, new Point(1550d, lastLineY), format, HPos.RIGHT);
		sketch(500d,line);
		lastLineY += 80d;
		lines.add(line);
	}

	private void clearLines()
	{
		lastLineY = 100d;
		for (Letters line : lines)
		{
			disappear(line);
		}
		lines.clear();
	}

	private void head(String text)
	{
		lastLineY = 100d;
		if (!lines.isEmpty()) clearLines();
		line(text, columnFormat);
	}

	private void lead(String text)
	{
		lastLineY += 30d;
		line(text, columnFormat);
	}

	private void sub(String text)
	{
		line(text, subFormat);
	}

}

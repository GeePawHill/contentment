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
import org.geepawhill.contentment.step.Addable;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ExceptionsScript extends ScriptBuilder
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
	private Letters takeOutTrash;
	private Letters putBagsInCan;
	private Letters putOneBagInCan;
	private ArrayList<Stroke> strokes;
	private Letters main;
	private Format lightComment;

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
		script.add(new Keyframe(0, opening()));
		script.add(new Keyframe(16000, stack()));
		script.add(new Keyframe(50000, special()));
		return script;
	}

	private Step opening()
	{
		buildPhrase();
		head("Microtesting Exceptions");
		mark(3000);
		sub("A GeePaw Quickie");
		return endBuild();
	}

	private Step stack()
	{
		buildPhrase();
		left = 1000d;
		double top = 250d;
		bottom = 850d;
		double right = 1580d;
		Format stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		Format normalFormat = new Format(TypeFace.font(new Font("Consolas", 60d), 2d, 1d), TypeFace.color(Color.BLUE, 1d));
		commentFormat = new Format(TypeFace.largeHand(), TypeFace.color(Color.WHITE, 1d), Frames.frame(Color.WHITE, 2d, 1d));
		lightComment = new Format(TypeFace.font(new Font("GoodDog", 60d), 1d, 1d), TypeFace.color(Color.WHITE, 1d), Frames.frame(Color.WHITE, 2d, 1d));

		head("A Program's Stack");
		
		strokes = new ArrayList<>();
		Stroke guideStroke = new Stroke(new PointPair(left, top, left, bottom), stackFormat);
		strokes.add(guideStroke);

		sketch(500d, guideStroke);
		for (int i = 0; i < 7; i++)
		{
			double localBottom = bottom - (i * 100);
			Stroke topStroke = new Stroke(new PointPair(left, localBottom, right, localBottom), stackFormat);
			sketch(500d, topStroke);
			strokes.add(topStroke);
		}

		mark(22000);
		head("The Household Program");

		mark(26000);
		main = new Letters("main()", new Point(left + 20d, bottom - 50d), normalFormat, HPos.LEFT);
		sketch(500d, main);

		mark(30000);
		doChores = new Letters("doChores()", new Point(left + 20d, bottom - 150d), normalFormat, HPos.LEFT);
		sketch(500d, doChores);

		mark(33000);
		takeOutTrash = new Letters("takeOutTrash()", new Point(left + 20d, bottom - 250d), normalFormat, HPos.LEFT);
		sketch(500d, takeOutTrash);
		mark(34000);

		mark(37500);
		putBagsInCan = new Letters("putBagsInCans()", new Point(left + 20d, bottom - 350d), normalFormat, HPos.LEFT);
		sketch(500d, putBagsInCan);

		mark(40500);
		putOneBagInCan = new Letters("putOneBagInCan()", new Point(left + 20d, bottom - 450d), normalFormat, HPos.LEFT);
		sketch(500d, putOneBagInCan);

		mark(44000);
		openCan = new Letters("openCan()", new Point(left + 20d, bottom - 550d), normalFormat, HPos.LEFT);
		sketch(500d, openCan);
		
		Letters joke = joke("whoops, he forgot openCan()");
		mark(49000);
		disappear(joke);

		return endBuild();
	}

	private Letters joke(String text)
	{
		Format jokeFormat = new Format(TypeFace.font(Font.font("Calibri",FontPosture.ITALIC,40d), 2d, 1d),TypeFace.color(Color.GREEN,Color.WHITE,1d));
		Letters joke = new Letters(text,new Point(400d,800d),jokeFormat,HPos.CENTER);
		appear(joke);
		return joke;
	}

	private Step special()
	{
		buildPhrase();
		addToWorking(new ChangeColor(openCan, Color.VIOLET));
		addToWorking(new ChangeColor(doChores, Color.VIOLET));

		mark(55000);
		Letters thrower = new Letters("Thrower", new Point(left - 40d, bottom - 550d), commentFormat, HPos.RIGHT);
		sketch(500d, thrower);
		mark(57500);
		Letters catcher = new Letters("Catcher", new Point(left - 40d, bottom - 150d), commentFormat, HPos.RIGHT);
		sketch(500d, catcher);
		
		
		mark(60000);
		
		Addable temp = endBuild();
		
		buildChord();
		for(Stroke stroke : strokes)
		{
			fadeOut(500d,stroke);
		}
		fadeOut(500d,takeOutTrash);
		fadeOut(500d,putBagsInCan);
		fadeOut(500d,putOneBagInCan);
		fadeOut(500d,main);
		Addable chord = endBuild();
		
		buildMore(temp);
		addToWorking(chord);
		
		mark(64000);
		Letters throwsLidNotFound = new Letters("throws LidNotFound",new Point(left+20,bottom-490),lightComment,HPos.LEFT);
		fadeIn(500d,throwsLidNotFound);

		mark(72000);
		Letters catchesLidNotFound = new Letters("catches LidNotFound",new Point(left+20,bottom-90),lightComment,HPos.LEFT);
		fadeIn(500d,catchesLidNotFound);
		

		mark(105000);
		Arrow arrow = new Arrow(thrower, false, catcher, true, commentFormat);
		sketch(1000d, arrow);
		
		mark(120000);
		fadeOut(1000d,arrow);
		
		return endBuild();

	}

	private void line(String text, Format format)
	{
		Letters line = new Letters(text, new Point(1550d, lastLineY), format, HPos.RIGHT);
		sketch(500d, line);
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

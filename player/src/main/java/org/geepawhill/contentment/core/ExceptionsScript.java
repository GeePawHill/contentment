package org.geepawhill.contentment.core;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Spot;
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
import org.geepawhill.contentment.style.Dash;
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
	private Actors stack;
	private Actors disappearingStackText;
	private Actors catchAndThrowColorText;
	private Letters main;
	private Format lightComment;
	private Script script;
	private Letters thrower;
	private Letters catcher;
	private double top;
	private double right;

	public ExceptionsScript()
	{
		this.lines = new ArrayList<>();
		this.stack = new Actors();
		this.disappearingStackText = new Actors();
		this.catchAndThrowColorText = new Actors();

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
		script = new Script(new MediaRhythm(new File("/exceptionsTrialBase.mp4")));
		script.add(new Keyframe(0, opening()));
		script.add(new Keyframe(16000, stack()));
		script.add(new Keyframe(50000, special()));
		script.add(new Keyframe(96000, indirectCall()));
		script.add(new Keyframe(140000, dependencies()));
		script.add(new Keyframe(270000, easierToTest()));
		dummy(290000, "What Should We Test?");
		dummy(308000, "Testing The Thrower");
		dummy(312000, "The Throw Condition");
		dummy(337000, "The Thrown Exception");
		dummy(412000, "Testing The Catcher");
		dummy(420000, "Catches The Right Exception");
		dummy(440000, "Does The Right Thing");
		dummy(460000, "Extract The Catch Clause");
		dummy(480000, "Finally, The Finally Clause");
		dummy(540000, "Extract The Finally, Too");
		dummy(550000, "Conclusion");
		return script;
	}

	private void dummy(long beat, String text)
	{
		buildPhrase();
		head(text);
		script.add(new Keyframe(beat, endBuild()));

	}

	private Step easierToTest()
	{
		buildPhrase();
		head("Easier To Test");
		return endBuild();
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
		top = 250d;
		bottom = 850d;
		right = 1580d;
		Format stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		Format normalFormat = new Format(TypeFace.font(new Font("Consolas", 60d), 2d, 1d), TypeFace.color(Color.BLUE, 1d));
		commentFormat = new Format(TypeFace.largeHand(), TypeFace.color(Color.WHITE, 1d), Frames.frame(Color.WHITE, 3d, 1d));
		lightComment = new Format(TypeFace.font(new Font("GoodDog", 60d), 1d, 1d), TypeFace.color(Color.WHITE, 1d),
				Frames.frame(Color.WHITE, 2d, 1d));

		head("A Program's Stack");

		Stroke guideStroke = new Stroke(new PointPair(left, top, left, bottom), stackFormat);
		sketch(500d, guideStroke);
		stack.add(guideStroke);

		for (int i = 0; i < 7; i++)
		{
			double localBottom = bottom - (i * 100);
			Stroke topStroke = new Stroke(new PointPair(left, localBottom, right, localBottom), stackFormat);
			sketch(500d, topStroke);
			stack.add(topStroke);
		}

		mark(22000);
		head("The Household Program");

		mark(26000);
		main = new Letters("main()", stackTextPoint(0), normalFormat, HPos.LEFT);
		disappearingStackText.add(main);
		sketch(500d, main);

		mark(30000);
		doChores = new Letters("doChores()", stackTextPoint(1), normalFormat, HPos.LEFT);
		catchAndThrowColorText.add(doChores);
		sketch(500d, doChores);

		mark(33000);
		takeOutTrash = new Letters("takeOutTrash()", stackTextPoint(2), normalFormat, HPos.LEFT);
		disappearingStackText.add(takeOutTrash);
		sketch(500d, takeOutTrash);
		mark(34000);

		mark(37500);
		putBagsInCan = new Letters("putBagsInCans()", stackTextPoint(3), normalFormat, HPos.LEFT);
		disappearingStackText.add(putBagsInCan);
		sketch(500d, putBagsInCan);

		mark(40500);
		putOneBagInCan = new Letters("putOneBagInCan()", stackTextPoint(4), normalFormat, HPos.LEFT);
		disappearingStackText.add(putOneBagInCan);
		sketch(500d, putOneBagInCan);

		mark(44000);
		openCan = new Letters("openCan()", stackTextPoint(5), normalFormat, HPos.LEFT);
		sketch(500d, openCan);
		catchAndThrowColorText.add(openCan);

		Letters joke = joke("whoops, he forgot openCan()");
		mark(49000);
		disappear(joke);

		return endBuild();
	}

	private Point stackTextPoint(int line)
	{
		return new Point(left + 20d, bottom - ((line*100d)+50d));
	}

	private Step special()
	{
		buildPhrase();
		addToWorking(new ChangeColor(openCan, Color.VIOLET));
		addToWorking(new ChangeColor(doChores, Color.VIOLET));

		mark(55000);
		thrower = new Letters("Thrower", new Point(left - 40d, bottom - 550d), commentFormat, HPos.RIGHT);
		sketch(500d, thrower);
		mark(57500);
		catcher = new Letters("Catcher", new Point(left - 40d, bottom - 150d), commentFormat, HPos.RIGHT);
		sketch(500d, catcher);

		mark(60000);
		fadeOut(500d, stack, disappearingStackText);

		mark(64000);
		Letters throwsLidNotFound = new Letters("throws LidNotFound", new Point(left + 20, bottom - 490), lightComment,
				HPos.LEFT);
		catchAndThrowColorText.add(throwsLidNotFound);
		fadeIn(500d, throwsLidNotFound);

		mark(72000);
		Letters catchesAll = new Letters("catches all exceptions", new Point(left + 20, bottom - 90), lightComment, HPos.LEFT);
		catchAndThrowColorText.add(catchesAll);
		fadeIn(500d, catchesAll);

		mark(82000);
		Letters catchesLidNotFound = new Letters("catches LidNotFound", new Point(left + 20, bottom - 40), lightComment,
				HPos.LEFT);
		catchAndThrowColorText.add(catchesLidNotFound);
		fadeIn(500d, catchesLidNotFound);

		mark(86000);
		Letters logsIt = new Letters("logs and moves on", new Point(left + 20, bottom + 10), lightComment, HPos.LEFT);
		catchAndThrowColorText.add(logsIt);
		fadeIn(500d, logsIt);

		return endBuild();

	}

	private Step indirectCall()
	{
		buildPhrase();
		head("How Throw & Catch Work");
		fadeOut(500d, catchAndThrowColorText);

		mark(105000);
		Arrow call = new Arrow(thrower,false,catcher,true,commentFormat);
		Letters letters = new Letters("call",new Point(left - 40d, bottom - 350d),commentFormat,HPos.RIGHT);
		sketch(500d,call);
		sketch(500d,letters);
		
		mark(111000);
		Cross cross = new Cross(call,150d);
		sketch(500d,cross);
		
		fadeIn(500d, stack);
		
		mark(115000);
		throwsText(5);
		mark(120000);
		noCatchText(4);
		mark(124000);
		noCatchText(3);
		mark(128000);
		noCatchText(2);
		mark(132000);
		catchText(1);
		
		return endBuild();
	}
	
	private Step dependencies()
	{
		buildPhrase();
		clear();
		head("Dependencies");
		mark(146000);
		OvalText thrower = new OvalText("Thrower",new Point(1000d,200d),commentFormat);
		sketch(1000d,thrower);
		OvalText catcher = new OvalText("Catcher",new Point(1500d,200d),commentFormat);
		sketch(1000d,catcher);
		Spot throwerSpot = new Spot(1000d,800d);
		Spot catcherSpot = new Spot(1500d,800d);
		sketch(1d,throwerSpot);
		sketch(1d,catcherSpot);
		Arrow throwerArrow = new Arrow(thrower,false,throwerSpot,false,commentFormat);
		sketch(500d,throwerArrow);
		Arrow catcherArrow = new Arrow(catcher,false,catcherSpot,false,commentFormat);
		sketch(500d,catcherArrow);
		
		mark(153000);
		Letters runtime = new Letters("Runtime",new Point(1250d,220d),commentFormat);
		sketch(500d,runtime);
		
		mark(160000);
		Arrow runtimeOne = knowLine(350d,false);
		
		mark(166000);
		Cross crossOne = new Cross(runtimeOne,70d);
		sketch(500d,crossOne);
		
		mark(172000);
		Arrow runtimeTwo = knowLine(475d,true);
		
		mark(176000);
		Cross crossTwo = new Cross(runtimeTwo,70d);
		sketch(500d,crossTwo);
		
		mark(190000);
		Letters compiletime = new Letters("Compile Time",new Point(1250d,550d),commentFormat);
		sketch(500d,compiletime);
		
		mark(194000);
		Arrow compiletimeOne = knowLine(680d,false);
		
		mark(202000);
		Cross crossThree = new Cross(compiletimeOne,70d);
		sketch(500d,crossThree);
		
		mark(207000);
		Arrow compiletimeTwo = knowLine(780d,true);
		
		mark(212000);
		Cross crossFour = new Cross(compiletimeTwo,70d);
		sketch(500d,crossFour);
		return endBuild();
	}
	
	private Arrow knowLine(double y, boolean leftHead)
	{
		Format knowsFormat = new Format(TypeFace.mediumHand(),TypeFace.color(Color.BLUE, 1d),Frames.frame(Color.BLUE, 2d, 1d,Dash.dash(4d)));
		Spot leftSpot = new Spot(1000d,y);
		sketch(1d,leftSpot);
		Spot rightSpot = new Spot(1500d,y);
		sketch(1d,rightSpot);
		Arrow line = new Arrow(leftSpot,leftHead==true,rightSpot,leftHead==false,knowsFormat);
		Letters letters = new Letters("knows?",new Point(1250,y-50),knowsFormat);
		sketch(500d,line);
		sketch(500d,letters);
		return line;
	}

	private void throwsText(int line)
	{
		Letters letters = new Letters("throws",stackTextPoint(line),commentFormat,HPos.LEFT);
		sketch(500d,letters);
	}
	
	private void noCatchText(int line)
	{
		Letters letters = new Letters("catches?",stackTextPoint(line),commentFormat,HPos.LEFT);
		sketch(500d,letters);
		Letters no = new Letters("no",stackTextPoint(line).add(180d,0d),new Format(commentFormat,TypeFace.color(Color.RED,1d)),HPos.LEFT);
		sketch(1d,no);
	}
	
	private void catchText(int line)
	{
		Letters letters = new Letters("catches?",stackTextPoint(line),commentFormat,HPos.LEFT);
		sketch(500d,letters);
		Letters no = new Letters("YES!",stackTextPoint(line).add(180d,0d),new Format(commentFormat,TypeFace.color(Color.GREEN,1d)),HPos.LEFT);
		sketch(1d,no);
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

	private Letters joke(String text)
	{
		Format jokeFormat = new Format(TypeFace.font(Font.font("Calibri", FontPosture.ITALIC, 40d), 2d, 1d),
				TypeFace.color(Color.GREEN, Color.WHITE, 1d));
		Letters joke = new Letters(text, new Point(400d, 800d), jokeFormat, HPos.CENTER);
		appear(joke);
		return joke;
	}

	private void fadeIn(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeIn(ms, actor);
			}
		}
		Addable chord = endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

	private void fadeOut(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeOut(ms, actor);
			}
		}
		Addable chord = endBuild();
		buildMore(temp);
		addToWorking(chord);
	}
	

}

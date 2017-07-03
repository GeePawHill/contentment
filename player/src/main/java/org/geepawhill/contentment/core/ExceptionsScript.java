package org.geepawhill.contentment.core;

import java.io.File;
import java.util.ArrayList;

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
	private Format leadFormat;
	private Format subFormat;
	private Format minorFormat;

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
	private Format knowsFormat;

	public ExceptionsScript()
	{
		this.lines = new ArrayList<>();
		this.stack = new Actors();
		this.disappearingStackText = new Actors();
		this.catchAndThrowColorText = new Actors();

		Font largeFont = new Font("GoodDog", 100d);
		Font mediumFont = new Font("GoodDog", 80d);
		Font minorFont = new Font("GoodDog", 60d);
		leadFormat = new Format(TypeFace.font(largeFont, 3d, 1d), TypeFace.color(Color.RED, 1d),
				Frames.frame(Color.RED, 5d, 1d));
		subFormat = new Format(leadFormat, TypeFace.font(mediumFont, 2d, 1d), TypeFace.color(Color.GREENYELLOW, 1d),
				Frames.frame(Color.GREEN, 5d, 1d));
		minorFormat = new Format(TypeFace.font(minorFont, 2d, 1d), TypeFace.color(Color.BLUEVIOLET, 1d),
				Frames.frame(Color.GREEN, 5d, 1d));
	}

	public Script make()
	{
		script = new Script(new MediaRhythm(new File("/exceptionsTrialBase.mp4")));
		script.add(new Keyframe(0, opening()));
		script.add(new Keyframe(16, stack()));
		script.add(new Keyframe(50, special()));
		script.add(new Keyframe(96, indirectCall()));
		script.add(new Keyframe(140, dependencies()));
		script.add(new Keyframe(218, noDependencies()));
		script.add(new Keyframe(280,whatToTest()));
		script.add(new Keyframe(300,testingTheThrower()));
		script.add(new Keyframe(359,actualThrowerTest()));
		script.add(new Keyframe(402,testingTheCatcher()));		
		script.add(new Keyframe(457,complexCatchClause()));
		return script;
	}
	
	public Step complexCatchClause()
	{
		buildPhrase();
		clear();
		head("Testing A Complex Catch");
		return endBuild();
	}

	
	public Step testingTheCatcher()
	{
		buildPhrase();
		clear();
		head("Testing The Catcher");
		OvalText catcher = new OvalText("Catcher",new Point(1250d,210d),commentFormat);
		sketch(1d,catcher);
		mark(411);
		lead(" ");
		sub("does it really catch?");
		mark(413);
		minor("set up throw");
		minor("call doChores()");
		mark(419);
		minor("test completes? doChores() caught it");
		mark(424);
		sub("does it do something about it?");
		mark(435);
		minor("supposed to dial home");
		mark(445);
		minor("tell us the missing lid");
		
		return endBuild();
	}

	
	public Step actualThrowerTest()
	{
		buildPhrase();
		clear();
		head("Two Questions, One Test");
		mark(362);
		sub("set up the throw condition");
		mark(364);
		minor("remove lids programmatically");
		mark(370);
		sub("call the thrower");
		minor("call openCan()");
		mark(374);
		sub("catch the exception");
		minor("test catches LidNotFound");
		minor("maybe inspect it");
		mark(382);
		sub("non-throwing?");
		mark(385);
		minor("write those first");
		return endBuild();
	}

	public Step testingTheThrower()
	{
		buildPhrase();
		clear();
		head("Testing The Thrower");
		OvalText thrower = new OvalText("Thrower",new Point(1250d,210d),commentFormat);
		sketch(1d,thrower);
		mark(302);
		lead(" ");
		sub("throws under right condition?");
		mark(304);
		minor("don't throw if the lid's right there");
		mark(309);
		minor("don't throw if something else is wrong");
		mark(315);
		minor("always & only throw when lid's not found");
		mark(324);
		sub("throws the right thing?");
		mark(328);
		minor("must throw right exception");
		mark(340);
		minor("must build it correctly");
		mark(346);
		minor("use an exception constructor for that");
		
		return endBuild();
	}

	
	public Step whatToTest()
	{
		buildPhrase();
		clear();
		head("What Do We Test?");
		mark(284);
		sub("five cases");
		mark(288);
		minor("each case asks a question");
		minor("what happens when we ... ?");
		mark(295);
		minor("(how to think of all microtests)");
		return endBuild();
	}

	private void dummy(long beat, String text)
	{
		buildPhrase();
		head(text);
		script.add(new Keyframe(beat, endBuild()));

	}

	private Step opening()
	{
		buildPhrase();
		head("Microtesting Exceptions");
		mark(3);
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

		mark(22);
		head("The Household Program");

		mark(26);
		main = new Letters("main()", stackTextPoint(0), normalFormat, HPos.LEFT);
		disappearingStackText.add(main);
		sketch(500d, main);

		mark(30);
		doChores = new Letters("doChores()", stackTextPoint(1), normalFormat, HPos.LEFT);
		catchAndThrowColorText.add(doChores);
		sketch(500d, doChores);

		mark(33);
		takeOutTrash = new Letters("takeOutTrash()", stackTextPoint(2), normalFormat, HPos.LEFT);
		disappearingStackText.add(takeOutTrash);
		sketch(500d, takeOutTrash);
		mark(34);

		mark(37);
		putBagsInCan = new Letters("putBagsInCans()", stackTextPoint(3), normalFormat, HPos.LEFT);
		disappearingStackText.add(putBagsInCan);
		sketch(500d, putBagsInCan);

		mark(40);
		putOneBagInCan = new Letters("putOneBagInCan()", stackTextPoint(4), normalFormat, HPos.LEFT);
		disappearingStackText.add(putOneBagInCan);
		sketch(500d, putOneBagInCan);

		mark(44);
		openCan = new Letters("openCan()", stackTextPoint(5), normalFormat, HPos.LEFT);
		sketch(500d, openCan);
		catchAndThrowColorText.add(openCan);

		Letters joke = joke("whoops, he forgot openCan()");
		mark(49);
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

		mark(55);
		thrower = new Letters("Thrower", new Point(left - 40d, bottom - 550d), commentFormat, HPos.RIGHT);
		sketch(500d, thrower);
		mark(57);
		catcher = new Letters("Catcher", new Point(left - 40d, bottom - 150d), commentFormat, HPos.RIGHT);
		sketch(500d, catcher);

		mark(60);
		fadeOut(500d, stack, disappearingStackText);

		mark(64);
		Letters throwsLidNotFound = new Letters("throws LidNotFound", new Point(left + 20, bottom - 490), lightComment,
				HPos.LEFT);
		catchAndThrowColorText.add(throwsLidNotFound);
		fadeIn(500d, throwsLidNotFound);

		mark(72);
		Letters catchesAll = new Letters("catches all exceptions", new Point(left + 20, bottom - 90), lightComment, HPos.LEFT);
		catchAndThrowColorText.add(catchesAll);
		fadeIn(500d, catchesAll);

		mark(82);
		Letters catchesLidNotFound = new Letters("catches LidNotFound", new Point(left + 20, bottom - 40), lightComment,
				HPos.LEFT);
		catchAndThrowColorText.add(catchesLidNotFound);
		fadeIn(500d, catchesLidNotFound);

		mark(86);
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

		mark(105);
		Arrow call = new Arrow(thrower,false,catcher,true,commentFormat);
		Letters letters = new Letters("call",new Point(left - 40d, bottom - 350d),commentFormat,HPos.RIGHT);
		sketch(500d,call);
		sketch(500d,letters);
		
		mark(111);
		Cross cross = new Cross(call,150d);
		sketch(500d,cross);
		
		fadeIn(500d, stack);
		
		mark(115);
		throwsText(5);
		mark(120);
		noCatchText(4);
		mark(124);
		noCatchText(3);
		mark(128);
		noCatchText(2);
		mark(132);
		catchText(1);
		
		return endBuild();
	}
	
	private Step dependencies()
	{
		Actors allButOvals = new Actors();
		buildPhrase();
		clear();
		head("Dependencies");
		mark(146);
		OvalText thrower = new OvalText("Thrower",new Point(1000d,200d),commentFormat);
		sketch(1000d,thrower);
		OvalText catcher = new OvalText("Catcher",new Point(1500d,200d),commentFormat);
		sketch(1000d,catcher);
		Spot throwerSpot = new Spot(1000d,800d);
		Spot catcherSpot = new Spot(1500d,800d);
		allButOvals.add(throwerSpot,catcherSpot);
		sketch(1d,throwerSpot);
		sketch(1d,catcherSpot);
		Arrow throwerArrow = new Arrow(thrower,false,throwerSpot,false,commentFormat);
		sketch(500d,throwerArrow);
		Arrow catcherArrow = new Arrow(catcher,false,catcherSpot,false,commentFormat);
		sketch(500d,catcherArrow);
		allButOvals.add(throwerArrow,catcherArrow);
		
		mark(153);
		Letters runtime = new Letters("Runtime",new Point(1250d,220d),commentFormat);
		sketch(500d,runtime);
		allButOvals.add(runtime);
		
		mark(160);
		Arrow runtimeOne = knowLine(allButOvals,350d,false);
		
		mark(166);
		Cross crossOne = new Cross(runtimeOne,70d);
		sketch(500d,crossOne);
		allButOvals.add(crossOne);
		
		mark(172);
		Arrow runtimeTwo = knowLine(allButOvals,475d,true);
		
		mark(176);
		Cross crossTwo = new Cross(runtimeTwo,70d);
		sketch(500d,crossTwo);
		allButOvals.add(crossTwo);
		
		mark(190);
		Letters compiletime = new Letters("Compile Time",new Point(1250d,550d),commentFormat);
		sketch(500d,compiletime);
		allButOvals.add(compiletime);

		mark(194);
		Arrow compiletimeOne = knowLine(allButOvals,680d,false);
		
		mark(202);
		Cross crossThree = new Cross(compiletimeOne,70d);
		sketch(500d,crossThree);
		allButOvals.add(crossThree);

		
		mark(207);
		Arrow compiletimeTwo = knowLine(allButOvals,780d,true);
		
		mark(212);
		Cross crossFour = new Cross(compiletimeTwo,70d);
		sketch(500d,crossFour);
		allButOvals.add(crossFour);
		
		return endBuild();
	}
	
	private Step noDependencies()
	{
		buildPhrase();
		clear();
		head("No Dependencies");
		sub("very different situation");
		
		mark(227);
		minor("no direct call");
		minor("so neither side knows the other");
		mark(233);
		
		OvalText thrower = new OvalText("Thrower",new Point(1000d,490d),commentFormat);
		sketch(1000d,thrower);
		OvalText catcher = new OvalText("Catcher",new Point(1500d,490d),commentFormat);
	
		sketch(1000d,catcher);
		
		mark(240);
		OvalText lnf = new OvalText("LidNotFound",new Point(1250d,650d),commentFormat);
		sketch(500d,lnf);
		
		Arrow throwerLnf = new Arrow(thrower,false,lnf,true,knowsFormat);
		Arrow catcherLnf = new Arrow(catcher,false,lnf,true,knowsFormat);
		
		
		sketch(500d,throwerLnf);
		sketch(500d,catcherLnf);
		minor(" ");
		minor(" ");
		minor(" ");
		minor(" ");
		mark(247);
		minor("shared dependency irrelevant");
		
		mark(252);
		clear();
		head("Easier To Test");
		
		mark(263);
		sub("four nopes = one yep?");
		mark(268);
		minor("test the thrower by itself");
		minor("test the catcher by itself");
		
		mark(274);
		lead("this is far easier");
		return endBuild();
	}
	
	private Arrow knowLine(Actors actors,double y, boolean leftHead)
	{
		knowsFormat = new Format(TypeFace.mediumHand(),TypeFace.color(Color.BLUE, 1d),Frames.frame(Color.BLUE, 2d, 1d,Dash.dash(4d)));
		Spot leftSpot = new Spot(1000d,y);
		sketch(1d,leftSpot);
		Spot rightSpot = new Spot(1500d,y);
		sketch(1d,rightSpot);
		Arrow line = new Arrow(leftSpot,leftHead==true,rightSpot,leftHead==false,knowsFormat);
		Letters letters = new Letters("knows?",new Point(1250,y-50),knowsFormat);
		sketch(500d,line);
		sketch(500d,letters);
		actors.add(leftSpot,rightSpot,line,letters);
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
		line(text, leadFormat);
	}

	private void lead(String text)
	{
		lastLineY += 30d;
		line(text, leadFormat);
	}
	
	private void minor(String text)
	{
		line(text,minorFormat);
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
}

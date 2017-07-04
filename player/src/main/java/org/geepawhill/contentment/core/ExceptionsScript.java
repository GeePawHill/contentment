package org.geepawhill.contentment.core;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.CodeBlock;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.format.Aligner;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ExceptionsScript extends ScriptBuilder
{
	private Format majorFormat;
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
	private Format codeFormat;
	private Format stackFormat;
	private Format largeCodeFormat;
	private Format jokeFormat;

	public ExceptionsScript()
	{
		Paint majorColor = color(13,165,15);
		Font majorHand = new Font("GoodDog", 100d);
		majorFormat = new Format(TypeFace.font(majorHand, 1d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
		
		Paint subColor = color(183,252,78);
		Font subHand = new Font("GoodDog", 80d);
		subFormat = new Format(majorFormat, TypeFace.font(subHand, 1d, 1d), TypeFace.color(subColor, 1d),
				Frames.frame(subColor, 5d, 1d));

		Paint minorColor = color(240,255,30);
		Font minorFont = new Font("GoodDog", 60d);
		minorFormat = new Format(TypeFace.font(minorFont, 1d, 1d), TypeFace.color(minorColor, 1d),
				Frames.frame(minorColor, 5d, 1d));

		jokeFormat = new Format(TypeFace.font(Font.font("Calibri", FontPosture.ITALIC, 50d), 3d, 1d),
				TypeFace.color(Color.BLUEVIOLET, Color.BLUEVIOLET, 1d));
		
		Paint commentColor = color(48, 201, 137);
		commentFormat = new Format(TypeFace.font(minorFont, 1d, 1d), TypeFace.color(commentColor, 1d), Frames.frame(commentColor, 3d, 1d));
		
		knowsFormat = new Format(TypeFace.mediumHand(), TypeFace.color(Color.BLUEVIOLET, 1d),
				Frames.frame(Color.BLUEVIOLET, 2d, 1d, Dash.dash(4d)));
		
		Paint codeColor = Color.WHITE;
		largeCodeFormat = new Format(TypeFace.font(new Font("Consolas", 60d), 2d, 1d), TypeFace.color(codeColor, 1d));
		
		this.lines = new ArrayList<>();
		this.stack = new Actors();
		this.disappearingStackText = new Actors();
		this.catchAndThrowColorText = new Actors();

		Font codeFont = new Font("Consolas",25d);
		
		codeFormat = new Format(TypeFace.font(codeFont, 2d, 1d), TypeFace.color(codeColor, 1d),Frames.frame(codeColor, 2d, 1d));

		stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		lightComment = new Format(TypeFace.font(new Font("GoodDog", 60d), 1d, 1d), TypeFace.color(commentColor, 1d),
				Frames.frame(commentColor, 2d, 1d));
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
		script.add(new Keyframe(280, whatToTest()));
		script.add(new Keyframe(300, testingTheThrower()));
		script.add(new Keyframe(359, actualThrowerTest()));
		script.add(new Keyframe(402, testingTheCatcher()));
		script.add(new Keyframe(457, complexCatchClause()));
		script.add(new Keyframe(478, finallyClause()));
		script.add(new Keyframe(527, complexFinallyClause()));
		script.add(new Keyframe(540, exceptionsAreEasy()));
		return script;
	}

	public Step exceptionsAreEasy()
	{
		buildPhrase();
		clear();
		head("Microtesting Exceptions Is Easy");
		sub("thrower test questions");
		minor("throws on the right condition?");
		minor("throws the right thing?");
		sub("catcher test questions");
		minor("catches the right thing?");
		minor("catch handler right?");
		minor("finally handler right?");
		mark(550);
		sub("remember the judgment premise!");
		mark(574);
		joke("Bad Pun Alert!");
		return endBuild();
	}

	public Step complexFinallyClause()
	{
		buildPhrase();
		clear();
		head("Testing A Complex Finally");
		double x = 1550d;

		String beforeText = "finally {\n" + "    // complex finally\n" + "    }";
		CodeBlock letters = new CodeBlock(beforeText, new Point(x, 340d), codeFormat, Aligner.rightCenter());
		appear(letters);

		mark(460);
		Letters extract = new Letters("extract this", new Point(1000d, 340d), commentFormat, Aligner.rightCenter());
		sketch(500d, extract);
		Spot spot = new Spot(1280d, 320d);
		appear(spot);
		Arrow arrow = new Arrow(extract, false, spot, true, commentFormat);
		sketch(500d, arrow);

		mark(464);
		Letters toThis = new Letters("to this", new Point(1000d, 550d), commentFormat, Aligner.rightCenter());
		sketch(500d, toThis);

		String afterText1 = "finally {\n" + "    handleFinally(...);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(afterText1, new Point(x, 500d), codeFormat, Aligner.rightCenter());
		appear(afterCode);

		String afterText2 = "public void handleFinally(...) {\n" + "    // complex finally\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(afterText2, new Point(x, 650d), codeFormat, Aligner.rightCenter());
		appear(afterCode2);

		mark(468);
		Letters andTestThis = new Letters("and test the handler here!", new Point(1550d, 825d), commentFormat,
				Aligner.rightCenter());
		sketch(500d, andTestThis);

		return endBuild();
	}

	public Step finallyClause()
	{
		buildPhrase();
		clear();
		head("Does The Finally Work?");
		mark(487);
		sub("finally clauses clean things up");
		minor("when it throws...");
		minor("when it doesn't throw");
		mark(492);
		sub("the bag has been allocated");
		mark(498);
		minor("okay? de-allocate it");
		mark(504);
		minor("uh-oh? de-allocate it");

		return endBuild();
	}

	public Step complexCatchClause()
	{
		buildPhrase();
		clear();
		head("Testing A Complex Catch");

		double x = 1550d;

		String beforeText = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
		CodeBlock letters = new CodeBlock(beforeText, new Point(x, 340d), codeFormat, Aligner.rightCenter());
		appear(letters);

		mark(460);
		Letters extract = new Letters("extract this", new Point(1000d, 340d), commentFormat, Aligner.rightCenter());
		sketch(500d, extract);
		Spot spot = new Spot(1150d, 335d);
		appear(spot);
		Arrow arrow = new Arrow(extract, false, spot, true, commentFormat);
		sketch(500d, arrow);

		mark(464);
		Letters toThis = new Letters("to this", new Point(1000d, 550d), commentFormat, Aligner.rightCenter());
		sketch(500d, toThis);

		String afterText1 = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    handle(lidNotFound);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(afterText1, new Point(x, 500d), codeFormat, Aligner.rightCenter());
		appear(afterCode);

		String afterText2 = "public void handle(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(afterText2, new Point(x, 650d), codeFormat, Aligner.rightCenter());
		appear(afterCode2);

		mark(468);
		Letters andTestThis = new Letters("and test the handler here!", new Point(1550d, 825d), commentFormat,
				Aligner.rightCenter());
		sketch(500d, andTestThis);

		return endBuild();
	}

	public Step testingTheCatcher()
	{
		buildPhrase();
		clear();
		head("Testing The Catcher");
		OvalText catcher = new OvalText("Catcher", new Point(1250d, 210d), commentFormat);
		sketch(1d, catcher);
		mark(410);
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
		OvalText thrower = new OvalText("Thrower", new Point(1250d, 210d), commentFormat);
		sketch(1d, thrower);
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
		main = new Letters("main()", stackTextPoint(0), largeCodeFormat, HPos.LEFT);
		disappearingStackText.add(main);
		sketch(500d, main);

		mark(30);
		doChores = new Letters("doChores()", stackTextPoint(1), largeCodeFormat, HPos.LEFT);
		catchAndThrowColorText.add(doChores);
		sketch(500d, doChores);

		mark(33);
		takeOutTrash = new Letters("takeOutTrash()", stackTextPoint(2), largeCodeFormat, HPos.LEFT);
		disappearingStackText.add(takeOutTrash);
		sketch(500d, takeOutTrash);
		mark(34);

		mark(37);
		putBagsInCan = new Letters("putBagsInCans()", stackTextPoint(3), largeCodeFormat, HPos.LEFT);
		disappearingStackText.add(putBagsInCan);
		sketch(500d, putBagsInCan);

		mark(40);
		putOneBagInCan = new Letters("putOneBagInCan()", stackTextPoint(4), largeCodeFormat, HPos.LEFT);
		disappearingStackText.add(putOneBagInCan);
		sketch(500d, putOneBagInCan);

		mark(44);
		openCan = new Letters("openCan()", stackTextPoint(5), largeCodeFormat, HPos.LEFT);
		sketch(500d, openCan);
		catchAndThrowColorText.add(openCan);

		Letters joke = joke("whoops, he forgot openCan()");
		mark(49);
		disappear(joke);

		return endBuild();
	}

	private Point stackTextPoint(int line)
	{
		return new Point(left + 20d, bottom - ((line * 100d) + 50d));
	}

	private Step special()
	{
		buildPhrase();
		reColor(openCan, Color.RED);
		reColor(doChores, Color.RED);

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
		Arrow call = new Arrow(thrower, false, catcher, true, commentFormat);
		Letters letters = new Letters("call", new Point(left - 40d, bottom - 350d), commentFormat, HPos.RIGHT);
		sketch(500d, call);
		sketch(500d, letters);

		mark(111);
		Cross cross = new Cross(call, 150d);
		sketch(500d, cross);

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
		OvalText thrower = new OvalText("Thrower", new Point(1000d, 200d), commentFormat);
		sketch(1000d, thrower);
		OvalText catcher = new OvalText("Catcher", new Point(1500d, 200d), commentFormat);
		sketch(1000d, catcher);
		Spot throwerSpot = new Spot(1000d, 850d);
		Spot catcherSpot = new Spot(1500d, 850d);
		allButOvals.add(throwerSpot, catcherSpot);
		sketch(1d, throwerSpot);
		sketch(1d, catcherSpot);
		Arrow throwerArrow = new Arrow(thrower, false, throwerSpot, false, commentFormat);
		sketch(500d, throwerArrow);
		Arrow catcherArrow = new Arrow(catcher, false, catcherSpot, false, commentFormat);
		sketch(500d, catcherArrow);
		allButOvals.add(throwerArrow, catcherArrow);

		mark(153);
		Letters runtime = new Letters("Runtime", new Point(1250d, 220d), commentFormat);
		sketch(500d, runtime);
		allButOvals.add(runtime);

		mark(160);
		Arrow runtimeOne = knowLine(allButOvals, 350d, false);

		mark(166);
		Cross crossOne = new Cross(runtimeOne, 125d, 100d, new Point(0d, -10d));
		sketch(500d, crossOne);
		allButOvals.add(crossOne);

		mark(172);
		Arrow runtimeTwo = knowLine(allButOvals, 475d, true);

		mark(176);
		Cross crossTwo = new Cross(runtimeTwo, 125d, 100d, new Point(0d, -10d));
		sketch(500d, crossTwo);
		allButOvals.add(crossTwo);

		mark(190);
		Letters compiletime = new Letters("Compile Time", new Point(1250d, 550d), commentFormat);
		sketch(500d, compiletime);
		allButOvals.add(compiletime);

		mark(194);
		Arrow compiletimeOne = knowLine(allButOvals, 680d, false);

		mark(202);
		Cross crossThree = new Cross(compiletimeOne, 125d, 100d, new Point(0d, -10d));
		sketch(500d, crossThree);
		allButOvals.add(crossThree);

		mark(207);
		Arrow compiletimeTwo = knowLine(allButOvals, 800d, true);

		mark(212);
		Cross crossFour = new Cross(compiletimeTwo, 125d, 100d, new Point(0d, -10d));
		sketch(500d, crossFour);
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

		OvalText thrower = new OvalText("Thrower", new Point(1000d, 490d), commentFormat);
		sketch(1000d, thrower);
		OvalText catcher = new OvalText("Catcher", new Point(1500d, 490d), commentFormat);

		sketch(1000d, catcher);

		mark(240);
		OvalText lnf = new OvalText("LidNotFound", new Point(1250d, 650d), commentFormat);
		sketch(500d, lnf);

		Arrow throwerLnf = new Arrow(thrower, false, lnf, true, knowsFormat);
		Arrow catcherLnf = new Arrow(catcher, false, lnf, true, knowsFormat);

		sketch(500d, throwerLnf);
		sketch(500d, catcherLnf);
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

	private Arrow knowLine(Actors actors, double y, boolean leftHead)
	{
		Spot leftSpot = new Spot(1000d, y);
		sketch(1d, leftSpot);
		Spot rightSpot = new Spot(1500d, y);
		sketch(1d, rightSpot);
		Arrow line = new Arrow(leftSpot, leftHead == true, rightSpot, leftHead == false, knowsFormat);
		Letters letters = new Letters("knows?", new Point(1250, y - 50), knowsFormat);
		sketch(500d, line);
		sketch(500d, letters);
		actors.add(leftSpot, rightSpot, line, letters);
		return line;
	}

	private void throwsText(int line)
	{
		Letters letters = new Letters("throws X", stackTextPoint(line), commentFormat, HPos.LEFT);
		sketch(500d, letters);
	}

	private void noCatchText(int line)
	{
		Letters letters = new Letters("catches X?", stackTextPoint(line), commentFormat, HPos.LEFT);
		sketch(500d, letters);
		Letters no = new Letters("no, keep looking...", stackTextPoint(line).add(218d, 0d),
				new Format(commentFormat, TypeFace.color(Color.RED, 1d)), HPos.LEFT);
		sketch(1d, no);
	}

	private void catchText(int line)
	{
		Letters letters = new Letters("catches X?", stackTextPoint(line), commentFormat, HPos.LEFT);
		sketch(500d, letters);
		Letters no = new Letters("YES! call this one!", stackTextPoint(line).add(218d, 0d),
				new Format(commentFormat, TypeFace.color(Color.GREEN, 1d)), HPos.LEFT);
		sketch(1d, no);
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
		line(text, majorFormat);
	}

	private void lead(String text)
	{
		lastLineY += 30d;
		line(text, majorFormat);
	}

	private void minor(String text)
	{
		line(text, minorFormat);
	}

	private void sub(String text)
	{
		line(text, subFormat);
	}

	private Letters joke(String text)
	{
		Letters joke = new Letters(text, new Point(400d, 300d), jokeFormat, HPos.CENTER);
		appear(joke);
		return joke;
	}

	private Paint color(int r, int g, int b)
	{
		return new Color((double) r / 255d, (double) g / 255d, (double) b / 255d, 1d);
	}
}

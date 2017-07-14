package org.geepawhill.contentment.core;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.CodeBlock;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Grid;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.AboveCenter;
import org.geepawhill.contentment.position.BelowCenter;
import org.geepawhill.contentment.position.BelowLeft;
import org.geepawhill.contentment.position.BelowRight;
import org.geepawhill.contentment.position.CenterRight;
import org.geepawhill.contentment.position.Centered;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.position.RightOf;
import org.geepawhill.contentment.position.TopLeft;
import org.geepawhill.contentment.position.TopRight;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ExceptionsScript extends ScriptBuilder
{
	private static final int STACK_ROWS = 6;

	private Format majorFormat;
	private Format subFormat;
	private Format minorFormat;
	private Format commentFormat;
	private Format knowsFormat;
	private Format codeFormat;
	private Format stackFormat;
	private Format largeCodeFormat;
	private Format jokeFormat;
	private Format lightComment;

	private ArrayList<Actor> lines;

	private Actors stack;
	private Actors disappearingStackText;
	private Actors catchAndThrowColorText;
	
	private Script script;
	
	private Letters thrower;
	private Letters catcher;
	
	private Grid stackGrid;

	private Letters openCan;

	private Letters doChores;

	public ExceptionsScript()
	{
		Paint majorColor = color(13, 165, 15);
		Font majorHand = Font.font("Chewed Pen BB", FontPosture.ITALIC, 90d);
		majorFormat = new Format(TypeFace.font(majorHand, 3d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));

		Paint subColor = color(163, 232, 78);
		Font subHand = Font.font("Chewed Pen BB", FontPosture.ITALIC, 68d);
		subFormat = new Format(majorFormat, TypeFace.font(subHand, 3d, 1d), TypeFace.color(subColor, 1d),
				Frames.frame(subColor, 5d, 1d));

		Paint minorColor = color(240, 255, 30);
		Font minorFont = Font.font("Chewed Pen BB", FontPosture.ITALIC, 50d);
		minorFormat = new Format(TypeFace.font(minorFont, 1d, 1d), TypeFace.color(minorColor, 1d),
				Frames.frame(minorColor, 5d, 1d));

		jokeFormat = new Format(TypeFace.font(Font.font("Calibri", FontPosture.ITALIC, 50d), 3d, 1d),
				TypeFace.color(Color.BLUEVIOLET, Color.BLUEVIOLET, 1d));

		Paint commentColor = color(48, 201, 137);
		Font commentFont = Font.font("Chewed Pen BB", FontPosture.ITALIC, 50d);
		commentFormat = new Format(TypeFace.font(commentFont, 1d, 1d), TypeFace.color(commentColor, 1d),
				Frames.frame(commentColor, 3d, 1d));

		knowsFormat = new Format(TypeFace.font(commentFont, 1d, 1d), TypeFace.color(Color.BLUEVIOLET, 1d),
				Frames.frame(Color.BLUEVIOLET, 2d, 1d, Dash.dash(4d)));

		Paint codeColor = Color.WHITE;
		largeCodeFormat = new Format(TypeFace.font(new Font("Consolas", 60d), 2d, 1d), TypeFace.color(codeColor, 1d));

		this.lines = new ArrayList<>();
		this.stack = new Actors();
		this.stackGrid = new Grid(1, STACK_ROWS, new PointPair(900d, 250d, 1480d, 850d));
		this.disappearingStackText = new Actors();
		this.catchAndThrowColorText = new Actors();

		Font codeFont = new Font("Consolas", 25d);

		codeFormat = new Format(TypeFace.font(codeFont, 2d, 1d), TypeFace.color(codeColor, 1d),
				Frames.frame(codeColor, 2d, 1d));

		stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		lightComment = new Format(TypeFace.font(commentFont, 1d, 1d), TypeFace.color(commentColor, 1d),
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
		CodeBlock beforeCode = new CodeBlock(beforeText, codeFormat, new TopRight(1550,260d));
		appear(beforeCode);

		mark(460);
		Letters extract = new Letters("extract this", new CenterRight(1000d, 340d), commentFormat);
		sketch(500d, extract);
		Spot spot = new Spot(1280d, 335d);
		appear(spot);
		Arrow arrow = new Arrow(extract, false, spot, true, commentFormat);
		sketch(500d, arrow);

		mark(464);
		Letters toThis = new Letters("to this", new CenterRight(1000d, 550d), commentFormat);
		sketch(500d, toThis);

		String afterText1 = "finally {\n" + "    handleFinally(...);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(afterText1, codeFormat, new TopRight(1550d,460d));
		appear(afterCode);

		String afterText2 = "public void handleFinally(...) {\n" + "    // complex finally\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(afterText2,codeFormat, new TopRight(1550d,640d));
		appear(afterCode2);

		mark(468);
		Letters andTestThis = new Letters("and test the handler here!", new BelowCenter(afterCode2), commentFormat);
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
		CodeBlock beforeCode = new CodeBlock(beforeText, codeFormat, new TopRight(1550,260d));
		appear(beforeCode);

		mark(460);
		Letters extract = new Letters("extract this", new CenterRight(1000d, 340d), commentFormat);
		sketch(500d, extract);
		Spot spot = new Spot(1150d, 335d);
		appear(spot);
		Arrow arrow = new Arrow(extract, false, spot, true, commentFormat);
		sketch(500d, arrow);

		mark(464);
		Letters toThis = new Letters("to this", new CenterRight(1000d, 550d), commentFormat);
		sketch(500d, toThis);

		String afterText1 = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    handle(lidNotFound);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(afterText1, codeFormat, new TopRight(1550d,460d));
		appear(afterCode);

		String afterText2 = "public void handle(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(afterText2,codeFormat, new TopRight(1550d,640d));
		appear(afterCode2);

		mark(468);
		Letters andTestThis = new Letters("and test the handler here!", new BelowCenter(afterCode2), commentFormat);
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
		minor(" ");
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
		minor(" ");
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

		head("A Program's Stack");
		drawStack();

		mark(22);
		head("The Household Program");

		mark(26);
		Letters main = new Letters("main()", stackTextPosition(0), largeCodeFormat);
		disappearingStackText.add(main);
		sketch(500d, main);

		mark(30);
		doChores = new Letters("doChores()", stackTextPosition(1), largeCodeFormat);
		catchAndThrowColorText.add(doChores);
		sketch(500d, doChores);

		mark(33);
		Letters takeOutTrash = new Letters("takeOutTrash()", stackTextPosition(2), largeCodeFormat);
		disappearingStackText.add(takeOutTrash);
		sketch(500d, takeOutTrash);
		mark(34);

		mark(37);
		Letters putBagsInCan = new Letters("putBagsInCans()", stackTextPosition(3), largeCodeFormat);
		disappearingStackText.add(putBagsInCan);
		sketch(500d, putBagsInCan);

		mark(40);
		Letters putOneBagInCan = new Letters("putOneBagInCan()", stackTextPosition(4), largeCodeFormat);
		disappearingStackText.add(putOneBagInCan);
		sketch(500d, putOneBagInCan);

		mark(44);
		openCan = new Letters("openCan()", stackTextPosition(5), largeCodeFormat);
		sketch(500d, openCan);
		catchAndThrowColorText.add(openCan);

		Letters joke = joke("whoops, he forgot openCan()");
		mark(49);
		disappear(joke);

		return endBuild();
	}

	private void drawStack()
	{
		Stroke guideStroke = new Stroke(stackGrid.westLine(), stackFormat);
		sketch(500d, guideStroke);
		stack.add(guideStroke);

		for (int i = 0; i < stackGrid.rows(); i++)
		{
			Stroke topStroke = new Stroke(stackGrid.area(0, i).northLine(), stackFormat);
			sketch(500d, topStroke);
			stack.add(topStroke);
		}
		Stroke stackBottom = new Stroke(stackGrid.southLine(0, STACK_ROWS - 1), stackFormat);
		sketch(500d, stackBottom);
		stack.add(stackBottom);
	}

	private Position stackTextPosition(int line)
	{
		return new TopLeft(stackTextPoint(line));
	}
	
	private Point stackTextPoint(int line)
	{
		return stackGrid.northLine(0, STACK_ROWS - 1 - line).from.add(20, 20);
	}


	private Step special()
	{
		buildPhrase();
		reColor(openCan, Color.RED);
		reColor(doChores, Color.RED);

		mark(55);
		thrower = new Letters("Thrower", new TopRight(leftCommentTextPoint(5)), commentFormat);
		sketch(500d, thrower);
		mark(57);
		catcher = new Letters("Catcher", new TopRight(leftCommentTextPoint(1)), commentFormat);
		sketch(500d, catcher);

		mark(60);
		fadeDown(500d, stack, disappearingStackText);

		mark(64);
		Letters throwsLidNotFound = new Letters("throws LidNotFound", new TopLeft(stackTextPoint(5).add(new Point(0,50))), lightComment);
		catchAndThrowColorText.add(throwsLidNotFound);
		fadeIn(500d, throwsLidNotFound);

		mark(72);
		Letters catchesAll = new Letters("catches all exceptions", new TopLeft(stackTextPoint(1).add(new Point(0,50))), lightComment);
		catchAndThrowColorText.add(catchesAll);
		fadeIn(500d, catchesAll);

		mark(82);
		Letters catchesLidNotFound = new Letters("catches LidNotFound", new BelowLeft(catchesAll),lightComment);
		catchAndThrowColorText.add(catchesLidNotFound);
		fadeIn(500d, catchesLidNotFound);

		mark(86);
		Letters logsIt = new Letters("logs and moves on", new BelowLeft(catchesLidNotFound), lightComment);
		catchAndThrowColorText.add(logsIt);
		fadeIn(500d, logsIt);

		return endBuild();

	}

	private Point leftCommentTextPoint(int line)
	{
		return stackTextPoint(line).add(-40, -10);
	}

	private Step indirectCall()
	{
		buildPhrase();
		head("How Throw & Catch Work");
		fadeOut(500d, catchAndThrowColorText);

		mark(105);
		Arrow call = new Arrow(thrower, false, catcher, true, commentFormat);
		Letters letters = new Letters("call", new Centered(call), commentFormat);
		sketch(500d, call);
		sketch(500d, letters);

		mark(111);
		Cross cross = new Cross(call, 150d);
		sketch(500d, cross);

		fadeUp(500d, stack);

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
		Letters runtime = new Letters("Runtime", new Centered(new Point(1250d, 220d)), commentFormat);
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
		Letters compiletime = new Letters("Compile Time", new Centered(new Point(1250d, 550d)), commentFormat);
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

		OvalText thrower = new OvalText("Thrower", new Point(1000d, 380d), commentFormat);
		sketch(1000d, thrower);
		OvalText catcher = new OvalText("Catcher", new Point(1500d, 380d), commentFormat);

		sketch(1000d, catcher);

		mark(240);
		OvalText lnf = new OvalText("LidNotFound", new Point(1250d, 550d), commentFormat);
		sketch(500d, lnf);

		Arrow throwerLnf = new Arrow(thrower, false, lnf, true, knowsFormat);
		Arrow catcherLnf = new Arrow(catcher, false, lnf, true, knowsFormat);

		sketch(500d, throwerLnf);
		sketch(500d, catcherLnf);
		minor(" ");
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
		lead("This Is Far Easier");
		return endBuild();
	}

	private Arrow knowLine(Actors actors, double y, boolean leftHead)
	{
		Spot leftSpot = new Spot(1000d, y);
		sketch(1d, leftSpot);
		Spot rightSpot = new Spot(1500d, y);
		sketch(1d, rightSpot);
		Arrow line = chooseArrow(leftHead, leftSpot, rightSpot);
		Letters know = new Letters("knows?", new AboveCenter(line), knowsFormat);
		sketch(500d, line);
		sketch(500d, know);
		actors.add(leftSpot, rightSpot, line, know);
		return line;
	}

	private Arrow chooseArrow(boolean rightToLeft, Spot left, Spot right)
	{
		if (rightToLeft) return new Arrow(right, false, left, true, knowsFormat);
		else return new Arrow(left, false, right, true, knowsFormat);
	}

	private void throwsText(int line)
	{
		Letters letters = new Letters("throws X", stackTextPosition(line), commentFormat);
		sketch(500d, letters);
	}

	private void noCatchText(int line)
	{
		Letters letters = new Letters("catches X?", stackTextPosition(line), commentFormat);
		sketch(500d, letters);
		Letters no = new Letters("no, keep looking...", new RightOf(letters,20d),
				new Format(commentFormat, TypeFace.color(Color.RED, 1d)));
		sketch(1d, no);
	}

	private void catchText(int line)
	{
		Letters letters = new Letters("catches X?", stackTextPosition(line), commentFormat);
		sketch(500d, letters);
		Letters no = new Letters("YES! call this one!", new RightOf(letters,20d),
				new Format(commentFormat, TypeFace.color(Color.GREEN, 1d)));
		sketch(1d, no);
	}

	private void line(String text, Format format)
	{
		Position position = null;
		if(!lines.isEmpty()) position=new BelowRight(lines.get(lines.size()-1));
		else position = new TopRight(1550d,50d);
		Letters line = new Letters(text,position,format);
		sketch(500d, line);
		lines.add(line);
	}

	private void clearLines()
	{
		for (Actor line : lines)
		{
			disappear(line);
		}
		lines.clear();
	}

	private void head(String text)
	{
		clearLines();
		line(text, majorFormat);
	}

	private void lead(String text)
	{
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
		Letters joke = new Letters(text, new Centered(new Point(380d, 300d)), jokeFormat);
		appear(joke);
		return joke;
	}

	private Paint color(int r, int g, int b)
	{
		return new Color((double) r / 255d, (double) g / 255d, (double) b / 255d, 1d);
	}
}

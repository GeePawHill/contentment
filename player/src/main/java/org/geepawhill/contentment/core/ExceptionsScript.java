package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;

import org.geepawhill.contentment.actors.CodeBlock;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Grid;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.AboveCenter;
import org.geepawhill.contentment.position.BelowCenter;
import org.geepawhill.contentment.position.BelowLeft;
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

	private Format commentFormat;
	private Format knowsFormat;
	private Format codeFormat;
	private Format stackFormat;
	private Format largeCodeFormat;
	private Format jokeFormat;
	private Format lightComment;

	private Script script;
	
	private Grid stackGrid;

	public ExceptionsScript()
	{
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

		this.stackGrid = new Grid(1, STACK_ROWS, new PointPair(900d, 250d, 1480d, 850d));

		Font codeFont = new Font("Consolas", 25d);

		codeFormat = new Format(TypeFace.font(codeFont, 2d, 1d), TypeFace.color(codeColor, 1d),
				Frames.frame(codeColor, 2d, 1d));

		stackFormat = new Format(Frames.frame(Color.YELLOW, 2d, 1d));
		lightComment = new Format(TypeFace.font(commentFont, 1d, 1d), TypeFace.color(commentColor, 1d),
				Frames.frame(commentColor, 2d, 1d));
	}

	public Script make()
	{
		script = new Script(new MediaRhythm(new File("/faceoverPositionTrial.mp4")));
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
	
	private Step opening()
	{
		cue(0).slide().enter().head("Microtesting Exceptions");
		cue(3).slide().sub("A GeePaw Quickie");
		return endBuild();
	}
	
	private Step stack()
	{
		cue(16).slide().head("A Program's Stack");
		drawStack();
		
		cue(22).slide().head("The Household Program");
		
		cue(26).letters("main()").at(stackTextPosition(0)).format(largeCodeFormat).in("stackText").sketch();
		cue(30).letters("doChores()").at(stackTextPosition(1)).format(largeCodeFormat).in("remainder").sketch();
		cue(33).letters("takeOutTrash()").at(stackTextPosition(2)).format(largeCodeFormat).in("stackText").sketch();
		cue(37).letters("putBagsInCans()").at(stackTextPosition(3)).format(largeCodeFormat).in("stackText").sketch();
		cue(40).letters("putOneBagInCan()").at(stackTextPosition(4)).format(largeCodeFormat).in("stackText").sketch();
		cue(44).letters("openCan()").at(stackTextPosition(5)).format(largeCodeFormat).in("remainder").sketch();
		
		cue(44).letters("whoops, he forgot openCan()").at(new Centered(new Point(380d, 300d))).format(jokeFormat).called("joke").appear();
		cue(49).actor("joke").disappear();
		
		return endBuild();
	}

	private Step special()
	{
		buildPhrase();
		cue(50).party("remainder").reColor(Color.RED);

		cue(55).letters("Thrower").at(new TopRight(leftCommentTextPoint(5))).format(commentFormat).called("thrower").sketch();
		cue(57).letters("Catcher").at(new TopRight(leftCommentTextPoint(1))).format(commentFormat).called("catcher").sketch();

		cue(60).party("stackText").fadeDown();
		party("stackLines").fadeDown();

		cue(64).letters("throws LidNotFound").at(new TopLeft(stackTextPoint(5).add(new Point(0,50)))).format(lightComment).in("colorText").sketch();
		cue(72).letters("catches all exceptions").at(new TopLeft(stackTextPoint(1).add(new Point(0,50)))).format(lightComment).in("colorText").called("catchesAll").sketch();

		cue(82).letters("catches LidNotFound").at(new BelowLeft(actor("catchesAll").groupSource())).format(lightComment).in("colorText").called("catchesLidNotFound").sketch();

		cue(86).letters("logs and moves on").at(new BelowLeft(actor("catchesLidNotFound").groupSource())).format(lightComment).in("colorText").sketch();
		return endBuild();

	}
	private Step indirectCall()
	{
		buildPhrase();
		cue(96).slide().head("How Throw & Catch Work");
		party("colorText").fadeOut();

		cue(105).connector().from(actor("thrower").groupSource(),false).to(actor("catcher").groupSource(), true).format(commentFormat).sketch().called("calls");
		letters("call").at(new Centered(actor("calls").groupSource())).format(commentFormat).sketch();

		cue(111).actor(new Cross(world,actor("calls"),150d)).sketch();
		party("stackLines").fadeUp();
		party("remainder").fadeOut();
		
		cue(115).letters("throws X").at(stackTextPosition(5)).format(commentFormat).sketch();
		
		cue(120).letters("catches X?").at(stackTextPosition(4)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(124).letters("catches X?").at(stackTextPosition(3)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(128).letters("catches X?").at(stackTextPosition(2)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(132).letters("catches X?").at(stackTextPosition(1)).format(commentFormat).sketch().called("line");
		letters("YES! call this one!").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.GREEN, 1d))).sketch();
		return endBuild();
	}

	private Step dependencies()
	{
		buildPhrase();
		cue(140).wipe();
		slide().enter();
		slide().head("Dependencies");
		cue(146).letters("Thrower").withOval().at(new Centered(1000d,200d)).format(commentFormat).sketch().called("thrower");
		letters("Catcher").withOval().at(new Centered(1500d, 200d)).format(commentFormat).sketch().called("catcher");
		spot(1000,850).appear().in("allButOvals").called("throwerSpot");
		spot(1500,850).appear().in("allButOvals").called("catcherSpot");
		connector().from(actor("thrower").groupSource(),false).to(actor("throwerSpot").groupSource(), false).format(commentFormat).sketch().in("allButOvals");
		connector().from(actor("catcher").groupSource(),false).to(actor("catcherSpot").groupSource(),false).format(commentFormat).sketch().in("allButOvals");
		
		cue(153).letters("Runtime").at(new Centered(new Point(1250d, 220d))).format(commentFormat).sketch().in("allButOvals");

		cue(160);
		knowLine(350d, false);

		cue(166).actor(new Cross(world, actor("arrow").groupSource(), 125d, 100d, new Point(0d, -10d))).sketch().in("allButOvals");

		cue(172);
		knowLine(475d, true);

		cue(176).actor(new Cross(world, actor("arrow").groupSource(), 125d, 100d, new Point(0d, -10d))).sketch().in("allButOvals");

		cue(190).letters("Compile Time").at(new Centered(new Point(1250d, 550d))).format(commentFormat).sketch().in("allButOvals");

		cue(194);
		knowLine(680d, false);
		
		cue(202).actor(new Cross(world, actor("arrow").groupSource(), 125d, 100d, new Point(0d, -10d))).sketch().in("allButOvals");

		cue(207);
		knowLine(800d, true);

		cue(212).actor(new Cross(world, actor("arrow").groupSource(), 125d, 100d, new Point(0d, -10d))).sketch().in("allButOvals");

		return endBuild();
	}

	private Step noDependencies()
	{
		buildPhrase();
		cue(218).wipe().slide().enter();
		slide().head("No Dependencies");
		slide().sub("very different situation");

		cue(227).slide().minor("no direct call");
		slide().minor("so neither side knows the other");

		cue(233).letters("Thrower").withOval().at(new Centered(1000d,420d)).format(commentFormat).sketch().called("thrower");
		letters("Catcher").withOval().at(new Centered(1500d, 420d)).format(commentFormat).sketch().called("catcher");

		cue(240).letters("LidNotFound").withOval().at(new Centered(1250d, 590d)).format(commentFormat).sketch().called("lidNotFound");

		connector().from(actor("thrower").groupSource(), false).to(actor("lidNotFound").groupSource(), true).format(commentFormat).sketch();
		connector().from(actor("catcher").groupSource(), false).to(actor("lidNotFound").groupSource(), true).format(commentFormat).sketch();

		slide().minor("").minor("").minor("").minor("").minor("");
		
		cue(247).slide().minor("shared dependency irrelevant");

		cue(252).wipe().slide().enter();
		cue(252).slide().head("Easier To Test");
		cue(263).slide().sub("four nopes = one yep?");
		cue(268).slide().minor("test the thrower by itself");
		slide().minor("test the catcher by itself");

		cue(274).slide().lead("This Is Far Easier");
		return endBuild();
	}
	
	public Step whatToTest()
	{
		buildPhrase();
		cue(280).wipe().slide().enter();
		slide().head("What Do We Test?");
		cue(284).slide().sub("five cases");
		cue(288).slide().minor("each case asks a question");
		slide().minor("what happens when we ... ?");
		cue(295).slide().minor("(how to think of all microtests)");
		return endBuild();
	}

	public Step testingTheThrower()
	{
		buildPhrase();
		cue(300).wipe().slide().enter();
		slide().head("Testing The Thrower");
		letters("Thrower").withOval().at(new Centered(1250d, 210d)).format(commentFormat).sketch();
		cue(302).slide().lead(" ").minor("");
		slide().sub("throws under right condition?");
		cue(304).slide().minor("don't throw if the lid's right there");
		cue(309).slide().minor("don't throw if something else is wrong");
		cue(315).slide().minor("always & only throw when lid's not found");
		cue(324).slide().sub("throws the right thing?");
		cue(328).slide().minor("must throw right exception");
		cue(340).slide().minor("must build it correctly");
		cue(346).slide().minor("use an exception constructor for that");
		return endBuild();
	}
	
	public Step actualThrowerTest()
	{
		buildPhrase();
		cue(359).wipe().slide().enter();
		slide().head("Two Questions, One Test");
		cue(362).slide().sub("set up the throw condition");
		cue(364).slide().minor("remove lids programmatically");
		cue(370).slide().sub("call the thrower");
		slide().minor("call openCan()");
		cue(374).slide().sub("catch the exception");
		slide().minor("test catches LidNotFound");
		slide().minor("maybe inspect it");
		cue(382).slide().sub("non-throwing?");
		cue(385).slide().minor("write those first");
		return endBuild();
	}
	
	public Step testingTheCatcher()
	{
		buildPhrase();
		cue(402).wipe().slide().enter();
		slide().head("Testing The Catcher");
		letters("Catcher").withOval().at(new Centered(1250d, 210d)).format(commentFormat).sketch();
		cue(410).slide().lead(" ").minor(" ");
		slide().sub("does it really catch?");
		cue(413).slide().minor("set up throw");
		slide().minor("call doChores()");
		cue(419).slide().minor("test completes? doChores() caught it");
		cue(424).slide().sub("does it do something about it?");
		cue(435).slide().minor("supposed to dial home");
		cue(445).slide().minor("tell us the missing lid");

		return endBuild();
	}

	public Step complexCatchClause()
	{
		buildPhrase();
		cue(457).wipe().slide().enter();
		slide().head("Testing A Complex Catch");

		String beforeText = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
		CodeBlock beforeCode = new CodeBlock(world, beforeText, codeFormat, new TopRight(1550,260d));
		actor(beforeCode).appear();

		cue(460).letters("extract this").at(new CenterRight(1000d, 340d)).format(commentFormat).sketch().called("extract");
		spot(1150, 335).appear().called("spot");
		connector().from(actor("extract").groupSource(),false).to(actor("spot").groupSource(), true).format(commentFormat).sketch();

		cue(464).letters("to this").at(new CenterRight(1000d, 550d)).format(commentFormat).sketch();

		String afterText1 = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    handle(lidNotFound);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(world, afterText1, codeFormat, new TopRight(1550d,460d));
		actor(afterCode).appear();

		String afterText2 = "public void handle(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(world,afterText2, codeFormat, new TopRight(1550d,640d));
		actor(afterCode2).appear();

		cue(468).letters("and test the handler here!").at(new BelowCenter(afterCode2.groupSource())).format(commentFormat).sketch();
		return endBuild();
	}

	public Step finallyClause()
	{
		buildPhrase();
		cue(478).wipe().slide().enter();
		slide().head("Does The Finally Work?");
		cue(487).slide().sub("finally clauses clean things up");
		slide().minor("when it throws...");
		slide().minor("when it doesn't throw");
		cue(492).slide().sub("the bag has been allocated");
		cue(498).slide().minor("okay? de-allocate it");
		cue(504).slide().minor("uh-oh? de-allocate it");
		return endBuild();
	}
	
	public Step complexFinallyClause()
	{
		buildPhrase();
		cue(527).wipe().slide().enter();
		slide().head("Testing A Complex Finally");

		String beforeText = "finally {\n" + "    // complex finally\n" + "    }";
		CodeBlock beforeCode = new CodeBlock(world, beforeText, codeFormat, new TopRight(1550,260d));
		actor(beforeCode).appear();

		cue(460).letters("extract this").at(new CenterRight(1000d, 340d)).format(commentFormat).sketch().called("extract");
		spot(1280,335).appear().called("spot");
		connector().from(actor("extract").groupSource(),false).to(actor("spot").groupSource(),true).format(commentFormat).sketch();

		cue(464).letters("to this").at(new CenterRight(1000d, 550d)).format(commentFormat).sketch().called("toThis");

		String afterText1 = "finally {\n" + "    handleFinally(...);\n" + "    }";
		CodeBlock afterCode = new CodeBlock(world, afterText1, codeFormat, new TopRight(1550d,460d));
		actor(afterCode).appear();

		String afterText2 = "public void handleFinally(...) {\n" + "    // complex finally\n" + "    }";
		CodeBlock afterCode2 = new CodeBlock(world,afterText2, codeFormat, new TopRight(1550d,640d));
		actor(afterCode2).appear();

		cue(468).letters("and test the handler here!").at(new BelowCenter(afterCode2.groupSource())).format(commentFormat).sketch();

		return endBuild();
	}

	public Step exceptionsAreEasy()
	{
		buildPhrase();
		cue(540).wipe().slide().enter();
		slide().head("Microtesting Exceptions Is Easy");
		slide().sub("thrower test questions");
		slide().minor("throws on the right condition?");
		slide().minor("throws the right thing?");
		slide().sub("catcher test questions");
		slide().minor("catches the right thing?");
		slide().minor("catch handler right?");
		slide().minor("finally handler right?");
		cue(550).slide().sub("remember the judgment premise!");
		cue(574).letters("Bad Pun Alert!").at(new Centered(new Point(380d, 300d))).format(jokeFormat).appear();
		return endBuild();
	}

	private void drawStack()
	{
		stroke(stackGrid.westLine()).format(stackFormat).in("stackLines").sketch();
		for (int i = 0; i < stackGrid.rows(); i++)
		{
			stroke(stackGrid.area(0, i).northLine()).format(stackFormat).in("stackLines").sketch();
		}
		stroke(stackGrid.southLine(0, STACK_ROWS - 1)).format(stackFormat).in("stackLines").sketch();
	}

	private Position stackTextPosition(int line)
	{
		return new TopLeft(stackTextPoint(line));
	}
	
	private Point stackTextPoint(int line)
	{
		return stackGrid.northLine(0, STACK_ROWS - 1 - line).from.add(20, 20);
	}

	private Point leftCommentTextPoint(int line)
	{
		return stackTextPoint(line).add(-40, -10);
	}

	private void knowLine(double y, boolean leftHead)
	{
		spot(1000d, y).appear().called("leftSpot").in("allButOvals");
		spot(1500d, y).appear().called("rightSpot").in("allButOvals");
		connector().from(actor("leftSpot").groupSource(),leftHead).to(actor("rightSpot").groupSource(), !leftHead).format(knowsFormat).sketch().called("arrow").in("allButOvals");
		letters("knows?").at(new AboveCenter(actor("arrow").groupSource())).format(knowsFormat).sketch().in("allButOvals");
	}
}

package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;

import org.geepawhill.contentment.actors.CodeBlock;
import org.geepawhill.contentment.actors.Column;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.FixedLetters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Grid;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.ViewPort;
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

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ExceptionsScript extends ScriptBuilder<ExceptionsScript>
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

	private Slide slide;

	public ExceptionsScript()
	{
		slide = new Slide(world);
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

		this.stackGrid = new Grid(1, STACK_ROWS, new PointPair(900d, 230d, 1480d, 820d));

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
		script.add(new Keyframe(58, special()));
		script.add(new Keyframe(111, indirectCall()));
		script.add(new Keyframe(160, dependencies()));
		script.add(new Keyframe(190, whatToTest()));
		script.add(new Keyframe(214, simplestCase()));
		script.add(new Keyframe(257, testingTheCatcher()));
		script.add(new Keyframe(282, stackDistance()));
		script.add(new Keyframe(320, playingFair()));
		script.add(new Keyframe(353, cheating()));
		script.add(new Keyframe(389, extractAndOverride()));
		script.add(new Keyframe(416, theTestableCatcher()));
		script.add(new Keyframe(475, notReallyCheating()));
		script.add(new Keyframe(490, outro()));
		return script;
	}
	
	private Step opening()
	{
		buildPhrase();
		cue(0).slide().enter().head("Microtesting Exceptions");
		slide().sub("A GeePaw Quickie");
		cue(5);
		slide().head("The Household Program");
		cue(7);
		stroke(stackGrid.southLine(0, STACK_ROWS - 1)).format(stackFormat).in("stackLines");
		stroke(stackGrid.westLine()).format(stackFormat).in("stackLines");
		for (int i = stackGrid.rows()-1; i >=0; i--)
		{
			stroke(stackGrid.area(0, i).northLine()).format(stackFormat).in("stackLines");
		}
		party("stackLines").fadeIn();
		party("stackLines").in("stackTextAndLines");
		
		cue(14).letters("main()").at(stackTextPosition(0)).format(largeCodeFormat).in("stackText").sketch();
		cue(22).letters("doChores()").at(stackTextPosition(1)).format(largeCodeFormat).in("remainder").sketch();
		cue(29).letters("clean()").at(stackTextPosition(2)).format(largeCodeFormat).in("stackText").sketch();
		cue(33).letters("takeOutTrash()").at(stackTextPosition(3)).format(largeCodeFormat).in("stackText").sketch();
		cue(44).letters("replaceTrashBag()").at(stackTextPosition(4)).format(largeCodeFormat).in("stackText").sketch();
		cue(50).letters("getBag()").at(stackTextPosition(5)).format(largeCodeFormat).in("remainder").sketch();
		party("stackText").in("stackTextAndLines");
		
		return endBuild();
	}

	private Step special()
	{
		buildPhrase();
		cue(60).party("remainder").reColor(Color.RED);

		cue(64).letters("Thrower").at(new TopRight(leftCommentTextPoint(5))).format(commentFormat).called("thrower").sketch();

		party("stackTextAndLines").fadeDown();

		cue(75).letters("throws BagNotFound").at(new TopLeft(stackTextPoint(5).add(new Point(0,50)))).format(lightComment).in("colorText").sketch();
		cue(85).letters("Catcher").at(new TopRight(leftCommentTextPoint(1))).format(commentFormat).called("catcher").sketch();
		cue(87).letters("catches all exceptions").at(new TopLeft(stackTextPoint(1).add(new Point(0,50)))).format(lightComment).in("colorText").called("catchesAll").sketch();

		cue(90).letters("catches BagNotFound").at(new BelowLeft(actor("catchesAll").groupSource())).format(lightComment).in("colorText").called("catchesLidNotFound").sketch();

		cue(96).letters("logs and moves on").at(new BelowLeft(actor("catchesLidNotFound").groupSource())).format(lightComment).in("colorText").sketch();
		return endBuild();
	}
	
	private Step indirectCall()
	{
		buildPhrase();
		cue(111).slide().head("How Throw & Catch Work");
		party("colorText").fadeOut();

		cue(119).connector().from(actor("thrower").groupSource(),false).to(actor("catcher").groupSource(), true).format(commentFormat).sketch().called("calls");
		letters("direct\n  call?").at(new Centered(actor("calls").groupSource())).format(commentFormat).sketch();

		cue(121).actor(new Cross(world,actor("calls"),150d)).sketch();
		party("remainder").disappear();
		party("stackLines").fadeUp();
		
		cue(126).letters("throws X").at(stackTextPosition(5)).format(commentFormat).sketch();
		
		cue(132).letters("catches X?").at(stackTextPosition(4)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(137).letters("catches X?").at(stackTextPosition(3)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(141).letters("catches X?").at(stackTextPosition(2)).format(commentFormat).sketch().called("line");
		letters("no, keep looking...").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.RED, 1d))).sketch();
		
		cue(150).letters("catches X?").at(stackTextPosition(1)).format(commentFormat).sketch().called("line");
		letters("YES! call this one!").at(new RightOf(actor("line").groupSource(),20d)).format(new Format(commentFormat, TypeFace.color(Color.GREEN, 1d))).sketch();
		return endBuild();
	}

	private Step dependencies()
	{
		buildPhrase();
		wipe();
		slide().enter();
		slide().head("Indirect Connection");
		cue(161).letters("Thrower").withOval().at(new Centered(1000d,300d)).format(commentFormat).sketch().called("thrower");
		letters("Catcher").withOval().at(new Centered(1500d, 300d)).format(commentFormat).sketch().called("catcher");
		connector().from(actor("catcher").groupSource(),true).to(actor("thrower").groupSource(), true).format(commentFormat).sketch().in("allButOvals").called("knows");
		letters("knows?").at(new AboveCenter(actor("knows").groupSource())).format(knowsFormat).sketch().in("allButOvals");
		cue(169).actor(new Cross(world, actor("knows").groupSource(), 125d, 100d, new Point(0d, -10d))).sketch().in("allButOvals");


		cue(171).letters("BagNotFound").withOval().at(new Centered(1250d, 590d)).format(commentFormat).sketch().called("lidNotFound");

		connector().from(actor("thrower").groupSource(), false).to(actor("lidNotFound").groupSource(), true).format(commentFormat).sketch().called("first");
		connector().from(actor("catcher").groupSource(), false).to(actor("lidNotFound").groupSource(), true).format(commentFormat).sketch().called("second");

		return endBuild();
	}
	
	public Step whatToTest()
	{
		buildPhrase();
		wipe().slide().enter().head("What Could Go Wrong?");
		cue(193);
		letters("Thrower").withOval().at(new Centered(1200d,300d)).format(commentFormat).sketch().called("thrower");
		Column column = new Column(world,new PointPair(1000,400,ViewPort.WIDTH,ViewPort.HEIGHT),HPos.LEFT,VPos.TOP);
		cue(196);
		column.enter();
		column.head(new LettersAtom(column.groupSource(),"1. Notice the fail condition",commentFormat,Position.DEFAULT));
		cue(199);
		column.line(new LettersAtom(column.groupSource(),"2. Construct the exception",commentFormat,Position.DEFAULT));
		cue(202);
		column.line(new LettersAtom(column.groupSource(),"3. throw the exception",commentFormat,Position.DEFAULT));
		return endBuild();
	}

	public Step simplestCase()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Forcing The Throw");
		FixedLetters code = new FixedLetters(world, 52, 14);
		code.at(new TopRight(1550,300)).appear();
		code.assume(Color.WHITE);
		cue(220);
		code.say(4, 8, "Supplies supplies = new Supplies");
		cue(230);
		code.say(5, 8, "supplies.getBag()");
		cue(238);
		code.say(2, 4, "TRY");
		code.say(3, 4, "{");
		code.say(6, 8, "FAIL \"We should have thrown here.\"");
		code.say(7, 4, "}");

		cue(240);
		code.say(0, 0, "TEST throwsOnEmpty()");
		code.say(1, 0, "{");
		code.say(12, 0, "}");
		
		cue(246);
		code.say(8, 4, "CATCH( BagNotFound exception )");
		code.say(9,4, "{");
		code.say(10, 8, "ASSERT exception.msg EQUALS BagNotFound.MSG");
		code.say(11, 4, "}");
		
	
		
//		letters("Thrower").withOval().at(new Centered(1250d, 210d)).format(commentFormat).sketch();
//		cue(302).slide().lead(" ").minor("");
//		slide().sub("throws under right condition?");
//		cue(304).slide().minor("don't throw if the lid's right there");
//		cue(309).slide().minor("don't throw if something else is wrong");
//		cue(315).slide().minor("always & only throw when lid's not found");
//		cue(324).slide().sub("throws the right thing?");
//		cue(328).slide().minor("must throw right exception");
//		cue(340).slide().minor("must build it correctly");
//		cue(346).slide().minor("use an exception constructor for that");
		return endBuild();
	}
	
	public Step testingTheCatcher()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Testing The Catcher");
//		letters("Catcher").withOval().at(new Centered(1250d, 210d)).format(commentFormat).sketch();
//		cue(410).slide().lead(" ").minor(" ");
//		slide().sub("does it really catch?");
//		cue(413).slide().minor("set up throw");
//		slide().minor("call doChores()");
//		cue(419).slide().minor("test completes? doChores() caught it");
//		cue(424).slide().sub("does it do something about it?");
//		cue(435).slide().minor("supposed to dial home");
//		cue(445).slide().minor("tell us the missing lid");

		return endBuild();
	}
	
	public Step stackDistance()
	{
		buildPhrase();
		wipe().slide().enter().head("A Difficult Case");
		return endBuild();
	}

	public Step playingFair()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Playing Fair");

//		String beforeText = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
//		CodeBlock beforeCode = new CodeBlock(world, beforeText, codeFormat, new TopRight(1550,260d));
//		actor(beforeCode).appear();
//
//		cue(460).letters("extract this").at(new CenterRight(1000d, 340d)).format(commentFormat).sketch().called("extract");
//		spot(1150, 335).appear().called("spot");
//		connector().from(actor("extract").groupSource(),false).to(actor("spot").groupSource(), true).format(commentFormat).sketch();
//
//		cue(464).letters("to this").at(new CenterRight(1000d, 550d)).format(commentFormat).sketch();
//
//		String afterText1 = "try { ... }\n" + "catch(LidNotFound lidNotFound) {\n" + "    handle(lidNotFound);\n" + "    }";
//		CodeBlock afterCode = new CodeBlock(world, afterText1, codeFormat, new TopRight(1550d,460d));
//		actor(afterCode).appear();
//
//		String afterText2 = "public void handle(LidNotFound lidNotFound) {\n" + "    // complex catch\n" + "    }";
//		CodeBlock afterCode2 = new CodeBlock(world,afterText2, codeFormat, new TopRight(1550d,640d));
//		actor(afterCode2).appear();
//
//		cue(468).letters("and test the handler here!").at(new BelowCenter(afterCode2.groupSource())).format(commentFormat).sketch();
		return endBuild();
	}

	public Step cheating()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("How Does Cheating Work?");
		return endBuild();
	}
	
	public Step extractAndOverride()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Extract & Override");

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

	public Step theTestableCatcher()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("The Testable Catcher");
		return endBuild();
	}
	
	public Step notReallyCheating()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Not Really Cheating");
		return endBuild();
	}
	
	public Step outro()
	{
		buildPhrase();
		wipe().slide().enter();
		slide().head("Outtro");
		return endBuild();
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

	public ExceptionsScript downcast()
	{
		return this;
	}
	
	private Slide slide()
	{
		return slide;
	}
}

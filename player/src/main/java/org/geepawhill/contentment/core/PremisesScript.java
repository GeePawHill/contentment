package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.awt.SecondaryLoop;
import java.io.File;
import java.util.Vector;

import org.geepawhill.contentment.actors.CodeBlock;
import org.geepawhill.contentment.actors.Column;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.FixedLetters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.format.Style;
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
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class PremisesScript extends ScriptBuilder<PremisesScript> {
	private static final double XMARGIN = 20;
	private static final double YMARGIN = 20;

	private Script script;

	private Format secondaryJumbo;
	private Format primaryJumbo;
	private Format emphasisFormat;
	private Format secondaryNormal;
	private Format primaryNormal;
	private Format emphaticNormal;
	private Format emphaticSmall;
	private Format emphaticJumbo;
	private Format secondarySmall;
	private Format primarySmall;

	public PremisesScript() {

		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		Paint primary = color(119, 187, 65);
		Paint secondary = color(177, 140, 254);
		Paint emphatic = color(255, 255, 0);

		primaryJumbo = format(primary, jumbo);
		primaryNormal = format(primary, normal);
		primarySmall = format(primary,small);

		secondaryJumbo = format(secondary, jumbo);
		secondaryNormal = format(secondary, normal);
		secondarySmall = new Format(format(secondary, small),Frames.frame(secondary, 3d, .7d));
		

		emphaticJumbo = format(emphatic, jumbo);
		emphaticNormal = format(emphatic, normal);
		emphaticSmall = new Format(format(emphatic, small),Frames.frame(emphatic, 3d, .7d));

		emphasisFormat = new Format(Frames.frame(emphatic, 4d, .7d));
	}

	private Format format(Paint majorColor, double fontsize) {
		Font font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize);
		return new Format(TypeFace.font(font, 2d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
	}

	public Script make() {
		script = new Script(
//				new MediaRhythm(new File("d:\\GeePawHillDotOrg\\videos\\premises.home\\TddPremisesFaceoverFinal.mp4")));
				new SimpleRhythm());
		script.add(new Keyframe(0, leadIn()));
		script.add(new Keyframe(3, opening()));
		script.add(new Keyframe(30, money()));
		script.add(new Keyframe(76, money2()));
		script.add(new Keyframe(132, money3()));
		script.add(new Keyframe(151, judgment()));
		script.add(new Keyframe(194, judgment2()));
		script.add(new Keyframe(234, judgment3()));
		script.add(new Keyframe(251, correlation()));
		script.add(new Keyframe(270, correlation2()));
		script.add(new Keyframe(318,correlation3()));
		script.add(new Keyframe(379, chaining()));
		script.add(new Keyframe(415, chaining2()));
		script.add(new Keyframe(446, steering()));
		script.add(new Keyframe(510, steering2()));
		script.add(new Keyframe(528, underplayed()));
		script.add(new Keyframe(598, outro()));
		return script;
	}
	
	private Gesture leadIn() {
		buildPhrase();
		wipe();
		cue(0);
		letters("GeePaw's Notebook:").format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").appear();
		assume(secondaryJumbo);
		letters("Five Underplayed Premises\nof Test-Driven Development").centered(450, 450).appear();
		assume(emphaticSmall);
		letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(new TopLeft(20,825)).appear();
		offset(3);

		return endBuild();
	}

	private Gesture opening() {
		buildPhrase();
		wipe();
		cue(0);
		header("Five Underplayed TDD Premises");
		Vector<Point> inner = polygon(5, 225, new Point(450, 450));
		Vector<Point> outer = polygon(5, 275, new Point(450, 450));
		offset(12);
		assume(secondaryNormal);
		letters("money").at(new Centered(outer.get(0))).sketch();
		offset(1);
		letters("judgment").at(new Centered(outer.get(1).add(45d, 0))).sketch();
		offset(1);
		letters("correlation").at(new Centered(outer.get(2))).sketch();
		offset(1);
		letters("chain").at(new Centered(outer.get(3))).sketch();
		offset(1);
		letters("steering").at(new Centered(outer.get(4).add(-42d, 0))).sketch();

		assume(primaryNormal);
		offset(3);
		for (int i = 0; i < 5; i++) {
			spot(inner.get(i).x, inner.get(i).y).called("P" + i).appear();
		}
		for (int i = 0; i < 5; i++) {
			connector().from("P" + i, false).to("P" + (i + 1) % 5, false).sketch();
		}

		letters("TDD\nLives\nHere").at(new Centered(450, 450)).format(secondaryNormal).sketch();

		return endBuild();
	}

	public Gesture money() {
		buildPhrase();
		cue(30);
		wipe();
		header("Money:  ");
		offset(7);
		headerEnd("We're In This For The Money");
		offset(8);
		assume(primaryJumbo);
		letters("Money").centered(290, 400).in("part1").sketch();
		letters("=").centered(450, 400).in("part1").sketch();
		offset(2);
		letters("Shipping\nMore\nValue\nFaster").centered(650, 400).in("part1").sketch();
		offset(5);
		emphasize(530, 245, 290, "part1");
		offset(1);
		emphasize(530, 140, 385, "part1");
		offset(1);
		emphasize(530, 155, 480, "part1");
		offset(1);
		emphasize(530, 200, 577, "part1");
		offset(8);
		assume(secondaryJumbo);
		letters("TDD makes us money.").centered(450, 720).in("part1").sketch();
		letters("and that's why we do it.").centered(450, 800).in("part1").sketch();

		return endBuild();
	}

	public Gesture money2() {
		buildPhrase();
		cue(75);
		party("part1").fadeOut();
		assume(primaryNormal);
		letters("some things TDD is not about...").at(new TopLeft(30, 140)).in("part2").sketch();

		offset(9);
		assume(secondaryNormal);
		letters("good citizenship?").at(new TopLeft(30, 220)).called("citizenship").in("part2").sketch();
		assume(primaryNormal);
		lettersBelow("   responsibility for the future", "citizenship", "responsibility", "part2");
		lettersBelow("   morality or decency", "responsibility", "morality", "part2");
		offset(6);
		cross("citizenship", 320, 40, 0, 10).in("part2").sketch();
		assume(emphaticNormal);
		letters("nope, not even close!").at(new RightOf(actor("citizenship").groupSource(), 40d)).in("part2").sketch();

		offset(11);
		assume(secondaryNormal);
		lettersBelow("quality?", "morality", "quality", "part2");
		assume(primaryNormal);
		lettersBelow("   proving customer delight", "quality", "delight", "part2");
		lettersBelow("   killing all defects", "delight", "help", "part2");
		offset(4);
		cross("quality", 140, 40, 0, 10).in("part2").sketch();
		assume(emphaticNormal);
		letters("naww, tho it might help!").at(new RightOf(actor("quality").groupSource(), 40d)).in("part2").sketch();
		offset(4);

		offset(12);
		assume(secondaryNormal);
		lettersBelow("art or craft?", "help", "art", "part2");
		assume(primaryNormal);
		lettersBelow("   the glow of excellent workmanship", "art", "craft", "part2");
		lettersBelow("   beauty, elegance, grace", "craft", "beauty", "part2");
		offset(1);
		cross("art", 250, 40, 0, 10).in("part2").sketch();
		assume(emphaticNormal);
		letters("no, but it makes good designs!").at(new RightOf(actor("art").groupSource(), 40d)).in("part2").sketch();
		return endBuild();
	}

	public Gesture money3() {
		buildPhrase();
		cue(132);
		party("part2").fadeOut();
		assume(primaryJumbo);
		letters("We TDD only to...").at(new Centered(new Point(450, 250))).called("we").sketch();
		assume(secondaryJumbo);
		letters("Ship").at(new BelowCenter(actor("we").groupSource())).called("shipping").sketch();
		letters("More").at(new BelowCenter(actor("shipping").groupSource())).called("more").sketch();
		letters("Value").at(new BelowCenter(actor("more").groupSource())).called("value").sketch();
		letters("Faster").at(new BelowCenter(actor("value").groupSource())).sketch();
		return endBuild();
	}

	public Gesture judgment() {
		buildPhrase();
		cue(151);
		wipe();
		header("Judgment:  ");
		offset(5);
		headerEnd("We Rely On Human Judgment");
		offset(12);
		assume(secondaryNormal);
		letters("this seems like TDD...").at(new TopLeft(30, 150)).in("part1").sketch();
		FixedLetters code = new FixedLetters(world, 26, 13);
		code.at(new TopLeft(150, 230)).in("part1").called("code").appear();
		code.assume(Color.WHITE);
		code.say(0, 0, "TestDrivenDevelopment()");
		code.say(1, 0, "{");
		code.say(2, 3, "while(!shipping())");
		code.say(3, 3, "{");
		code.say(4, 6, "while(!storyDone())");
		code.say(5, 6, "{");
		code.say(6, 9, "red();");
		code.say(7, 9, "green();");
		code.say(8, 9, "refactor()");
		code.say(9, 9, "push()");
		code.say(10, 6, "}");
		code.say(11, 3, "}");
		code.say(12, 0, "}");
		offset(7);
		letters("but it's not.").at(new TopLeft(30, 770)).in("part1").sketch();
		cross("code", 400, 400, 0, 0).in("part1").sketch();
		return endBuild();
	}

	public Gesture judgment2() {
		buildPhrase();
		cue(194);
		party("part1").fadeOut();
		assume(secondaryNormal);
		letters("Programming is translating").at(new Centered(450, 200)).in("part2").sketch();
		assume(primaryJumbo);
		letters("Human Words").withOval().at(new Centered(450, 330)).called("human").in("part2").sketch();
		letters("Computer Program").withOval().at(new Centered(450, 730)).called("computer").in("part2").sketch();
		connector().from("human", false).to("computer", true).in("part2").sketch();
		offset(6);
		assume(emphaticSmall);
		letters("just add\ngeeks!").at(new TopLeft(250, 480)).in("part2").sketch();
		offset(3);
		letters("(some\nconsciousness\nrequired)").at(new TopRight(new Point(750, 450))).in("part2").sketch();
		return endBuild();
	}

	public Gesture judgment3() {
		buildPhrase();
		cue(234);
		party("part2").fadeOut();
		assume(secondaryJumbo);
		letters("We are...").at(new Centered(450, 200)).called("we").sketch();
		assume(primaryJumbo);
		belowCentered("...absolutely...", "we", "absolutely", "part3");
		offset(2);
		belowCentered("...routinely...", "absolutely", "routinely", "part3");
		offset(2);
		belowCentered("...everyday...", "routinely", "everyday", "part3");
		offset(1);
		belowCentered("...all the time...", "everyday", "always", "part3");
		assume(emphaticJumbo);
		offset(1);
		belowCentered("...happily...", "always", "happily", "part3");
		assume(secondaryJumbo);
		belowCentered("dependent on human judgment", "happily", "dependent", "part3");
		return endBuild();
	}

	public Gesture correlation() {
		buildPhrase();
		cue(251);
		wipe();
		header("Correlation:  ");
		offset(5);
		headerEnd("Internal Quality & Productivity Correlate");
		assume(primaryNormal);
		offset(2);
		letters("(Internal quality is IQ.)").at(new Centered(480,190)).in("part1").sketch();

		offset(2);
		assume(secondaryJumbo);
		letters("When IQ goes up...").at(new Centered(300,290)).in("part1").sketch();
		assume(primaryJumbo);
		letters("...productivity goes up!").at(new Centered(600,370)).in("part1").sketch();
		
		offset(1);
		assume(secondaryJumbo);
		letters("When IQ goes down...").at(new Centered(300,490)).in("part1").sketch();
		assume(primaryJumbo);
		letters("...productivity goes down!").at(new Centered(600,570)).in("part1").sketch();
		return endBuild();
	}

	public Gesture correlation2() {
		buildPhrase();
		cue(272);
		party("part1").fadeOut();
		assume(secondaryNormal);
		letters("External").at(new Centered(220, 200)).called("external").in("part2").sketch();
		offset(3);
		assume(primaryJumbo);
		belowCentered("Users See It", "external", "users", "part2");
		offset(4);
		assume(secondaryNormal);
		belowCentered("fast?", "users", "fast", "part2");
		offset(2);
		belowCentered("complete?", "fast", "complete", "part2");
		offset(5);
		belowCentered("stable?", "complete", "stable", "part2");
		offset(3);
		belowCentered("pretty?", "stable", "pretty", "part2");
		offset(1);
		belowCentered("fluid?", "pretty", "fluid", "part2");

		offset(4);
		assume(secondaryNormal);
		letters("Internal").at(new Centered(750, 200)).called("internal").in("part2").sketch();
		
		offset(3);
		assume(primaryJumbo);
		belowCentered("Geeks See It", "internal", "geeks", "part2");
		assume(secondaryNormal);
		
		offset(5);
		belowCentered("scannable?", "geeks", "scannable", "part2");
		belowCentered("readable?", "scannable", "readable", "part2");
		offset(5);
		belowCentered("well-factored?", "readable", "factored", "part2");
		offset(2);
		belowCentered("changeable?", "factored", "changeable", "part2");
		offset(4);
		belowCentered("tested?", "changeable", "tested", "part2");
		return endBuild();
	}
	
	public Gesture correlation3() {
		buildPhrase();
		cue(319);
		party("part2").fadeOut();
		assume(secondaryJumbo);
		letters("EQ *can* trade for productivity").at(new TopLeft(30, 200)).called("eq").in("part3").sketch();
		offset(4);
		assume(primaryNormal);
		lettersBelow("   less speed, completeness, or beauty","eq","lessEQ","part3");
		offset(8);
		lettersBelow("   means more time to work on other things","lessEQ","time","part3");
		
		offset(8);
		assume(secondaryJumbo);
		letters("IQ *can't* trade for productivity").at(new TopLeft(30, 500)).called("iq").in("part3").sketch();
		offset(2);
		assume(primaryNormal);
		lettersBelow("    less effort towards changeability","iq","changing","part3");
		lettersBelow("    means more effort to change","changing","expense","part3");
		lettersBelow("    means less time to work on other things","expense","other","part3");
		return endBuild();
	}
	
	public Gesture chaining() {
		buildPhrase();
		wipe();
		cue(380);
		header("Chaining: ");
		offset(4);
		headerEnd("Test A Chain By Testing Links");
		
		offset(10);
		assume(primaryNormal);
		letters(" A ").at(new Centered(250,250)).withOval().called("a").sketch();
		letters(" B ").at(new Centered(375,375)).withOval().called("b").sketch();
		letters(" C ").at(new Centered(500,500)).withOval().called("c").sketch();
		letters(" D ").at(new Centered(625,625)).withOval().called("d").sketch();
		letters(" E ").at(new Centered(750,750)).withOval().called("e").sketch();
		assume(secondarySmall);
		letters("layers, packages\nobjects, or functions").at(new Centered(800,420)).called("pieces").in("part1").sketch();
		spot(640, 480).called("piecesMark").in("part1").appear();
		connector().from("piecesMark", false).to("c", false).in("part1").sketch();
		connector().from("piecesMark", false).to("d", false).in("part1").sketch();

		offset(7);
		assume(primaryNormal);
		connector().from("a", false).to("b", true).sketch();
		connector().from("b", false).to("c", true).sketch();
		connector().from("c", false).to("d", true).sketch();
		connector().from("d", false).to("e", true).sketch();
		
		assume(secondarySmall);
		letters("dependencies").at(new Centered(470,770)).called("dependencies").in("part1").sketch();
		spot(565, 570).called("dep1").in("part1").appear();
		spot(675, 680).called("dep2").in("part1").appear();
		connector().from("dependencies", false).to("dep1", false).in("part1").sketch();
		connector().from("dependencies", false).to("dep2", false).in("part1").sketch();
		return endBuild();                                             
	}

	public Gesture chaining2() {
		buildPhrase();
		cue(414);
		party("part1").fadeOut();
		assume(secondarySmall);
		offset(8);
		sketchRect(new PointPair(150,150,440,430), "1");
		letters("test that A works...").at(new TopLeft(500,180)).sketch();
		letters("...assuming that B works").at(new TopLeft(500,230)).sketch();
		offset(8);
		sketchRect(new PointPair(300,300,560,580),"2");
		letters("and B works...").at(new TopLeft(650,320)).sketch();
		letters("...if C works").at(new TopLeft(650,370)).sketch();
		
		offset(8);
		assume(emphaticNormal);
		letters("Chain Tests Are\nThe Cheapest Tests").at(new TopLeft(100,600)).sketch();
		return endBuild();                                             
	}

	private void sketchRect(PointPair bounds, String name) {
		spot(bounds.from.x,bounds.from.y).called("nw"+name).appear();
		spot(bounds.from.x,bounds.to.y).called("sw"+name).appear();
		spot(bounds.to.x,bounds.from.y).called("ne"+name).appear();
		spot(bounds.to.x,bounds.to.y).called("se"+name).appear();
		connector().from("nw"+name, false).to("sw"+name, false).sketch();
		connector().from("sw"+name,false).to("se"+name, false).sketch();
		connector().from("se"+name, false).to("ne"+name, false).sketch();
		connector().from("ne"+name, false).to("nw"+name,false).sketch();
	}

	public Gesture steering() {
		buildPhrase();
		wipe();
		header("Steering:  ");
		offset(17);
		headerEnd("Testability Helps Steer Our Designs");
		offset(10);
		assume(secondaryNormal);
		letters("factors in our design process...").at(new Centered(450,160)).in("part1").sketch();
		assume(primaryNormal);
		offset(6);
		letters("market").withOval().centered(200,300).in("part1").sketch();
		offset(6);
		letters("platform").withOval().centered(700, 700).in("part1").sketch();
		offset(6);
		letters(" price ").withOval().centered(450, 700).in("part1").sketch();
		offset(3);
		letters("geek skills").withOval().centered(700, 300).in("part1").sketch();
		offset(2);
		letters("stability").withOval().centered(200, 500).in("part1").sketch();
		letters("predictors").withOval().centered(700, 500).in("part1").sketch();
		offset(2);
		letters("priorities").withOval().centered(450, 300).in("part1").sketch();
		letters("performance").withOval().centered(200,700).in("part1").sketch();
		
		offset(10);
		assume(emphaticNormal);
		letters(" tests &\ntestability").withOval().centered(450, 500).in("part1").sketch();
		
		return endBuild();
	}
	
	public Gesture steering2() {
		buildPhrase();
		cue(510);
		party("part1").fadeOut();
		assume(primaryNormal);
		letters("all through development we ask...").at(new Centered(450,180)).sketch();
		assume(secondaryJumbo);
		offset(2);
		letters("is this testable?").at(new Centered(450,280)).called("testable").sketch();
		offset(2);
		belowCentered("is the test cheap?", "testable", "cheap", "part2");
		offset(2);
		belowCentered("can we make it cheaper?","cheap","cheaper", "part2");
		
		offset(4);
		assume(primaryNormal);
		letters("Tests and testability become").at(new Centered(450,600)).called("tandt").sketch();
		assume(emphaticNormal);
		belowCentered("first class factors","tandt","first","part2");
		assume(primaryNormal);
		belowCentered("in steering our design.","first","steer","part2");
		return endBuild();
	}

	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").sketch();
	}

	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").groupSource())).sketch();
	}

	public Gesture underplayed() {
		buildPhrase();
		wipe();
		cue(528);
		header("Underplayed?  ");
		Vector<Point> pentagon = polygon(5, 200, new Point(450, 450));

		Vector<Point> pentagon2 = polygon(5, 250, new Point(450, 450));
		assume(secondaryNormal);
		offset(3);
		letters("money").at(new Centered(pentagon2.get(0))).sketch();
		offset(1);
		letters("judgment").at(new Centered(pentagon2.get(1).add(38d, 0))).called("judgment").sketch();
		offset(1);
		letters("correlation").at(new Centered(pentagon2.get(2))).called("correlation").sketch();
		offset(1);
		letters("chain").at(new Centered(pentagon2.get(3))).sketch();
		offset(1);
		letters("steering").at(new Centered(pentagon2.get(4).add(-38d, 0))).sketch();
		offset(2);
		assume(primaryNormal);
		for (int i = 0; i < 5; i++) {
			spot(pentagon.get(i).x, pentagon.get(i).y).called("P" + i).appear();
		}
		for (int i = 0; i < 5; i++) {
			connector().from("P" + i, false).to("P" + (i + 1) % 5, false).sketch();
		}
		offset(5);
		headerEnd("Visible Outside, Invisible Inside");
		
		assume(emphaticSmall);
		offset(4);
		letters("outside,\nwe see them").centered(800,350).called("outside").sketch();
		connector().from("outside", false).to("judgment", true).sketch();
		connector().from("outside", false).to("correlation", true).sketch();
		offset(9);
		letters("inside\n  we  \n*live*\n them  ").centered(450, 430).sketch();
		return endBuild();
	}

	public Gesture outro() {
		buildPhrase();
		wipe();
		final double premiseToText = 50d;
		final double testToPremise = 90d;

		header("Five Underplayed TDD Premises");
		double y = 120d;
		
		assume(primarySmall);
		letters("The Money Premise").at(new TopLeft(20, y)).sketch();
		y += premiseToText;
		assume(secondaryNormal);
		letters("We're in this for the money.").at(new TopLeft(60, y)).appear();
		
		y += testToPremise;
		assume(primarySmall);
		letters("The Judgment Premise").at(new TopLeft(20, y)).appear();
		y += premiseToText;
		assume(secondaryNormal);
		letters("We rely on human judgment.").at(new TopLeft(60, y)).appear();
		
		y += testToPremise;
		assume(primarySmall);
		letters("The Correlation Premise").at(new TopLeft(20, y)).appear();
		assume(secondaryNormal);
		y += premiseToText;
		letters("Internal quality & productivity correlate.").at(new TopLeft(60, y)).appear();
		
		
		y += testToPremise;
		assume(primarySmall);
		letters("The Chain Premise").at(new TopLeft(20, y)).appear();
		y += premiseToText;
		assume(secondaryNormal);
		letters("Test a chain by testing its links.").at(new TopLeft(60, y)).appear();

		y += testToPremise;
		assume(primarySmall);
		letters("The Steering Premise").at(new TopLeft(20, y)).appear();
		y += premiseToText;
		assume(secondaryNormal);
		letters("Testability steers development.").at(new TopLeft(60, y)).appear();

		return endBuild();
	}

	public void belowCentered(String text, String target, String name, String party) {
		letters(text).at(new BelowCenter(actor(target).groupSource())).called(name).in(party).sketch();
	}

	Vector<Point> polygon(int sides, double radius, Point at) {
		Vector<Point> result = new Vector<>();
		for (int i = 0; i < sides; i += 1) {
			double angle = ((double) i / (double) sides) * 2 * Math.PI;
			double pointX = (Math.sin(angle) * radius) + at.x;
			double pointY = (Math.cos(angle) * radius) + at.y;
			result.add(new Point(pointX, pointY));
		}
		return result;
	}

	public PremisesScript downcast() {
		return this;
	}

	private void lettersBelow(String text, String target, String name, String party) {
		letters(text).at(new BelowLeft(actor(target).groupSource())).called(name).in(party).sketch();
	}

	private void emphasize(double fromX, double width, double atY, String in) {
		spot(fromX, atY).called("us" + atY).in("part1").appear();
		spot(fromX + width, atY).called("ue" + atY).in("part1").appear();
		connector().from("us" + atY, false).to("ue" + atY, false).format(emphasisFormat).in("part1").sketch();
	}

}

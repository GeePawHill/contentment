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
	private Format codeFormat;

	public PremisesScript() {

		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		Paint primary = color(119, 187, 65);
		Paint secondary = color(177, 140, 254);
		Paint emphatic = color(255, 255, 0);

		primaryJumbo = format(primary, jumbo);
		primaryNormal = format(primary, normal);

		secondaryJumbo = format(secondary, jumbo);
		secondaryNormal = format(secondary, normal);

		emphaticJumbo = format(emphatic, jumbo);
		emphaticNormal = format(emphatic, normal);
		emphaticSmall = format(emphatic, small);

		emphasisFormat = new Format(Frames.frame(emphatic, 4d, .7d));

		Font codeFont = new Font("Consolas", 25d);
		Paint codeColor = Color.WHITE;
		codeFormat = new Format(TypeFace.font(codeFont, 2d, 1d), TypeFace.color(codeColor, 1d),
				Frames.frame(codeColor, 2d, 1d));

	}

	private Format format(Paint majorColor, double fontsize) {
		Font font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize);
		return new Format(TypeFace.font(font, 2d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
	}

	public Script make() {
		script = new Script(
				new MediaRhythm(new File("d:\\GeePawHillDotOrg\\videos\\premises.home\\positioned_2_1.mp4")));
		script.add(new Keyframe(0, opening()));
		script.add(new Keyframe(30, money()));
		script.add(new Keyframe(76, money2()));
		script.add(new Keyframe(132, money3()));
		script.add(new Keyframe(151, judgment()));
		script.add(new Keyframe(194, judgment2()));
		script.add(new Keyframe(234, judgment3()));
		script.add(new Keyframe(251, correlation()));
		script.add(new Keyframe(270, correlation2()));
		script.add(new Keyframe(318,correlation3()));
		script.add(new Keyframe(380, chaining()));
		script.add(new Keyframe(446, steering()));
		script.add(new Keyframe(528, underplayed()));
		script.add(new Keyframe(598, outro()));
		return script;
	}

	private Step opening() {
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

	public Step money() {
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

	public Step money2() {
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

	public Step money3() {
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

	public Step judgment() {
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

	public Step judgment2() {
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

	public Step judgment3() {
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

	public Step correlation() {
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

	public Step correlation2() {
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
	
	public Step correlation3() {
		buildPhrase();
		cue(319);
		party("part2").fadeOut();
		assume(secondaryJumbo);
		letters("We *can* trade EQ for productivity").at(new TopLeft(30, 200)).called("eq").in("part3").sketch();
		offset(4);
		assume(primaryNormal);
		lettersBelow("   less speed, completeness, or beauty","eq","lessEQ","part3");
		offset(8);
		lettersBelow("   means more time to work on other things","lessEQ","time","part3");
		
		offset(8);
		assume(secondaryJumbo);
		letters("We *can't* trade IQ for productivity").at(new TopLeft(30, 500)).called("iq").in("part3").sketch();
		offset(2);
		assume(primaryNormal);
		lettersBelow("    less effort towards changeability","iq","changing","part3");
		lettersBelow("    means more effort to change","changing","expense","part3");
		lettersBelow("    means less time to work on other things","expense","other","part3");
		return endBuild();
	}
	
	public Step chaining() {
		buildPhrase();
		wipe();
		header("The Chaining Premise");
		return endBuild();
	}

	public Step steering() {
		buildPhrase();
		wipe();
		header("The Steering Premise");
		return endBuild();
	}

	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").sketch();
	}

	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").groupSource())).sketch();
	}

	public Step underplayed() {
		buildPhrase();
		wipe();
		Vector<Point> pentagon = polygon(5, 225, new Point(450, 450));
		for (int i = 0; i < 5; i++) {
			spot(pentagon.get(i).x, pentagon.get(i).y).called("P" + i).appear();
		}
		for (int i = 0; i < 5; i++) {
			connector().from("P" + i, false).to("P" + (i + 1) % 5, false).format(secondaryJumbo).appear();
		}

		Vector<Point> pentagon2 = polygon(5, 275, new Point(450, 450));
		assume(secondaryNormal);
		letters("money").at(new Centered(pentagon2.get(0))).appear();
		letters("judgment").at(new Centered(pentagon2.get(1).add(38d, 0))).appear();
		letters("correlation").at(new Centered(pentagon2.get(2))).appear();
		letters("chain").at(new Centered(pentagon2.get(3))).appear();
		letters("steering").at(new Centered(pentagon2.get(4).add(-38d, 0))).appear();
		return endBuild();
	}

	public Step outro() {
		buildPhrase();
		wipe();
		final double premiseToText = 50d;
		final double testToPremise = 90d;

		letters("Five Underplayed TDD Premises").at(new TopLeft(new Point(20, 15))).format(secondaryJumbo).appear();
		double y = 120d;
		letters("The Money Premise").at(new TopLeft(20, y)).format(secondaryNormal).appear();
		y += premiseToText;
		letters("We're in this for the money.").at(new TopLeft(60, y)).format(primaryJumbo).appear();
		y += testToPremise;
		letters("The Judgment Premise").at(new TopLeft(20, y)).format(secondaryNormal).appear();
		y += premiseToText;
		letters("We rely entirely on human judgment.").at(new TopLeft(60, y)).format(primaryJumbo).appear();

		y += testToPremise;
		letters("The Correlation Premise").at(new TopLeft(20, y)).format(secondaryNormal).appear();
		y += premiseToText;
		letters("Internal quality & productivity correlate directly.").at(new TopLeft(60, y)).format(primaryJumbo)
				.appear();

		y += testToPremise;
		letters("The Chain Premise").at(new TopLeft(20, y)).format(secondaryNormal).appear();
		y += premiseToText;
		letters("Test a chain by testing its links.").at(new TopLeft(60, y)).format(primaryJumbo).appear();

		y += testToPremise;
		letters("The Steering Premise").at(new TopLeft(20, y)).format(secondaryNormal).appear();
		y += premiseToText;
		letters("Tests & testability steer design & development.").at(new TopLeft(60, y)).format(primaryJumbo).appear();

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

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
import org.geepawhill.contentment.step.Chord;
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

public class OptimizingScript extends ScriptBuilder<OptimizingScript> {
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
	private Paint secondary;
	private Paint primary;
	private Paint emphatic;

	public OptimizingScript() {

		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		primary = color(119, 187, 65);
		secondary = color(177, 140, 254);
		emphatic = color(255, 255, 0);

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
//				new MediaRhythm(new File("d:\\GeePawHillDotOrg\\videos\\optimizing.home\\finalFaceoverSequence.mp4")));
				new SimpleRhythm());
		script.add(new Keyframe(0, leadIn()));
		script.add(new Keyframe(4, opening()));
		script.add(new Keyframe(88, explanation()));
		script.add(new Keyframe(149, programming()));
		script.add(new Keyframe(202, scanning1()));
		script.add(new Keyframe(240, scanning2()));
		script.add(new Keyframe(303, scanning3()));
		script.add(new Keyframe(328, optimizing1()));
		script.add(new Keyframe(360, optimizing2()));
		script.add(new Keyframe(462, outro()));
		return script;
	}
	
	private Step leadIn() {
		buildPhrase();
		wipe();
		cue(0);
		letters("GeePaw's Notebook:").format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").appear();
		assume(secondaryJumbo);
		letters("Optimizing a Program\nAnd Optimizing Programming").centered(450, 450).appear();
		assume(emphaticSmall);
		letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(new TopLeft(20,825)).appear();
		offset(3);

		return endBuild();
	}

	private Step opening() {
		buildPhrase();
		wipe();
		cue(4);
		header("Optimizing A Program");
		FixedLetters code = new FixedLetters(world, 26, 13);
		code.at(new TopLeft(150, 230)).in("part1").called("code").appear();
		code.assume(Color.WHITE);
		offset(4);
		code.say(0, 0, "program()");
		code.say(1, 0, "{");
		code.say(2, 3, "for( 1..50 ) ");
		code.say(3, 3, "{");
		code.say(4, 6, "read()");
		code.say(5, 6, "for( 1..100 ) ");
		code.say(6, 6, "{" );
		code.say(7, 9, "scan()");
		code.say(8, 6, "}");
		code.say(9, 3, "}");
		code.say(10, 3, "write()");
		code.say(11, 0, "}");
		offset(9);
		stroke(200,355,200,620).format(emphaticNormal).sketch();
		offset(7);
		stroke(270,475,270,580).format(emphaticNormal).sketch();

		offset(15);
		assume(emphaticSmall);
		letters("100 ms").at(new TopLeft(580,370)).sketch();
		offset(2);
		letters("100 ms").at(new TopLeft(580,500)).sketch();
		offset(2);
		letters("100 ms").at(new TopLeft(580,620)).sketch();

		offset(8);
		assume(secondaryNormal);
		letters("runtime: 5,005,100 ms").centered(450,750).sketch();
		
		offset(19);
		code.assume(primary);
		code.say(4, 6, "read()");
		offset(2);
		code.say(7,9,"scan()");
		offset(2);
		code.say(10, 3, "write()");
		
		offset(7);
		assume(emphaticJumbo);
		letters("Answer: Start With scan()").centered(450,850).sketch();

		return endBuild();
	}

	

	public Step explanation() {
		buildPhrase();
		cue(90);
		wipe();
		header("Why Scan Is Obvious");
		
		double y0 = 180d;
		double y1 = y0+80;
		double y2 = y1+80;
		double y3 = y2+80;
		
		double y4 = y3+120;
		double y5 = y4+80;
		double y6 = y5+80;
		
		double cx0 = 100;
		double cx1 = 350;
		double cx2 = 580;
		double cx3 = 810;
		assume(primaryNormal);
		letters("scan").centered(new Point(cx1,y0)).in("table").appear();
		letters("read").centered(new Point(cx2,y0)).in("table").appear();
		letters("write").centered(new Point(cx3,y0)).in("table").appear();

		letters("calls").centered(new Point(cx0,y1)).in("table").appear();
		letters("per call").centered(new Point(cx0,y2)).in("table").appear();
		letters("consumes").centered(new Point(cx0,y3)).in("table").appear();

		offset(4);
		assume(emphaticNormal);
		letters("5000").centered(new Point(cx1,y1)).in("table").sketch();
		letters("1").centered(new Point(cx3,y1)).in("table").sketch();
		assume(secondaryNormal);
		letters("50").centered(new Point(cx2,y1)).in("table").sketch();

		offset(4);
		letters("100").centered(new Point(cx1,y2)).in("table").appear();
		letters("100").centered(new Point(cx2,y2)).in("table").appear();
		letters("100").centered(new Point(cx3,y2)).in("table").appear();

		assume(secondaryNormal);
		letters("5,000,000").centered(new Point(cx1,y3)).in("table").appear();
		letters("5,000").centered(new Point(cx2,y3)).in("table").appear();
		letters("100").centered(new Point(cx3,y3)).in("table").appear();

		offset(6);
		assume(primaryNormal);
		letters("save").centered(new Point(cx0,y4)).in("table").appear();
		letters("calls").centered(new Point(cx0,y5)).in("table").appear();
		letters("savings").centered(new Point(cx0,y6)).in("table").appear();
		
		assume(secondaryNormal);
		letters("10").centered(new Point(cx1,y4)).in("table").appear();
		letters("10").centered(new Point(cx2,y4)).in("table").appear();
		letters("10").centered(new Point(cx3,y4)).in("table").appear();

		assume(primaryNormal);
		assume(secondaryNormal);
		letters("5000").centered(new Point(cx1,y5)).in("table").appear();
		letters("50").centered(new Point(cx2,y5)).in("table").appear();
		letters("1").centered(new Point(cx3,y5)).in("table").appear();

		assume(emphaticNormal);
		offset(10);
		letters("10 ms").centered(new Point(cx3,y6)).in("table").sketch();
		
		offset(9);
		letters("50,000 ms").centered(new Point(cx1,y6)).in("table").sketch();

		offset(14);
		assume(secondaryNormal);
		letters("500 ms").centered(new Point(cx2,y6)).in("table").sketch();
		assume(secondaryNormal);
		return endBuild();
	}
	
	public Step programming() {
		buildPhrase();
		cue(149);
		wipe();
		header("A Slight Tweak: ");
		offset(2);
		FixedLetters code = new FixedLetters(world, 26, 13);
		code.at(new TopLeft(150, 230)).in("part1").called("code").appear().fadeDown();
		code.assume(Color.WHITE);
		code.say(0, 0, "program()");
		code.say(1, 0, "{");
		code.say(2, 3, "for( 1..50 ) ");
		code.say(3, 3, "{");
		code.say(4, 6, "read()");
		code.say(5, 6, "for( 1..100 ) ");
		code.say(6, 6, "{" );
		code.say(7, 9, "scan()");
		code.say(8, 6, "}");
		code.say(9, 3, "}");
		code.say(10, 3, "write()");
		code.say(11, 0, "}");
		code.fadeUp();
		
		FixedLetters programming = new FixedLetters(world, 26, 13);
		programming.at(new TopLeft(150, 230)).in("programming").appear().fadeDown();
		programming.assume(Color.WHITE);
		programming.say(0, 0, "programming...");
		programming.say(1, 0, "{");
		programming.say(2, 3, "for( 1..50 ) ");
		programming.say(3, 3, "{");
		programming.say(4, 6, "read some code...");
		programming.say(5, 6, "for( 1..100 ) ");
		programming.say(6, 6, "{" );
		programming.say(7, 9, "scan some code...");
		programming.say(8, 6, "}");
		programming.say(9, 3, "}");
		programming.say(10, 3, "write some code...");
		programming.say(11, 0, "}");
		
		offset(8);
		buildChord();
		headerEnd("Programming");
		programming.fadeUp();
		code.fadeOut();
		endChord();
		
		offset(7);
		programming.fadeOut();
		
		assume(secondaryNormal);
		letters("In programming...").at(new TopLeft(60,220)).sketch();
		assume(primaryJumbo);
		letters("we scan ").at(new TopLeft(160,300)).called("scan").sketch();
		letters("tons of ").at(new RightOf(actor("scan").groupSource())).called("tons").sketch();
		letters("code").at(new RightOf(actor("tons").groupSource())).sketch();
		letters("we read ").at(new TopLeft(160,400)).called("read").sketch();
		letters("some ").at(new RightOf(actor("read").groupSource())).called("some").sketch();
		letters("code").at(new RightOf(actor("some").groupSource())).sketch();
		letters("we write ").at(new TopLeft(160,500)).called("write").sketch();
		letters("a little ").at(new RightOf(actor("write").groupSource())).called("little").sketch();
		letters("code").at(new RightOf(actor("little").groupSource())).sketch();
		
		offset(8);
		actor("tons").reColor(emphatic);
		offset(1);
		actor("some").reColor(emphatic);
		offset(1);
		actor("little").reColor(emphatic);
		offset(14);
		assume(emphaticJumbo);
		letters("optimize programming by").centered(450,720).sketch();
		letters("optimizing scanning").centered(450,800).sketch();
		return endBuild();                                             
	}

	public Step scanning1() {
		buildPhrase();
		cue(202);
		wipe();
		header("Scanning:  ");
		offset(4);
		headerEnd("Seeing Fast");
		final double premiseToText = 55d;
		final double testToPremise = 90d;
		double y = 180d;
		
		offset(14);
		assume(secondaryJumbo);
		letters("Linear Scanning").at(new TopLeft(20, y)).sketch();
		offset(4);
		y += testToPremise;
		assume(primaryNormal);
		letters("rapid scrolling through one package").at(new TopLeft(60,y)).sketch();
		offset(1);
		y+= premiseToText;
		letters("grabbing a structural overview").at(new TopLeft(60,y)).sketch();
		y+=premiseToText;
		offset(2);
		assume(emphaticNormal);
		letters("filter: a bone-shaped hole").at(new TopLeft(60,y)).sketch();
		
		y+=testToPremise;
		assume(secondaryJumbo);
		offset(3);
		letters("Bounce Scanning").at(new TopLeft(20,y)).sketch();
		y+=testToPremise;
		offset(1);
		assume(primaryNormal);
		letters("jumping package to package").at(new TopLeft(60,y)).sketch();
		offset(1);
		y+=premiseToText;
		letters("grabbing an answer to a tiny question").at(new TopLeft(60,y)).sketch();
		y+=premiseToText;
		offset(3);
		assume(emphaticNormal);
		letters("filter: an answer-shaped hole").at(new TopLeft(60,y)).sketch();
		return endBuild();
	}
	
	public Step scanning2() {
		buildPhrase();
		cue(240);
		wipe();
		header("Scanning:  ");
		offset(2);
		headerEnd("About Waldo Puzzles");
		final double premiseToText = 55d;
		final double testToPremise = 90d;
		double y = 180d;
		
		offset(2);
		assume(secondaryJumbo);
		letters("Where's Waldo?").at(new TopLeft(20, y)).in("part1").sketch();
		offset(1);
		y += testToPremise;
		assume(primaryNormal);
		letters("(or wally, or van-lang, or charlie)").at(new TopLeft(60,y)).in("part1").sketch();
		offset(9);
		y+= premiseToText;
		letters("find one shape in a forest of shapes").at(new TopLeft(60,y)).in("part1").sketch();
		y+=premiseToText;
		offset(13);
		assume(emphaticNormal);
		letters("solving waldo puzzles is scanning").at(new TopLeft(60,y)).in("part1").sketch();
		
		y+=testToPremise;
		assume(secondaryJumbo);
		offset(6);
		letters("Bounce Scanning").at(new TopLeft(20,y)).in("part1").sketch();
		y+=testToPremise;
		offset(1);
		assume(primaryNormal);
		letters("point to point with a waldo filter").at(new TopLeft(60,y)).in("part1").sketch();
		offset(4);
		y+=premiseToText;
		letters("waiting for the filter to trigger").at(new TopLeft(60,y)).in("part1").sketch();
		y+=premiseToText;
		offset(7);
		assume(emphaticNormal);
		letters("then confirm, or deny and move on").at(new TopLeft(60,y)).in("part1").sketch();

		y+=testToPremise;
		assume(secondaryJumbo);
		offset(10);
		letters("(GeePaw Is Irresponsible)").at(new TopLeft(20,y)).in("part1").sketch();

		return endBuild();                                             
	}

	public Step scanning3() {
		buildPhrase();
		cue(303);
		party("part1").fadeOut();
		assume(primaryNormal);
		offset(5);
		letters("Waldo Puzzles Make Scanning Hard").centered(450,250).sketch();
		assume(emphaticJumbo);
		offset(12);
		letters("To Optimize For Scanning").centered(450,450).called("optimize").sketch();
		belowCentered("We Must", "optimize", "must", "part2");
		belowCentered("Un-Waldo Our Code","must","unwaldo","part2");
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

	

	
	public Step optimizing1() {
		buildPhrase();
		wipe();
		cue(328);
		header("Optimizing For Scanning:  ");
		offset(3);
		headerEnd("Sizing, Grouping, Naming");
		Vector<Point> triangle = polygon(3,200,new Point(450,330));
		assume(secondaryNormal);
		offset(2);
		letters("sizing").withOval().centered(triangle.get(0)).called("sizing").in("part1").sketch();
		
		offset(2);
		letters("grouping").withOval().centered(triangle.get(1)).called("grouping").in("part1").sketch();
		offset(2);
		letters("naming").withOval().centered(triangle.get(2)).called("naming").in("part1").sketch();
		offset(2);
		assume(primaryNormal);
		connector().from(actor("sizing").groupSource(), true).to(actor("grouping").groupSource(), true).in("part1").sketch();
		connector().from(actor("grouping").groupSource(), true).to(actor("naming").groupSource(), true).in("part1").sketch();
		connector().from(actor("naming").groupSource(), true).to(actor("sizing").groupSource(), true).in("part1").sketch();
	
		offset(8);
		assume(emphaticJumbo);
		final double textY = 630d;
		letters("Complicated!").at(new Centered(450, textY)).in("part1").sketch();
		assume(primaryNormal);
		letters("it's *design*").at(new Centered(450,textY+80)).in("part1").sketch();
		letters("no one is enough").at(new Centered(450,textY+130)).in("part1").sketch();
		letters("we need all three").at(new Centered(450,textY+180)).in("part1").sketch();
		

		return endBuild();
	}


	public Step optimizing2() {
		buildPhrase();
		party("part1").fadeOut();
		cue(360);
		
		double y = 130d;
		final double premiseToText = 55d;
		final double testToPremise = 75d;
		assume(secondaryNormal);
		letters("Sizing").at(new TopLeft(20, y)).sketch();
		offset(10);
		y += premiseToText;
		assume(primaryNormal);
		letters("1000+ places to scan: hard").at(new TopLeft(60,y)).sketch();
		offset(10);
		y+= premiseToText;
		letters("make smaller pages").at(new TopLeft(60,y)).sketch();
		y+= premiseToText;
		offset(13);
		assume(emphaticNormal);
		letters("but the pages are still random").at(new TopLeft(60,y)).sketch();
	
		y+=testToPremise;
		assume(secondaryNormal);
		offset(20);
		letters("Grouping").at(new TopLeft(20,y)).sketch();
		y+=premiseToText;
		offset(7);
		assume(primaryNormal);
		letters("organizing for easy filtering").at(new TopLeft(60,y)).sketch();
		offset(3);
		y+=premiseToText;
		letters("putting related things together").at(new TopLeft(60,y)).sketch();
		assume(emphaticNormal);
		y+= premiseToText;
		offset(8);
		letters("okay, but which page has which things?").at(new TopLeft(60,y)).sketch();
	
		y+=testToPremise;
		assume(secondaryNormal);
		offset(8);
		letters("Naming").at(new TopLeft(20,y)).sketch();
		y+=premiseToText;
		offset(2);
		assume(primaryNormal);
		letters("just go to the \"things like waldo\" page").at(new TopLeft(60,y)).sketch();
		offset(2);
		y+=premiseToText;
		letters("the trick is discovering likely scan targets").at(new TopLeft(60,y)).sketch();
		y+= premiseToText;
		assume(emphaticNormal);
		letters("names are critically important").at(new TopLeft(60,y)).sketch();
		return endBuild();
	}
	
	public Step outro() {
		buildPhrase();
		cue(462);
		wipe();

		header("Easier To Scan & Good Design: ");
		offset(4);
		headerEnd("The Same Problem");
		final double premiseToText = 50d;
		final double testToPremise = 90d;
		double y = 180d;
		
		offset(14);
		assume(secondaryJumbo);
		letters("Notice Scanning").at(new TopLeft(20, y)).sketch();
		offset(4);
		y += testToPremise;
		assume(primaryNormal);
		letters("notice how often you do it").at(new TopLeft(60,y)).sketch();
		offset(8);
		y+= premiseToText;
		letters("notice how & when it works").at(new TopLeft(60,y)).sketch();
		y+=premiseToText;
		assume(emphaticNormal);
		offset(6);
		letters("and notice how & when it doesn't work").at(new TopLeft(60,y)).sketch();
		
		y+=testToPremise;
		assume(secondaryJumbo);
		offset(16);
		letters("Change Code").at(new TopLeft(20,y)).sketch();
		y+=testToPremise;
		offset(9);
		assume(emphaticNormal);
		letters("reorganize around scannability").at(new TopLeft(60,y)).sketch();
		assume(primaryNormal);
		offset(12);
		y+=premiseToText;
		letters("make broken scanning work").at(new TopLeft(60,y)).sketch();
		y+=premiseToText;
		offset(6);
		letters("this is called 'refactoring'").at(new TopLeft(60,y)).sketch();
		return endBuild();
	}
	
	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").sketch();
	}
	
	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").groupSource())).sketch();
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

	public OptimizingScript downcast() {
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

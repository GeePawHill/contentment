package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;
import java.util.Vector;

import org.geepawhill.contentment.actor.Appearance;
import org.geepawhill.contentment.actors.*;
import org.geepawhill.contentment.flow.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.grid.*;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.Frames;

import javafx.scene.paint.Paint;

public class LumpOfCodingScript extends ScriptBuilder<LumpOfCodingScript> {
	private static final double XMARGIN = 20;
	private static final double YMARGIN = 20;
	
	private FormatTable formats;

	private Format secondaryJumbo;
	private Format primaryJumbo;
	private Format secondaryNormal;
	private Format primaryNormal;
	private Format emphaticSmall;
	private Format emphaticJumbo;
	private Paint emphatic;
	private Format tertiaryNormal;

	public LumpOfCodingScript() {
		super(new MediaRhythm(new File("d:\\GeePawHillDotOrg\\videos\\newbie\\faceoverLumpOfCode.mp4")));
//				new SimpleRhythm());
		
		formats = new FormatTable();

		color(119, 187, 65);
		color(177, 140, 254);
		emphatic = color(255, 255, 0);

		primaryJumbo = formats.get(Size.Jumbo, Color.Primary);
		primaryNormal = formats.get(Size.Normal, Color.Primary);

		secondaryJumbo = formats.get(Size.Jumbo, Color.Secondary);
		secondaryNormal = formats.get(Size.Normal, Color.Secondary);
		
		formats.get(Size.Jumbo, Color.Tertiary);
		tertiaryNormal = formats.get(Size.Normal, Color.Tertiary);

		emphaticJumbo = formats.get(Size.Jumbo, Color.Emphatic);
		formats.get(Size.Normal, Color.Emphatic);
		emphaticSmall = new Format(formats.get(Size.Small,Color.Emphatic),Frames.frame(emphatic, 3d, .7d));

	}

	public Script make() {
		leadIn();
		noob();
		end();
		return script;
	}
	
	private void leadIn() {
		scene(0);
		wipe();
		letters("GeePaw's Notebook:").format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").appear();
		assume(secondaryJumbo);
		letters("The Lump Of Coding Fallacy\n(A Letter For Noobs)").centered(450, 450).appear();
		assume(emphaticSmall);
		letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(new TopLeft(20,825)).appear();
	}

	private void noob() {
		scene(5);
		wipe();
		header("X Amount Of Value");
		Grid overAll = new Grid();
		Grid columns = overAll.nested(5, 15, 95, 90);
		
		
		Grid before = columns.nested(33, 0, 60, 100);
		Horizontal betweenOne = before.horizontal(50);
		Horizontal betweenTwo = before.horizontal(80);
		
		Point beforeHeader = before.all().north();
		beforeHeader = new Point(beforeHeader.x,beforeHeader.y-50);
		
		letters("Before").format(emphaticJumbo).centered(beforeHeader).appear();
		
		assume(primaryNormal);
		Marks gakBefore = box( before.area(before.left(),before.top(), before.right(), betweenOne).grow(-10)).actor;
		gakBefore.appear();
		
		assume(secondaryNormal);
		Marks study = box( before.area(before.left(),betweenOne, before.right(), betweenTwo).grow(-10)).actor;
		study.appear();
		
		assume(tertiaryNormal);
		Marks programming = box( before.area(before.left(),betweenTwo, before.right(),before.bottom()).grow(-10)).actor;
		programming.appear();
		
		Grid textGrid = columns.nested(0,0,33,100);
		Flow gakText = new Flow(world, textGrid.area(textGrid.left(),textGrid.top(), textGrid.right(), betweenOne));
		gakText.load("pnGAK\n"
				+ "pn     Study\n"
				+ "ps     Testing\n"
				+ "ps     Debug\n"
				+ "ps\n"
				+ "snStudy\n"
				+ "ss     Scanning\n"
				+ "ss      Reading\n"
				+ "ss\n"
				+ "tnProgramming\n"
				+ "ts     Coding\n"
				+ "ts     Design\n");
		for(int i=0; i<12;i++) gakText.letters(i).appear();

		Grid after = columns.nested(73, 0, 100, 100);
		Horizontal difference = after.horizontal(38);
		Horizontal afterOne = after.horizontal(45);
		Horizontal afterTwo = after.horizontal(60);

		Point afterHeader = after.all().north();
		afterHeader = new Point(afterHeader.x,afterHeader.y-50);
		letters("After").format(emphaticJumbo).centered(afterHeader).appear();

		assume(primaryNormal);
		Marks gakAfter = box( after.area(after.left(),difference, after.right(), afterOne).grow(-10)).actor;
		gakAfter.appear();
		Appearance<Connector> gakLine = connector();
		gakLine.actor.from(gakBefore.entrance(), false).to(new Point(1130,410), true);
		gakLine.format(emphaticSmall).sketch();
		
		assume(secondaryNormal);
		Marks afterStudy = box( after.area(after.left(),afterOne, after.right(), afterTwo).grow(-10)).actor;
		afterStudy.appear();
		Appearance<Connector> studyLine = connector();
		studyLine.actor.from(study.entrance(), false).to(afterStudy.entrance(), true);
		studyLine.format(emphaticSmall).sketch();
		
		assume(tertiaryNormal);
		Marks afterProgramming = box( after.area(after.left(),afterTwo, after.right(),after.bottom()).grow(-10)).actor;
		afterProgramming.appear();
		assume(emphaticSmall);
		Appearance<Connector> programmingLine = connector();
		programmingLine.actor.from(programming.entrance(), false).to(afterProgramming.entrance(), true);
		programmingLine.sketch();
	}
	
	/*
	 * before	after
	 * 50	-> 0		5  -> 40
	 * 30	-> 50		15 -> 45
	 * 20	-> 80		40 -> 60
	 */

	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").sketch();
	}
	
	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").entrance())).sketch();
	}
	
	public void belowCentered(String text, String target, String name, String party) {
		letters(text).at(new BelowCenter(actor(target).entrance())).called(name).in(party).sketch();
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

	public LumpOfCodingScript downcast() {
		return this;
	}
}

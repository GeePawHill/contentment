package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;
import java.util.*;

import org.geepawhill.contentment.actor.Appearance;
import org.geepawhill.contentment.actors.*;
import org.geepawhill.contentment.flow.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.grid.*;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.Frames;

import com.sun.media.jfxmediaimpl.MarkerStateListener;

import javafx.scene.paint.Paint;

public class LumpOfCodingScript extends ScriptBuilder<LumpOfCodingScript> {
	
	private static final int INSET = 3;
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
	
	private final Grid master;
	private final Flow outline;
	private Grid viewport;

	public LumpOfCodingScript() {
		super(new MediaRhythm(new File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
//				new SimpleRhythm());
		
		master = new Grid();
		viewport = master.nested(INSET, 15, 100-INSET, 100-INSET);
		outline = new Flow(world, viewport.all() );
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
//		noob();
		proportions();
		outro();
		end();
		return script;
	}
	
	private void outro()
	{
		scene(473);
		fadeOut();
		header("GeePaw's Advice");
		outline.load("sjNotice Your Proportions\n"
				+ "pn   programming computer\n"
				+ "pn   studying source\n"
				+ "pn   gakking around\n"
				+ "sjActual Effort\n"
				+ "pn   do some lessons\n"
				+ "pn   start with a toy\n"
				+ "pn   move to your own leaf classes\n"
				+ "pn   grow in to hard testing problems\n");
		sync(6);
		outline.letters(0).sketch();
		sync(5);
		outline.letters(1).sketch();
		sync(2);
		outline.letters(2).sketch();
		sync(2);
		outline.letters(3).sketch();
		sync(8);
		outline.letters(4).sketch();
		sync(2);
		outline.letters(5).sketch();
		sync(11);
		outline.letters(6).sketch();
		sync(4);
		outline.letters(7).sketch();
		sync(16);
		outline.letters(8).sketch();
		sync(14);
		fadeOut();
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

	private void proportions() {
		scene(284);
		wipe();
		header("X Amount Of Value");
		
		Grid intermittent = viewport.nested(25, 0, 33, 85);
		Grid before = viewport.nested(36, 0, 60, 85);
		Grid after = viewport.nested(70, 0, 94, 85);
		
		Format programmingFormat = tertiaryNormal;
		Format studyingFormat = primaryNormal;
		Format gakkingFormat = secondaryNormal;
		
		
		Horizontal betweenOne = before.horizontal(50);
		Horizontal betweenTwo = before.horizontal(80);
		
		Flow gakText = new Flow(world, viewport.area(0, 0, 100, 100));
		gakText.load("snGAK Activity\n"
				+ "sn     Study\n"
				+ "ss     Testing\n"
				+ "ss     Debug\n"
//				+ "ss\n"
				+ "pnStudying Code\n"
				+ "ps     Scanning\n"
				+ "ps      Reading\n"
//				+ "ps\n"
				+ "tnChanging Code\n"
				+ "ts     Entering\n"
				+ "ts     Designing\n");
		buildChord();
		for(int i=0; i<10;i++) gakText.letters(i).fadeIn();
		endChord();
		sync(18);
		Random selector = new Random();
		for(int i=3; i<99;i+=3)
		{
			PointPair points = intermittent.area(0, i, 100, i);
			double probability = selector.nextDouble();
			if(probability<.2) assume(programmingFormat);
			else if(probability<.5) assume(studyingFormat);
			else assume(gakkingFormat);
			stroke(points).appear();
		}

		sync(11);
		assume(programmingFormat);
		Appearance<Marks> programmingBefore = box( before.area(0,80, 100,100).grow(-10));
		programmingBefore.sketch(2000);
		
		sync(10);
		assume(gakkingFormat);
		Appearance<Marks> gakBefore = box( before.area(before.left(),before.top(), before.right(), betweenOne).grow(-10));
		gakBefore.sketch(2000);
		
		sync(12);
		assume(studyingFormat);
		Appearance<Marks> studyBefore = box( before.area(before.left(),betweenOne, before.right(), betweenTwo).grow(-10));
		studyBefore.sketch(1500);
		
		scene(356);

		Horizontal difference = after.horizontal(38);
		Horizontal afterOne = after.horizontal(45);
		Horizontal afterTwo = after.horizontal(60);

		Point beforeHeader = before.all().north();
		beforeHeader = new Point(beforeHeader.x,beforeHeader.y-50);
		letters("Before").format(emphaticJumbo).at(new BelowCenter(programmingBefore.groupSource(),0)).appear();

		sync(8);
		assume(programmingFormat);
		PointPair programmingAfterBounds = after.area(after.left(),afterTwo, after.right(),after.bottom()).grow(-10);
		Appearance<Marks> programmingAfter = box( programmingAfterBounds);
		programmingAfter.sketch(2000);
		assume(emphaticSmall);
		
		Point afterHeader = after.all().north();
		afterHeader = new Point(afterHeader.x,afterHeader.y-50);
		letters("After").format(emphaticJumbo).at(new BelowCenter(programmingAfter.groupSource(),0)).appear();
		
		sync(12);
		Appearance<Connector> programmingLine = connector();
		programmingLine.actor.from(programmingBefore.groupSource(), false).to(programmingAfterBounds.west(), true);
		programmingLine.sketch();
		letters("Doubled").at(new AboveCenter(programmingLine.groupSource(),20d)).appear();
		
		sync(16);
		assume(studyingFormat);
		PointPair studyingAfterBounds = after.area(after.left(),afterOne, after.right(), afterTwo).grow(-10);
		Appearance<Marks> afterStudy = box( studyingAfterBounds);
		afterStudy.sketch(2000);
		
		sync(19);
		assume(emphaticSmall);
		Appearance<Connector> studyLine = connector();
		studyLine.actor.from(studyBefore.groupSource(), false).to(studyingAfterBounds.west(), true);
		studyLine.format(emphaticSmall).sketch();
		letters("Halved").at(new AboveCenter(studyLine.groupSource(),0d)).appear();

		sync(15);
		assume(gakkingFormat);
		PointPair gakAfterBounds = after.area(after.left(),difference, after.right(), afterOne).grow(-10);
		Appearance<Marks> gakAfter = box( gakAfterBounds );
		gakAfter.sketch(2000);
		assume(emphaticSmall);
		sync(8);
		Appearance<Connector> gakLine = connector();
		gakLine.actor.from(gakBefore.groupSource(), false).to(gakAfterBounds.west(), true);
		gakLine.format(emphaticSmall).sketch();
		letters("Slashed!").at(new AboveCenter(gakLine.groupSource(),0d)).appear();
	}
	
	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(master.point(INSET,INSET))).called("header").sketch();
	}
	
	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").entrance())).sketch();
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

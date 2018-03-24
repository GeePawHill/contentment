package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;
import java.util.Vector;

import org.geepawhill.contentment.flow.Flow;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.*;

import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class LumpOfCodingScript extends ScriptBuilder<LumpOfCodingScript> {
	private static final double XMARGIN = 20;
	private static final double YMARGIN = 20;

	private Format secondaryJumbo;
	private Format primaryJumbo;
	private Format secondaryNormal;
	private Format primaryNormal;
	private Format emphaticNormal;
	private Format emphaticSmall;
	private Format emphaticJumbo;
	private Paint secondary;
	private Paint primary;
	private Paint emphatic;

	public LumpOfCodingScript() {
		super(new MediaRhythm(new File("d:\\GeePawHillDotOrg\\videos\\newbie\\faceoverLumpOfCode.mp4")));
//				new SimpleRhythm());

		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		primary = color(119, 187, 65);
		secondary = color(177, 140, 254);
		emphatic = color(255, 255, 0);

		primaryJumbo = format(primary, jumbo);
		primaryNormal = format(primary, normal);

		secondaryJumbo = format(secondary, jumbo);
		secondaryNormal = format(secondary, normal);

		emphaticJumbo = format(emphatic, jumbo);
		emphaticNormal = format(emphatic, normal);
		emphaticSmall = new Format(format(emphatic, small),Frames.frame(emphatic, 3d, .7d));

	}

	private Format format(Paint majorColor, double fontsize) {
		Font font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize);
		return new Format(TypeFace.font(font, 2d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
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
		header("A Working Stiff");
		Flow flow = new Flow(world, new PointPair(20,150,500,500));
		flow.load("pnJust A Working Geek\n"
				+"sn    less than 5 years old\n"
				+"sn    over half of all geeks!\n"
				+"sn    working for a big company\n"
				+"sn    the usual: database to web and back again"
				);
		for(int line = 0; line<flow.lines().size(); line++) {
			sync(2);
			flow.letters(line).appear();
		}

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

	public LumpOfCodingScript downcast() {
		return this;
	}
}

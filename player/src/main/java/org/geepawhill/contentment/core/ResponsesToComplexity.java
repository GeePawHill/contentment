package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.ClipArt;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ResponsesToComplexity
{

	private CommonSteps common;

	private OvalText[] ovals;

	private Format lineFormat;

	public ResponsesToComplexity(Sequence sequence)
	{
		this.common = new CommonSteps(sequence);
	}

	public void add()
	{
		Format labelFormat = new Format(TypeFace.mediumHand(), TypeFace.color(Color.LIGHTBLUE, 1d));
		Format smallLabelFormat = new Format(TypeFace.smallHand(), TypeFace.color(Color.GREEN, 1d));
		lineFormat = new Format(Frames.frame(Color.AQUAMARINE, 2d, 1d, Dash.dash(3d)));
		Format ovalFormat = new Format(TypeFace.largeHand(), TypeFace.color(Color.RED, 1d), 
				Frames.frame(Color.RED, 3d, 1d, Dash.solid()));
		common.clear();
		Image image = new Image("/org/geepawhill/scripts/usOutline.png", 1500d, 800d, true, true);
		ClipArt art = new ClipArt(image, new PointPair(150d, 50d, 1550d, 850d));
		common.appear(art);
		Image redBall = new Image("/org/geepawhill/scripts/redBall.png", 50d, 50d, true, true);
		
		ClipArt sf = new ClipArt(redBall, new PointPair(200d, 400d, 0d, 0d));
		Letters sfLetters = new Letters("San Francisco", new Point(245d, 390d), labelFormat);
		common.appear(sfLetters);
		
		common.appear(sf);
		ClipArt ny = new ClipArt(redBall, new PointPair(1280d, 200d, 0d, 0d));
		Letters nyLetters = new Letters("New York", new Point(1300d, 200d), labelFormat);
		common.appear(nyLetters);
		common.appear(ny);
		common.cue();

		Image blueBall = new Image("/org/geepawhill/scripts/blueBall.png", 25d, 25d, true, true);
		ClipArt reno = new ClipArt(blueBall, new PointPair(320d, 360d, 0d, 0d));
		common.appear(reno);
		Letters renoLetters = new Letters("Reno", new Point(320d, 360d), smallLabelFormat);
		common.appear(renoLetters);

		ClipArt lasVegas = new ClipArt(blueBall, new PointPair(340d, 420d, 0d, 0d));
		common.appear(lasVegas);
		Letters lasVegasLetters = new Letters("Las Vegas", new Point(340, 420d), smallLabelFormat);
		common.appear(lasVegasLetters);

		ClipArt saltLake = new ClipArt(blueBall, new PointPair(440d, 400d, 0d, 0d));
		common.appear(saltLake);
		Letters saltLakeLetters = new Letters("Salt Lake", new Point(440, 400d), smallLabelFormat);
		common.appear(saltLakeLetters);

		ClipArt grandCanyon = new ClipArt(blueBall, new PointPair(410d, 480d, 0d, 0d));
		common.appear(grandCanyon);
		Letters grandCanyonLetters = new Letters("Grand Canyon", new Point(410d, 480d), smallLabelFormat);
		common.appear(grandCanyonLetters);
		common.cue();

		Arrow sfReno = new Arrow(sf, false, reno, true, lineFormat);
		common.sketch(1d, sfReno);

		Arrow sfLasVegas = new Arrow(sf, false, lasVegas, true, lineFormat);
		common.appear(sfLasVegas);

		Arrow renoSaltLake = new Arrow(reno, false, saltLake, true, lineFormat);
		common.appear(renoSaltLake);

		Arrow lasVegasGrandCanyon = new Arrow(lasVegas, false, grandCanyon, true, lineFormat);
		common.appear(lasVegasGrandCanyon);

		Arrow lasVegasSaltLake = new Arrow(lasVegas, false, saltLake, true, lineFormat);
		common.appear(lasVegasSaltLake);
		common.cue();
		common.clear();

		Point points[] =
		{
				new Point(220d, 400d),
				new Point(375d, 608d),
				new Point(510d, 208d),
				new Point(570d, 448d),
				new Point(810d, 224d),
				new Point(600d, 688d),
				new Point(860d, 668d),
				new Point(1020d, 400d),
				new Point(1275d, 256d),
				new Point(1120d, 608d),
				new Point(1370d, 588d),
				new Point(1500d, 352d),
		};

		ovals = new OvalText[points.length];

		for (int i = 0; i < points.length; i++)
		{
			ovals[i] = new OvalText("" + i, points[i], ovalFormat);
		common.appear(	ovals[i]);
		}

		arrow(0, 1);
		arrow(0, 2);
		Arrow a0_3 = arrow(0, 3);
		arrow(1, 5);
		arrow(2, 3);
		arrow(2, 4);
		Arrow a3_7 = arrow(3, 7);
		arrow(4, 7);
		arrow(6, 9);
		arrow(6, 7);
		arrow(5, 6);
		Arrow a7_8 = arrow(7, 8);
		arrow(9, 10);
		arrow(10, 11);
		Arrow a8_11 = arrow(8, 11);

		common.cue();
		Paint highlight = Color.GOLD;
		common.reColor(ovals[0], highlight);
		common.reColor(a0_3, highlight);
		common.reColor(ovals[3], highlight);
		common.reColor(a3_7, highlight);
		common.reColor(ovals[7], highlight);
		common.reColor(a7_8, highlight);
		common.reColor(ovals[8], highlight);
		common.reColor(a8_11, highlight);
		common.reColor(ovals[11], highlight);

		common.cue();

	}

	private Arrow arrow(int from, int to)
	{
		Arrow arrow = new Arrow(ovals[from], false, ovals[to], true, lineFormat);
		common.appear(arrow);
		return arrow;
	}

}

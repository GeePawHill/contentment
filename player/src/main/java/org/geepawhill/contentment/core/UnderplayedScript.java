package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.ClipArt;
import org.geepawhill.contentment.actors.LabelBox;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.OvalText;
import org.geepawhill.contentment.actors.Title;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.ViewPort;
import org.geepawhill.contentment.step.CommonSteps;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UnderplayedScript
{
	private Sequence sequence;
	private CommonSteps common;

	private Format columnFormat;
	private Format subFormat;
	private Format moralFormat;
	private Format fourthFormat;

	private ArrayList<Letters> lines;
	private double lastLineY;

	public UnderplayedScript(Sequence sequence)
	{
		this.sequence = sequence;
		this.common = new CommonSteps(sequence);
		this.lines = new ArrayList<>();
		Font largeFont = new Font("GoodDog",100d);
		Font mediumFont = new Font("GoodDog",80d);
		Font moralFont = new Font("GoodDog",120d);
		columnFormat = new Format(TypeFace.font(largeFont, 3d, 1d), TypeFace.color(Color.RED, 1d), Frames.frame(Color.RED, 5d, 1d));
		subFormat = new Format(columnFormat, TypeFace.font(mediumFont,2d,1d), TypeFace.color(Color.GREENYELLOW, 1d), Frames.frame(Color.GREEN, 5d, 1d));
		moralFormat = new Format(TypeFace.font(moralFont,5d,1d), TypeFace.color(Color.LIGHTBLUE, Color.BLUE, 1d), Frames.frame(Color.BLUE, 5d, 1d));
		fourthFormat = new Format(TypeFace.mediumSans(), TypeFace.color(Color.YELLOW, 1d));
	}

	public void add()
	{
		scene1();
	}

	private void scene1()
	{
		head("Five Underplayed Premises");
		common.keyframe(16d);
		head("TDD'er for 20 years");
		common.keyframe(20d);
		sub("doing");
		sub("learning");
		sub("teaching");
		sub("arguing (w/alcohol)");

		common.keyframe(28d);
		head("Programming *is* TDD");
		common.keyframe(36d);
		sub("continuous integration");
		sub("small steps");
		sub("merciless refactoring");
		sub("microtesting");
		sub("small objects & methods");

		common.keyframe(44d);
		head("There are different TDD styles");
		sub("chicago vs london");
		sub("story vs micro");
		sub("and so on");
		common.keyframe(54d);
		head("GeePaw's Style...");
		common.keyframe(1d,0d);
		sub("...huge emphasis on microtests");
		common.keyframe(1d,8d);
		sub("...rare usage of auto-mocking tools");
		common.keyframe(1d,16d);
		sub("...think my way in");
		common.keyframe(1d,30d);
		sub("...test drive my way back out");
		
		common.keyframe(1d,41d);
		head("Five Underplayed Premises");
		common.keyframe(1d,46d);
		sub("the money premise");
		sub("the judgment premise");
		sub("the chaining premise");
		sub("the correlation premise");
		sub("the driving premise");
		
		common.keyframe(1d,56d);
		head("Underplayed?");
		common.keyframe(2d,03d);
		sub("mentioned in passing");
		sub("modeled much of the time");
		sub("not called out sharply");
		common.keyframe(2d,15d);
		head("It's Easy To Be Distracted");
		sub("look at the pretty lights!");
		sub("the premises need hammering");
		
		common.keyframe(2d,22d);
		Letters moral = new Letters("Premises Front & Center",new Point(ViewPort.CENTERX,800d),moralFormat);
		common.fadeIn(500d, moral);
		common.cue();
		

	}
	
	private Letters line(String text, Format format)
	{
		Letters line = new Letters(text,new Point(1550d,lastLineY),format,HPos.RIGHT);
		common.fadeIn(400d, line);
		lastLineY += 80d;
		lines.add(line);
		return line;
	}
	
	private void clearLines()
	{
		lastLineY=100d;
		for(Letters line : lines)
		{
			common.disappear(line);
		}
		lines.clear();
	}
	
	private void head(String text)
	{
		clearLines();
		line(text,columnFormat);
	}

	private void sub(String text)
	{
		line(text,subFormat);
	}

}

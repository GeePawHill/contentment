package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.step.Universals.*;

import java.io.File;
import java.util.ArrayList;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.geometry.HPos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UnderplayedScript
{
	private Format columnFormat;
	private Format subFormat;
	private Format moralFormat;

	private ArrayList<Letters> lines;
	private double lastLineY;

	public UnderplayedScript()
	{
		this.lines = new ArrayList<>();
		Font largeFont = new Font("GoodDog", 100d);
		Font mediumFont = new Font("GoodDog", 80d);
		Font moralFont = new Font("GoodDog", 120d);
		columnFormat = new Format(TypeFace.font(largeFont, 3d, 1d), TypeFace.color(Color.RED, 1d),
				Frames.frame(Color.RED, 5d, 1d));
		subFormat = new Format(columnFormat, TypeFace.font(mediumFont, 2d, 1d), TypeFace.color(Color.GREENYELLOW, 1d),
				Frames.frame(Color.GREEN, 5d, 1d));
		moralFormat = new Format(TypeFace.font(moralFont, 5d, 1d), TypeFace.color(Color.LIGHTBLUE, Color.BLUE, 1d),
				Frames.frame(Color.BLUE, 5d, 1d));
	}

	public void add()
	{
		// intro();
		// money();
		// judgment();
	}

	public Script make()
	{
		Script script = new Script(new MediaRhythm());
		script.add(new Keyframe(0, opening()));
		script.add(new Keyframe(3000,programmingIsTdd()));
		script.add(new Keyframe(5000,style()));
		return script;
	}

	// private void intro()
	// {
	// opening();
	//
	// sequence.add(new WaitForVideoStep(28000));
	//
	// common.keyframe(28d);
	// head("Programming *is* TDD");
	// common.keyframe(36d);
	// sub("continuous integration");
	// sub("small steps");
	// sub("merciless refactoring");
	// sub("microtesting");
	// sub("small objects & methods");
	//
	// common.keyframe(44d);
	// head("There are different TDD styles");
	// sub("chicago vs london");
	// sub("story vs micro");
	// sub("and so on");
	// common.keyframe(54d);
	// head("GeePaw's Style...");
	// common.keyframe(1d,0d);
	// sub("...huge emphasis on microtests");
	// common.keyframe(1d,8d);
	// sub("...rare usage of auto-mocking tools");
	// common.keyframe(1d,16d);
	// sub("...think my way in");
	// common.keyframe(1d,30d);
	// sub("...test drive my way back out");
	//
	// common.keyframe(1d,41d);
	// head("Five Underplayed Premises");
	// common.keyframe(1d,46d);
	// sub("the money premise");
	// sub("the judgment premise");
	// sub("the chaining premise");
	// sub("the correlation premise");
	// sub("the driving premise");
	//
	// common.keyframe(1d,56d);
	// head("Underplayed?");
	// common.keyframe(2d,03d);
	// sub("mentioned in passing");
	// sub("modeled much of the time");
	// sub("not called out sharply");
	// common.keyframe(2d,15d);
	// head("It's Easy To Be Distracted");
	// sub("look at the pretty lights!");
	// sub("the premises need hammering");
	//
	// common.keyframe(2d,22d);
	// Letters moral = new Letters("Premises Front & Center",new
	// Point(ViewPort.CENTERX,800d),moralFormat);
	// common.fadeIn(500d, moral);
	// common.cue();
	//
	//
	// }

	private Phrase opening()
	{
		Phrase phrase = new Phrase();
		phrase.add(head("Five Underplayed Premises"));
		return phrase;
	}

	private Phrase style()
	{
		Phrase phrase = new Phrase();
		phrase.add(head("Programming *is* TDD"));
		return phrase;
	}
	
	private Phrase programmingIsTdd()
	{
		Phrase phrase = new Phrase();
		phrase.add(head("There Are Styles Of TDD"));
		return phrase;
	}

	//
	// private void money()
	// {
	// common.clear();
	// head("The Money Premise");
	// common.keyframe(08d);
	// lead("we're in this for the money");
	// common.keyframe(18d);
	// sub("more features faster...");
	// sub("...means...");
	// sub("...more money!");
	//
	// common.keyframe(34d);
	// head("What's TDD *Not* About?");
	// common.keyframe(38d);
	// sub("intellectual purity");
	// common.keyframe(41d);
	// sub("art & beauty");
	// common.keyframe(42d);
	// sub("good citizenship");
	// common.keyframe(44d);
	// sub("morality");
	//
	// common.keyframe(54d);
	// lead("Test To Move Features Faster");
	//
	// common.keyframe(01d,08d);
	// head("How's The Money Premise Work?");
	// common.keyframe(01d,10d);
	// sub("only what we need");
	// common.keyframe(01d,15d);
	// sub("only what we really run");
	// sub("nothing slow");
	// sub("nothing flickery");
	// common.keyframe(01d,23d);
	// lead("no hard tests");
	// common.keyframe(01d,32d);
	// sub("easy to read");
	// sub("easy to write");
	// sub("easy to grasp");
	//
	// common.keyframe(01d,36d);
	// Letters moral = new Letters("We're In This For The Money",new
	// Point(ViewPort.CENTERX,800d),moralFormat);
	// common.fadeIn(500d, moral);
	// common.cue();
	// }
	//
	//
	// private void judgment()
	// {
	// common.clear();
	// head("Never And Always");
	// common.keyframe(04d);
	// lead("(a very long time)");
	// sub("never say never?");
	// sub("ummm");
	// sub("very rarely say never!");
	// common.keyframe(15d);
	// head("The Judgment Premise");
	// common.keyframe(17d);
	// lead("no algorithms...");
	// common.keyframe(20d);
	// sub("... for rolling code");
	// common.keyframe(22d);
	// sub("... for designing code");
	// common.keyframe(25d);
	// sub("... for TDD");
	//
	// common.keyframe(37d);
	// lead("that's good news");
	// common.keyframe(43d);
	// sub("better jobs");
	// common.keyframe(50d);
	// sub("way better lives");
	//
	// common.keyframe(57d);
	// head("The Judgment Premise");
	// sub("we rely on human judgment");
	// sub("absolutely");
	// sub("irrevocably");
	// sub("invariably");
	// sub("continuously");
	// common.keyframe(1d,4d);
	// sub("happily");
	// common.keyframe(01d,10d);
	// head("How Does Judgment Work?");
	// common.keyframe(01d,17d);
	// sub("which test to write");
	// common.keyframe(01d,20d);
	// sub("how to say it best");
	// common.keyframe(01d,26d);
	// sub("what's too awkward");
	// common.keyframe(01d,32d);
	// sub("test through vs around");
	// common.keyframe(01d,58d);
	// sub("when to refactor");
	// common.keyframe(02d,01d);
	// sub("when to bull on through");
	// common.keyframe(02d,03d);
	// moral("Judgment Premise","We Rely On Human Judgment");
	// common.cue();
	// }

	private Phrase line(String text, Format format)
	{
		Phrase phrase = new Phrase();
		Letters line = new Letters(text, new Point(1550d, lastLineY), format, HPos.RIGHT);
		phrase.add(sketch(500d,line));
		lastLineY += 80d;
		lines.add(line);
		return phrase;
	}

	// private void moral(String text)
	// {
	// Letters moral = new Letters(text,new
	// Point(ViewPort.CENTERX,690d),moralFormat);
	// common.fadeIn(500d, moral);
	// }
	//
	// private void moral(String text1,String text2)
	// {
	// moral(text1);
	// Letters moral = new Letters(text2,new
	// Point(ViewPort.CENTERX,780d),moralFormat);
	// common.fadeIn(500d, moral);
	// }

	private Phrase clearLines()
	{
		Phrase phrase = new Phrase();
		lastLineY = 100d;
		for (Letters line : lines)
		{
			phrase.add(disappear(line));
		}
		lines.clear();
		return phrase;
	}

	private Phrase head(String text)
	{
		Phrase phrase = new Phrase();
		lastLineY = 100d;
		if (!lines.isEmpty()) phrase.add(clearLines());
		phrase.add(line(text, columnFormat));
		return phrase;
	}

	private Phrase lead(String text)
	{
		lastLineY += 30d;
		return line(text, columnFormat);
	}

	private Phrase sub(String text)
	{
		return line(text, subFormat);
	}

}

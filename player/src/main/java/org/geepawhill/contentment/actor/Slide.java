package org.geepawhill.contentment.actor;

import java.util.ArrayList;
import java.util.List;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.Instant;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Slide implements Actor
{
	
	private Group group;
	
	public Slide()
	{
		group = new Group();
	}

	@Override
	public void outline(KvOutline output)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public String nickname()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void show(Sequence sequence, String[] slides)
	{
		sequence.add(new Entrance(this));
		flip(sequence,slides);
	}
	
	static class FlipStep implements Instant
	{
		private Group group;
		private List<Node> old;
		private List<Node> texts;
		
		public FlipStep(Group group,List<Node> texts)
		{
			this.texts = texts;
			this.group = group;
			old = new ArrayList<>();
		}

		@Override
		public void before(Context context)
		{
			group.getChildren().clear();
			group.getChildren().addAll(old);
		}

		@Override
		public void after(Context context)
		{
			group.getChildren().clear();
			group.getChildren().addAll(texts);
		}
		
	}
	
	public void flip(Sequence sequence, String[] slides)
	{
		List<Node> texts = makeTextBoxes(slides);
		sequence.add(new FlipStep(group,texts));
	}
	
	static class LineFormatter
	{
		String text;
		HPos alignment; 
		Paint stroke;
		Font font;
		
		public void align(Text text)
		{
			if(alignment==HPos.LEFT) return;
			if(alignment==HPos.RIGHT)
			{
				text.setTranslateX( 1600d-text.getBoundsInLocal().getWidth());
			}
			if(alignment==HPos.CENTER)
			{
				text.setTranslateX( 800d-text.getBoundsInLocal().getWidth()/2d);
			}
		}
	}

	private List<Node> makeTextBoxes(String[] slides)
	{
		ArrayList<Node> texts = new ArrayList<>();
		double nextY = 0d;
		for(String line : slides)
		{
			LineFormatter formatter = getLineFormatter(line);
			Text text = new Text(0d,nextY,formatter.text);
			text.setTextOrigin(VPos.TOP);
			text.setStroke(formatter.stroke);
			text.setFill(formatter.stroke);
			text.setFont(formatter.font);
			formatter.align(text);
			nextY+=text.getBoundsInLocal().getHeight();
			texts.add(text);
		}
		return texts;
	}

	private LineFormatter getLineFormatter(String line)
	{
		LineFormatter formatter = new LineFormatter();
		formatter.stroke = Color.WHITE;
		formatter.font = new Font("Buxton Sketch",30d);
		formatter.alignment = HPos.LEFT;
		if(line.startsWith("+++"))
		{
			formatter.stroke = Color.RED;
			formatter.text = line.substring(3,line.length());
			formatter.font = new Font("Buxton Sketch",40d);
			return formatter;
		}
		if(line.startsWith("++"))
		{
			formatter.stroke = Color.BLUE;
			formatter.text = line.substring(2,line.length());
			formatter.font = new Font("Buxton Sketch",60d);
			return formatter;
		}
		if(line.startsWith("+"))
		{
			formatter.stroke = Color.GREENYELLOW;
			formatter.alignment = HPos.RIGHT;
			formatter.text = line.substring(1,line.length());
			formatter.font = new Font("Buxton Sketch",80d);
			return formatter;
		}
		if(line.startsWith("="))
		{
			formatter.stroke = Color.YELLOW;
			formatter.font = new Font("Buxton Sketch",100d);
			formatter.text =line.substring(1,line.length());
			formatter.alignment = HPos.CENTER;
			return formatter;
		}
		formatter.font = new Font("Buxton Sketch",100d);
		formatter.stroke = Color.YELLOW;
		formatter.text = line;
		return formatter;
	}
	
}

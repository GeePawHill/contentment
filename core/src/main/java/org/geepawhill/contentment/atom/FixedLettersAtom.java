package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FixedLettersAtom implements NodeAtom
{
	
	private Canvas canvas;
	private GroupSource owner;
	private Position at;
	private int columns;
	private int rows;
	private double cellWidth;
	private double cellHeight;
	private Font font;
	
	public FixedLettersAtom(GroupSource owner, int columns, int rows)
	{
		this.owner = owner;
		this.columns = columns;
		this.rows = rows;
		font = new Font("Consolas",40d);
		Text text = new Text("A");
		text.setFont(font);
		PointPair bounds = new PointPair(text);
		cellWidth = bounds.width();
		cellHeight = bounds.height();
	}

	@Override
	public void setup(Context context)
	{
		canvas = new Canvas(cellWidth*columns,cellHeight*rows);
		at.position(canvas,new PointPair(0,0,cellWidth*columns,cellHeight*rows));
		JfxUtility.addIfNeeded(owner, canvas);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		return false;
	}

	@Override
	public Node get()
	{
		return canvas;
	}

	@Override
	public void at(Position position)
	{
		this.at = position;
	}
	
	public FixedLettersSayAtom say(Paint paint,int row,int column,String text)
	{
		double x = column*cellWidth;
		double y = row*cellHeight;
		return new FixedLettersSayAtom(()->canvas,font,paint,x,y,text);
	}

}

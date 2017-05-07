package org.geepawhill.contentment.step;

import java.util.ArrayList;
import java.util.List;

import org.geepawhill.contentment.step.SlideFormat.Layout;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class SlideFormatter
{

	public static final double VMARGIN = 20d;
	public static final double HMARGIN = 40d;

	public List<SlideFormat> layout(String... lines)
	{
		ArrayList<SlideFormat> formats = new ArrayList<>();
		double lastY = VMARGIN;
		for(String line : lines)
		{
			SlideFormat format = new SlideFormat();
			format.text = new Text();
			format.text.setTextOrigin(VPos.TOP);
			format.text.setY(lastY);
			if(line.startsWith("+++"))
			{
				format.text.setText(line.substring(3));
				format.text.setFont(new Font("Buxton Sketch",40d));
				format.text.setStroke(Color.RED);
				format.text.setFill(Color.RED);
				format.layout = Layout.INDENT;
			}
			else if(line.startsWith("++"))
			{
				format.text.setText(line.substring(2));
				format.text.setFont(new Font("Buxton Sketch",60d));
				format.text.setStroke(Color.BLUE);
				format.text.setFill(Color.BLUE);			
				format.layout = Layout.LEFT;
			}
			else if (line.startsWith("+"))
			{
				format.text.setText(line.substring(1));
				format.text.setFont(new Font("Buxton Sketch",80d));
				format.text.setStroke(Color.YELLOWGREEN);
				format.text.setFill(Color.YELLOWGREEN);
				format.layout = Layout.RIGHT;

			}
			else if (line.startsWith("="))
			{
				format.layout = Layout.CENTER;
				format.text.setText(line.substring(1));
				format.text.setFont(new Font("Buxton Sketch",100d));
				format.text.setStroke(Color.YELLOW);
				format.text.setFill(Color.YELLOW);			}
			else
			{
				format.layout = Layout.LEFT;
				format.text.setText(line);
				format.text.setFont(new Font("Buxton Sketch",100d));
				format.text.setStroke(Color.YELLOW);
				format.text.setFill(Color.YELLOW);
			}
			lastY = format.text.getBoundsInParent().getMaxY();
			formats.add(format);
		}
		
		double scale = 1d;
		if(lastY>900d)
		{
			scale = 900d/lastY;
			System.out.println("Scale: "+scale);
			lastY = VMARGIN;
			for(SlideFormat format : formats)
			{
				format.text.getTransforms().add(new Scale(scale,scale));
				lastY= format.text.getBoundsInParent().getMaxY();
			}
			
		}
		for(SlideFormat format : formats)
		{
			switch(format.layout)
			{
			case LEFT:
				format.text.setX(HMARGIN);
				break;
			case RIGHT:
				format.text.getTransforms().add(new Translate((1600d-HMARGIN)/scale-format.text.getBoundsInLocal().getWidth(),0d));
				break;
			case CENTER:
				format.text.setX(800d-HMARGIN/2d -format.text.getBoundsInLocal().getWidth()/2d);
				break;
			case INDENT:
				format.text.setX(2d*HMARGIN);
				break;
			}
		}
		return formats;
	}

}

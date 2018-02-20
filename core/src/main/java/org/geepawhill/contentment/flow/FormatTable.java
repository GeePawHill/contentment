package org.geepawhill.contentment.flow;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.util.HashMap;
import java.util.Map;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class FormatTable
{
	Map<Size,Map<Color,Format>> sizeToColors;
	
	static class EntryNotFoundException extends RuntimeException
	{
		public EntryNotFoundException(Size size, Color color, String string)
		{
			super("FormatTable.get( "+size.toString()+","+color.toString()+" "+string);
		}

		private static final long serialVersionUID = -4794179798871696945L;
	}
	
	public FormatTable()
	{
		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		Paint primary = color(119, 187, 65);
		Paint secondary = color(177, 140, 254);
		Paint emphatic = color(255, 255, 0);

		sizeToColors = new HashMap<>();
		
		Map<Color,Format> jumbos = new HashMap<>();
		jumbos.put(Color.Primary, format(primary,jumbo));
		jumbos.put(Color.Secondary, format(secondary,jumbo));
		jumbos.put(Color.Emphatic, format(emphatic,jumbo));
		sizeToColors.put(Size.Jumbo, jumbos);
		
		Map<Color,Format> normals = new HashMap<>();
		normals.put(Color.Primary, format(primary,normal));
		normals.put(Color.Secondary, format(secondary,normal));
		normals.put(Color.Emphatic, format(emphatic,normal));
		sizeToColors.put(Size.Normal, normals);
		
		Map<Color,Format> smalls = new HashMap<>();
		smalls.put(Color.Primary, format(primary,small));
		smalls.put(Color.Secondary, format(secondary,small));
		smalls.put(Color.Emphatic, format(emphatic,small));
		
		sizeToColors.put(Size.Small, smalls);
	}

	public Format get(Size size, Color color)
	{
		Map<Color,Format> colorToFormat = sizeToColors.get(size);
		if(colorToFormat==null) throw new EntryNotFoundException(size,color,"Size not found.");
		Format result = colorToFormat.get(color);
		if(result==null) throw new EntryNotFoundException(size,color,"Color not found.");
		return result;
	}
	
	private Format format(Paint majorColor, double fontsize) {
		Font font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize);
		return new Format(TypeFace.font(font, 2d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
	}


}

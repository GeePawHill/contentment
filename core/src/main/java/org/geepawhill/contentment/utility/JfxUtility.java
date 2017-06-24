package org.geepawhill.contentment.utility;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

public class JfxUtility
{
	public static void anchor(Node node)
	{
		AnchorPane.setTopAnchor(node, 0d);
		AnchorPane.setBottomAnchor(node, 0d);
		AnchorPane.setLeftAnchor(node, 0d);
		AnchorPane.setRightAnchor(node, 0d);
	}

	public static AnchorPane makeAnchorFor(Node node)
	{
		AnchorPane anchor = new AnchorPane();
		anchor(node);
		anchor.getChildren().add(node);
		return anchor;
	}

	public static boolean forEachDescendant(Node node, NodeProcessor processor)
	{
		if (!processor.accept(node)) return false;
		if (node instanceof Parent)
		{
			Parent parent = (Parent) node;
			for (Node child : parent.getChildrenUnmodifiable())
			{
				if (!forEachDescendant(child, processor)) return false;
			}
		}
		return true;
	}
	
	public static void capture(Node node)
	{
		WritableImage image = node.snapshot(new SnapshotParameters(), null);

		// TODO: probably use a file chooser here
		File file = new File("output_0.png");
		int i=1;
		while(file.exists()) file = new File("output_"+ i++ +".png");
		try
		{
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		}
		catch (IOException e)
		{
			// TODO: handle exception here
		}
	}

}

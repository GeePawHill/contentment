package org.geepawhill.contentment.jfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class ScaleListener implements ChangeListener<Number>
{
	private final Pane canvas;
	private Group scalingGroup;

	public ScaleListener(Pane canvas, Group scalingGroup)
	{
		this.canvas = canvas;
		this.scalingGroup = scalingGroup;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue)
	{
		changed();
	}

	public void changed()
	{
		final double newWidth = canvas.getWidth();
		final double newHeight = canvas.getHeight();
		double scaleFactor = newWidth / newHeight > 16d / 9d ? newHeight / 900d : newWidth / 1600d;
		Scale newScale = new Scale(scaleFactor, scaleFactor);
		newScale.setPivotX(0);
		newScale.setPivotY(0);
		scalingGroup.getTransforms().setAll(newScale);
	}

}

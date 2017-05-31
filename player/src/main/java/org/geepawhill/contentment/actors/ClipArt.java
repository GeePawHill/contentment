package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.EntranceStep;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClipArt implements Actor
{

	Group group;
	Image image;
	private ImageView view;

	public ClipArt(Image image, PointPair points)
	{
		group = new Group();
		view = new ImageView(image);
		group.getChildren().add(view);
		view.setX(points.from.x);
		view.setY(points.from.y);
	}

	public void flip(Sequence sequence)
	{
		sequence.add(new EntranceStep(this));
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
}

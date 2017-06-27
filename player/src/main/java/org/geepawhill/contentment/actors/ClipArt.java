package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.FastStep;
import org.geepawhill.contentment.step.Step;

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
		view.setX(points.from.x);
		view.setY(points.from.y);
	}
	
	@Override
	public Step draw(double ms)
	{
		return new FastStep(new AddNode(group, view));
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

package model;

import java.awt.Color;
import java.awt.Shape;

/**
 * Representation of a shape and its color.
 */
public class ColouredShape {
	Color color;
	Shape shape;

	public ColouredShape(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}
}

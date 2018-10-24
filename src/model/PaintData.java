package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import controler.Paint;

public class PaintData {
	Paint controller;

	List<ColouredShape> shapes = new ArrayList<ColouredShape>();
	Tool selectedTool;
	Color color = Color.BLACK;
	Tool[] tools;
	Tool[] colors;
	boolean[] buttons = new boolean[3];

	public PaintData(Paint controller) {
		this.controller = controller;
		initTools();
	}

	public class Tool extends AbstractAction implements MouseInputListener {
		Shape shape;
		String name;
		Point o;

		/**
		 * Creates a tool. If the name of the tool is not unique, it might create
		 * problems.
		 * 
		 * @param name The tool's name
		 */
		public Tool(String name) {
			super(name);
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			if (Paint.debug)
				System.out.println("using tool " + this.name);
			Tool old = selectedTool;
			selectedTool = this;
			controller.changeTool(old, selectedTool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && !rightButtonDown())
				o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && !rightButtonDown())
				shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public String toString() {
			return name;
		}

		public AbstractAction[] getOptions() {
			return null;
		}
	}

	/**
	 * Initializes the tools list
	 */
	public void initTools() {
		Tool[] tools = { new Tool("pen") {
			public void mouseDragged(MouseEvent e) {
				if (!SwingUtilities.isLeftMouseButton(e))
					return;

				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					shape = path;
					shapes.add(new ColouredShape(color, path));
				}
				path.lineTo(e.getX(), e.getY());
				controller.toolFinished(new ArrayList(shapes));
			}

			public AbstractAction[] getOptions() {
				return colors.clone();
			}
		}, new Tool("rect") {
			public void mouseDragged(MouseEvent e) {
				if (!SwingUtilities.isLeftMouseButton(e))
					return;

				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					shape = rect;
					shapes.add(new ColouredShape(color, rect));
				}
				rect.setRect(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
						Math.abs(e.getY() - o.getY()));
				controller.toolFinished(new ArrayList(shapes));
			}

			public AbstractAction[] getOptions() {
				return colors.clone();
			}
		}, new Tool("elli") {
			public void mouseDragged(MouseEvent e) {
				if (!SwingUtilities.isLeftMouseButton(e))
					return;

				Ellipse2D.Double elli = (Ellipse2D.Double) shape;
				if (elli == null) {
					elli = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					shape = elli;
					shapes.add(new ColouredShape(color, elli));
				}
				elli.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
						Math.abs(e.getY() - o.getY()));
				controller.toolFinished(new ArrayList(shapes));
			}

			public AbstractAction[] getOptions() {
				return colors.clone();
			}
		} };

		this.tools = tools;

		Tool[] colors = { new Tool("Black") {

			public void actionPerformed(ActionEvent e) {
				if (Paint.debug)
					System.out.println("Choosing color Black");
				color = Color.BLACK;
				controller.toolFinished(new ArrayList(shapes));
			}
		}, new Tool("Red") {
			public void actionPerformed(ActionEvent e) {
				if (Paint.debug)
					System.out.println("Choosing color Red");
				color = Color.RED;
				controller.toolFinished(new ArrayList(shapes));
			}
		}, new Tool("Orange") {
			public void actionPerformed(ActionEvent e) {
				if (Paint.debug)
					System.out.println("Choosing color Orange");
				color = Color.ORANGE;
				controller.toolFinished(new ArrayList(shapes));
			}
		}, new Tool("Blue") {
			public void actionPerformed(ActionEvent e) {
				if (Paint.debug)
					System.out.println("Choosing color Blue");
				color = Color.BLUE;
				controller.toolFinished(new ArrayList(shapes));
			}
		} };

		this.colors = colors;
	}

	/**
	 * Saves which button is pressed or not
	 * 
	 * @param button the pressed button
	 */
	public void buttonDown(int button) {
		buttons[button - 1] = true;
	}

	/**
	 * Saves which button is pressed or not
	 * 
	 * @param button the released button
	 */
	public void buttonUp(int button) {
		buttons[button - 1] = false;
	}

	/**
	 * Indicates if the left button is held down
	 * 
	 * @return
	 */
	public boolean leftButtonDown() {
		return buttons[0];
	}

	/**
	 * Indicates if the left button is held down
	 * 
	 * @return
	 */
	public boolean rightButtonDown() {
		return buttons[2];
	}

	/**
	 * Returns a copy of the tools list
	 * 
	 * @return A copy of the tools list
	 */
	public Tool[] getTools() {
		return tools.clone();
	}

	/**
	 * Returns a copy of the colors list
	 * 
	 * @return A copy of the colors list
	 */
	public Tool[] getColors() {
		return tools.clone();
	}
}

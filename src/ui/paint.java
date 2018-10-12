//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;
import java.lang.Math.*;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

class Paint extends JFrame {
	Vector<ColouredShape> shapes = new Vector<ColouredShape>();
	Tool tool;
	JPanel panel;
	Color color = Color.BLACK;

	class ColouredShape {
		Color color;
		Shape shape;
		
		public ColouredShape(Color color, Shape shape) {
			this.color = color;
			this.shape = shape;
		}
	}
	
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				shape = path;
				shapes.add(new ColouredShape(color, path));
			}
			path.lineTo(e.getX(), e.getY());
			panel.repaint();
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				shape = rect;
				shapes.add(new ColouredShape(color, rect));
			}
			rect.setRect(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
					Math.abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("elli") {
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double elli = (Ellipse2D.Double) shape;
			if (elli == null) {
				elli = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				shape = elli;
				shapes.add(new ColouredShape(color, elli));
			}
			elli.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
					Math.abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("Black") {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Choosing color Black");
			color = Color.BLACK;
			panel.repaint();
		}
	} , new Tool("Red") {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Choosing color Red");
			color = Color.RED;
			panel.repaint();
		}
	}, new Tool("Orange") {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Choosing color Orange");
			color = Color.ORANGE;
			panel.repaint();
		}
	}, new Tool("Blue") {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Choosing color Blue");
			color = Color.BLUE;
			panel.repaint();
		}
	}};

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);
		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (ColouredShape shape : shapes) {
					g2.setColor(shape.color);
					g2.draw(shape.shape);
				}
			}
		});

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint("L'outil de peinture le plus perfectionné de 2018");
			}
		});
	}
}

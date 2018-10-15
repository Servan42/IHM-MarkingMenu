//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.ColouredShape;
import model.PaintData.Tool;

/* paint *******************************************************************/

public class PaintUI extends JFrame {
	JPanel panel;
	List<ColouredShape> displayed;

	public PaintUI(String title, AbstractAction[] tools) {
		super(title);
		displayed = new ArrayList();

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

				for (ColouredShape shape : displayed) {
					g2.setColor(shape.getColor());
					g2.draw(shape.getShape());
				}
			}
		});

		pack();
		setVisible(true);
	}

	/**
	 * Assigns the required listeners
	 * 
	 * @param oldTool
	 *            The listeners to be removed
	 * @param newTool
	 *            The listeners to be added
	 */
	public void changeTool(Tool oldTool, Tool newTool) {
		panel.removeMouseListener(oldTool);
		panel.addMouseListener(newTool);

		panel.removeMouseMotionListener(oldTool);
		panel.addMouseMotionListener(newTool);
	}

	/**
	 * Assigns the shapes to be displayed
	 * 
	 * @param shapes
	 *            The list of shapes to be displayed
	 */
	public void setShapes(List shapes) {
		displayed = shapes;
	}

	/**
	 * Redraws the whole panel
	 */
	public void redraw() {
		panel.repaint();
	}
}

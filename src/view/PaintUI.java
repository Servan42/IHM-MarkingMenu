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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controler.MarkingMenu;
import model.ColouredShape;
import model.PaintData.Tool;

/* paint *******************************************************************/

public class PaintUI extends JFrame {
	JPanel panel;
	JLayeredPane layeredPane;
	MarkingMenu mm;
	List<ColouredShape> displayed;
	MouseListener[] storedListeners;
	MouseMotionListener[] storedMotionListeners;

	public PaintUI(String title, AbstractAction[] tools) {
		super(title);
		displayed = new ArrayList();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		layeredPane = new JLayeredPane();
		layeredPane.setMinimumSize(new Dimension(800, 600));
		layeredPane.add(panel = new JPanel() {
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
		}, new Integer(1));

		layeredPane.add(mm = new MarkingMenu(this), new Integer(1));

		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		mm.setBounds(0, 0, this.getWidth(), this.getHeight());

		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);
		add(layeredPane, BorderLayout.CENTER);

		layeredPane.moveToBack(mm);

		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {
					// layeredPane.moveToBack(panel);
					// mm.setPosX(e.getX());
					// mm.setPosY(e.getY());
					displayMenu(e.getX(), e.getY());
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

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

	/**
	 * Displays the MarkingMenu over the Pane and affects the right listeners
	 * 
	 * @param xPos
	 *            the x position of the marking menu
	 * @param yPos
	 *            the y position of the marking menu
	 */
	public void displayMenu(int xPos, int yPos) {
		storedListeners = panel.getListeners(MouseListener.class);
		for (MouseListener ml : storedListeners)
			panel.removeMouseListener(ml);
		storedMotionListeners = panel.getListeners(MouseMotionListener.class);
		for (MouseMotionListener ml : storedMotionListeners)
			panel.removeMouseMotionListener(ml);

		MouseListener[] mls = mm.getMouseListeners();
		for (MouseListener ml : mls)
			panel.addMouseListener(ml);
		MouseMotionListener[] mmls = mm.getMouseMotionListeners();
		for (MouseMotionListener ml : mmls)
			panel.addMouseMotionListener(ml);

		layeredPane.moveToBack(panel);
		mm.setPosX(xPos);
		mm.setPosY(yPos);
	}

	/**
	 * Hides the MarkingMenu behind the panel and affects the right listeners
	 */
	public void hideMenu() {
		MouseListener[] MMml = panel.getListeners(MouseListener.class);
		for (MouseListener ml : MMml)
			panel.removeMouseListener(ml);
		MouseMotionListener[] MMmml = panel.getListeners(MouseMotionListener.class);
		for (MouseMotionListener ml : MMmml)
			panel.removeMouseMotionListener(ml);

		for (MouseListener ml : storedListeners)
			panel.addMouseListener(ml);
		for (MouseMotionListener ml : storedMotionListeners)
			panel.addMouseMotionListener(ml);

		layeredPane.moveToFront(panel);
	}
}

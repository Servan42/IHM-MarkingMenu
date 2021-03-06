package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import controller.MarkingMenu;
import controller.Paint;

/**
 * View of the Marking Menu.
 */
public class MarkingMenuUI extends ComponentUI {

	private MarkingMenu menu;

	/**
	 * Constructor of the view.
	 * 
	 * @param menu Controller of the Marking Menu.
	 */
	public MarkingMenuUI(MarkingMenu menu) {
		this.menu = menu;
		Handler handler = new Handler();
		menu.addMouseListener(handler);
		menu.addMouseMotionListener(handler);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		int diameter = menu.getDiameter();
		Object[] items = menu.getList();
		int numberOfItems = items.length;
		super.paint(g, c);
		g.setColor(Color.BLACK);
		g.fillOval(menu.getPosX() - diameter / 2 - 3, menu.getPosY() - diameter / 2 - 3, diameter + 6, diameter + 6);
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(menu.getPosX() - diameter / 2, menu.getPosY() - diameter / 2, diameter, diameter);
		g.setColor(Color.BLACK);
		g.fillOval(menu.getPosX() - diameter / 8, menu.getPosY() - diameter / 8, diameter / 4, diameter / 4);

		for (int i = 0; i < numberOfItems; i++) {

			g.drawLine(menu.getPosX(), menu.getPosY(),
					menu.getPosX() + ((int) (diameter / 2 * Math.cos(Math.toRadians(i * 360 / numberOfItems)))),
					menu.getPosY() + ((int) (diameter / 2 * Math.sin(Math.toRadians(i * 360 / numberOfItems)))));

			g.drawString(items[i].toString(),
					menu.getPosX()
							+ ((int) (diameter / 3
									* Math.cos(Math.toRadians((i * 360 / numberOfItems) + 360 / numberOfItems / 2))))
							- items[i].toString().length() * 2,
					menu.getPosY() + ((int) (diameter / 3
							* Math.sin(Math.toRadians((i * 360 / numberOfItems) + 360 / numberOfItems / 2)))));
		}

		if (Paint.debug)
			System.out.println("MarkingMenuUI.paint() called.");
	}

	/**
	 * Resolve which item is at which angle.
	 * 
	 * @param angle The angle of the item to identify.
	 * @return The item that is at this angle.
	 */
	public int indexFromAngle(double angle) {
		int nbItems = menu.getList().length;
		int i = 0;
		angle = 360 - angle; // Making it clockwise

		for (i = 0; i < nbItems; i++) {
			if (angle >= i * 360 / nbItems && angle < (i + 1) * 360 / nbItems)
				break;
		}

		return i;
	}

	/**
	 * Class that catch the events on the MarkingMenu.
	 */
	public class Handler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			menu.handleReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			menu.handleMoved(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}
}

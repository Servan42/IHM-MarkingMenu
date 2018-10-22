package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import controler.MarkingMenu;
import controler.Paint;

public class MarkingMenuUI extends ComponentUI {

	private MarkingMenu menu;

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
		g.setColor(Color.RED);
		g.fillOval(menu.getPosX() - diameter / 2, menu.getPosY() - diameter / 2, diameter, diameter);
		g.setColor(Color.BLACK);
		g.fillOval(menu.getPosX() - diameter / 8, menu.getPosY() - diameter / 8, diameter / 4, diameter / 4);

		for (int i = 0; i < numberOfItems * 2; i++) {
			if (i % 2 == 0) {
				g.drawString(items[i / 2].toString(),
						menu.getPosX() + ((int) (diameter / 3 * Math.cos(Math.toRadians(i/2 * 360 / numberOfItems)))),
						menu.getPosY() + ((int) (diameter / 3 * Math.sin(Math.toRadians(i/2 * 360 / numberOfItems)))));
			} else {
				g.drawLine(menu.getPosX(), menu.getPosY(),
						menu.getPosX() + ((int) (diameter / 2 * Math.cos(Math.toRadians(i * 360 / (numberOfItems*2))))),
						menu.getPosY() + ((int) (diameter / 2 * Math.sin(Math.toRadians(i * 360 / (numberOfItems*2))))));
			}
		}

		if (Paint.debug)
			System.out.println("MarkingMenuUI.paint() called.");
	}

	public class Handler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			menu.mouseReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
		}

	}
}

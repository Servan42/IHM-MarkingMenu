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
		super.paint(g, c);
		g.setColor(Color.BLUE);
		g.fillOval(menu.getPosX() - 50, menu.getPosY() - 50, 100, 100);
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

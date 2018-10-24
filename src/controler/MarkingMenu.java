package controler;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import model.MarkingMenuData;
import model.PaintData;
import view.MarkingMenuUI;
import view.PaintUI;

public class MarkingMenu extends JComponent {

	private MarkingMenuUI ui;
	private MarkingMenuData data;
	private PaintUI globalUI;

	public MarkingMenu(int posX, int posY, AbstractAction[] list, PaintUI globalUI) throws IllegalArgumentException {
		if (list.length > 8)
			throw new IllegalArgumentException();
		this.setUI(ui = new MarkingMenuUI(this));
		this.setBounds(0, 0, globalUI.getWidth(), globalUI.getHeight());
		data = new MarkingMenuData(posX, posY, list);
		this.globalUI = globalUI;
	}

	public int getPosX() {
		return data.getPosX();
	}

	public int getPosY() {
		return data.getPosY();
	}

	public void setPosX(int x) {
		data.setPosX(x);
	}

	public void setPosY(int y) {
		data.setPosY(y);
	}

	public int getDiameter() {
		return data.getDiameter();
	}

	public Object[] getList() {
		return data.getList();
	}

	public void handleReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			globalUI.hideMenu();
			if (e.getPoint().distance(data.getPosX(), data.getPosY()) > (data.getDiameter() / 8)) {
				AbstractAction item = data.getList()[getIndexFromPoint(e.getPoint())];
				item.actionPerformed(new ActionEvent(item, ActionEvent.ACTION_PERFORMED, "MarkingMenuSelect"));
			}
		}
	}

	public void handleMoved(MouseEvent e) {
		if (e.getPoint().distance(data.getPosX(), data.getPosY()) > (data.getDiameter() / 2)) {
			globalUI.hideMenu();
			
			AbstractAction item = data.getList()[getIndexFromPoint(e.getPoint())];
			item.actionPerformed(new ActionEvent(item, ActionEvent.ACTION_PERFORMED, "MarkingMenuSelect"));
		}
	}

	private int getIndexFromPoint(Point2D e) {
		double angle =-Math.toDegrees(Math.atan2((e.getY() - data.getPosY()), (e.getX() - data.getPosX()))); 
		if (angle < 0)
			angle += 360;
		return ui.indexFromAngle(angle);
	}
}

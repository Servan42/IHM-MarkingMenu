package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import model.MarkingMenuData;
import model.PaintData.Tool;
import view.MarkingMenuUI;
import view.PaintUI;

/**
 * Controller of the Marking Menu
 */
public class MarkingMenu extends JComponent {

	private static final long serialVersionUID = 1L;
	private MarkingMenuUI ui;
	private MarkingMenuData data;
	private PaintUI globalUI;

	/**
	 * Constructor of the Marking Menu.
	 * 
	 * @param posX     Horizontal position.
	 * @param posY     Vertical position.
	 * @param list     Tool list, can be null.
	 * @param globalUI UI where the menu is going to be added.
	 * @throws IllegalArgumentException This is triggered when the list contains
	 *                                  more than 8 items.
	 */
	public MarkingMenu(int posX, int posY, AbstractAction[] list, PaintUI globalUI) throws IllegalArgumentException {
		if (list.length > 8)
			throw new IllegalArgumentException();
		this.setUI(ui = new MarkingMenuUI(this));
		this.setBounds(0, 0, globalUI.getWidth(), globalUI.getHeight());
		data = new MarkingMenuData(posX, posY, list);
		this.globalUI = globalUI;
	}

	/**
	 * Getter.
	 * 
	 * @return The horizontal position of the menu.
	 */
	public int getPosX() {
		return data.getPosX();
	}

	/**
	 * Getter.
	 * 
	 * @return The vertical position of the menu.
	 */
	public int getPosY() {
		return data.getPosY();
	}

	/**
	 * Sets the horizontal position of the menu.
	 * 
	 * @param x Horizontal position to set.
	 */
	public void setPosX(int x) {
		data.setPosX(x);
	}

	/**
	 * Sets the vertical position of the menu.
	 * 
	 * @param y Vertical position to set.
	 */
	public void setPosY(int y) {
		data.setPosY(y);
	}

	/**
	 * Getter.
	 * 
	 * @return The diameter of the menu.
	 */
	public int getDiameter() {
		return data.getDiameter();
	}

	/**
	 * Getter.
	 * 
	 * @return The list of tools contained in the menu.
	 */
	public Object[] getList() {
		return data.getList();
	}

	/**
	 * Perform the action that has to be done when the mouse is released. Does
	 * nothing if the mouse is release in the center. Select an item if the mouse is
	 * released on the menu.
	 * 
	 * @param e The mouse event.
	 */
	public void handleReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			globalUI.hideMenu();
			if (e.getPoint().distance(data.getPosX(), data.getPosY()) > (data.getDiameter() / 8)) {
				AbstractAction item = data.getList()[getIndexFromPoint(e.getPoint())];
				item.actionPerformed(new ActionEvent(item, ActionEvent.ACTION_PERFORMED, "MarkingMenuSelect"));
			}
		}
	}

	/**
	 * Perform the action that has to be done when the mouse is moved. Does nothing
	 * if the mouse is moved inside of the menu. When you leave the menu zone,
	 * selects an item.
	 * 
	 * @param e The mouse event.
	 */
	public void handleMoved(MouseEvent e) {
		if (e.getPoint().distance(data.getPosX(), data.getPosY()) > (data.getDiameter() / 2)) {
			globalUI.hideMenu();
			AbstractAction item = data.getList()[getIndexFromPoint(e.getPoint())];
			item.actionPerformed(new ActionEvent(item, ActionEvent.ACTION_PERFORMED, "MarkingMenuSelect"));
			if (((Tool) item).getOptions() != null)
				globalUI.displayMenu(new MarkingMenu(e.getX(), e.getY(), ((Tool) item).getOptions(), globalUI));
		}
	}

	/**
	 * Resolve which item is being pointed.
	 * 
	 * @param e A point in space in the menu.
	 * @return The item number that is located at this point.
	 */
	private int getIndexFromPoint(Point2D e) {
		double angle = -Math.toDegrees(Math.atan2((e.getY() - data.getPosY()), (e.getX() - data.getPosX())));
		if (angle < 0)
			angle += 360;
		return ui.indexFromAngle(angle);
	}
}

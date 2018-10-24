package model;

import javax.swing.AbstractAction;

/**
 * The data of the Marking Menu.
 */
public class MarkingMenuData {
	private int posX;
	private int posY;
	private int diameter;
	private AbstractAction[] list;

	/**
	 * Constructor of MarkingMenuData.
	 * 
	 * @param x    Horizontal position of the Marking Menu.
	 * @param y    Vertical position of the Marking Menu.
	 * @param list List of tools inside the Marking Menu.
	 */
	public MarkingMenuData(int x, int y, AbstractAction[] list) {
		posX = x;
		posY = y;
		diameter = 200;
		this.list = list;
	}

	/**
	 * Sets the horizontal position of the menu.
	 * 
	 * @param x Horizontal position to set.
	 */
	public void setPosX(int x) {
		posX = x;
	}

	/**
	 * Sets the vertical position of the menu.
	 * 
	 * @param y Vertical position to set.
	 */
	public void setPosY(int y) {
		posY = y;
	}

	/**
	 * Getter.
	 * 
	 * @return The horizontal position of the menu.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Getter.
	 * 
	 * @return The vertical position of the menu.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Getter.
	 * 
	 * @return The diameter of the menu.
	 */
	public int getDiameter() {
		return diameter;
	}

	/**
	 * Getter.
	 * 
	 * @return A copy of the tool list contained in the menu.
	 */
	public AbstractAction[] getList() {
		return list.clone();
	}
}

package model;

import javax.swing.AbstractAction;

public class MarkingMenuData {
	private int posX;
	private int posY;
	private int diameter;
	private AbstractAction[] list;
	
	public MarkingMenuData(int x, int y, AbstractAction[] list) {
		posX = x;
		posY = y;
		diameter = 200;
		this.list = list;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getDiameter() {
		return diameter;
}

	public AbstractAction[] getList() {
		return list.clone();
	}
}

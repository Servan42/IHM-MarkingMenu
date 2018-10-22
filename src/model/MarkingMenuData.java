package model;

public class MarkingMenuData {
	private int posX;
	private int posY;
	private int diameter;
	private Object[] list;
	
	public MarkingMenuData(int x, int y, Object[] list) {
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

	public Object[] getList() {
		return list.clone();
	}
}

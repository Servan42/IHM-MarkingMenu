package model;

public class MarkingMenuData {
	private int posX;
	private int posY;
	
	public MarkingMenuData(int x, int y) {
		posX = x;
		posY = y;
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
}

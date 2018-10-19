package controler;

import javax.swing.JComponent;

import model.MarkingMenuData;
import view.MarkingMenuUI;

public class MarkingMenu extends JComponent {

	private MarkingMenuUI ui;
	private MarkingMenuData data;

	public MarkingMenu() {
		this.setUI(ui = new MarkingMenuUI(this));
		data = new MarkingMenuData(0, 0);
	}
	
	public int getPosX() {
		return data.getPosX();
	}
	
	public int getPosY() {
		return data.getPosY();
	}
	
	public void setPosX(int x){
		data.setPosX(x);
	}
	
	public void setPosY(int y){
		data.setPosY(y);
	}
}

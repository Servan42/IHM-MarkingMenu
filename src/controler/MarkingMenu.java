package controler;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import model.MarkingMenuData;
import view.MarkingMenuUI;
import view.PaintUI;

public class MarkingMenu extends JComponent {

	private MarkingMenuUI ui;
	private MarkingMenuData data;
	private PaintUI globalUI;

	public MarkingMenu(PaintUI globalUI) {
		this.setUI(ui = new MarkingMenuUI(this));
		data = new MarkingMenuData(0, 0);
		this.globalUI = globalUI;
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
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			globalUI.hideMenu();
		// layeredPane.moveToFront(panel);
	}
}

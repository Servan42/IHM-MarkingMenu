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

	public MarkingMenu(int posX, int posY, Object[] list, PaintUI globalUI) throws IllegalArgumentException {
		if(list.length > 8)
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
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			globalUI.hideMenu();
	}
}

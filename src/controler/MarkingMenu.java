package controler;

import java.awt.Graphics;

import javax.swing.JComponent;

import view.MarkingMenuUI;

public class MarkingMenu extends JComponent {
	
	public MarkingMenu() {
		this.setUI(new MarkingMenuUI(this));
	}
	
}

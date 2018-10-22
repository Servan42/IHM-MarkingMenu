package controler;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingUtilities;

import model.PaintData;
import model.PaintData.Tool;
import view.PaintUI;

public class Paint {
	public static final boolean debug = true;

	private PaintData donnees;
	private PaintUI ui;

	/**
	 * Creates the controller with a link to the data and ui
	 * 
	 * @param donnees The data
	 * @param ui      The ui
	 */
	public Paint(PaintData donnees, PaintUI ui) {
		this.donnees = donnees;
		this.ui = ui;
	}

	/**
	 * Creates the controller with no link
	 */
	public Paint() {
	}

	/**
	 * Assigns a new tool to be used
	 * 
	 * @param oldTool The tool we're switching from
	 * @param newTool The tool we're switching to
	 */
	public void changeTool(Tool oldTool, Tool newTool) {
		ui.changeTool(oldTool, newTool);
	}

	/**
	 * Assigns the eventually new list of shapes to be drawn by the ui, and redraws
	 * 
	 * @param shapes The list of shapes to be displayed
	 */
	public void toolFinished(List shapes) {
		ui.setShapes(shapes);
		ui.redraw();
	}

	/**
	 * Sets the data to be used
	 * 
	 * @param donnees The data
	 */
	public void setData(PaintData donnees) {
		this.donnees = donnees;
	}

	/**
	 * Sets the ui to be used
	 * 
	 * @param ui the ui
	 */
	public void setUI(PaintUI ui) {
		this.ui = ui;
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			ui.displayMenu(e.getX(), e.getY());
		}
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint();
				PaintData data = new PaintData(paint);
				PaintUI ui = new PaintUI("L'outil de peinture le plus perfectionné de 2018", data.getTools(), paint);
				paint.setUI(ui);
				paint.setData(data);
			}
		});
	}
}

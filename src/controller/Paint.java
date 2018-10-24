package controller;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import model.PaintData;
import model.PaintData.Tool;
import view.PaintUI;

/**
 * Controller of Paint
 */
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
	@SuppressWarnings("rawtypes")
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

	/**
	 * Handles the mousePressed event over the window
	 * 
	 * @param e the event data
	 */
	public void mousePressed(MouseEvent e) {
		// Clic droit : on crée et fait afficher un menu selon le contexte
		if (e.getButton() == MouseEvent.BUTTON3 && !donnees.leftButtonDown()) {
			try {
				MarkingMenu mm = new MarkingMenu(e.getX(), e.getY(), donnees.getTools(), ui);
				ui.displayMenu(mm);
			} catch (IllegalArgumentException iae) {
				// La liste donnée est trop longue, on n'affiche pas le menu
				if (debug)
					System.out.println("Paint.mousePressed IllegalArgumentException");
			}
		}

		donnees.buttonDown(e.getButton());
	}

	/**
	 * Notify Paint data that the mouse button is no longer pressed.
	 * 
	 * @param e The mouse event.
	 */
	public void mouseReleased(MouseEvent e) {
		donnees.buttonUp(e.getButton());
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint();
				PaintData data = new PaintData(paint);
				AbstractAction[] tools;
				tools = Stream.concat(Arrays.stream(data.getTools()), Arrays.stream(data.getColors()))
						.toArray(Tool[]::new);
				tools = Stream.concat(Arrays.stream(tools), Arrays.stream(data.getFill()))
						.toArray(Tool[]::new);
				PaintUI ui = new PaintUI("L'outil de peinture le plus perfectionné de 2018", tools, paint);

				paint.setUI(ui);
				paint.setData(data);
			}
		});
	}
}

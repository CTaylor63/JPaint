package commands;

import java.util.ArrayList;

import main.CommandHistory;
import main.CustomShape;
import main.Main;

public class PasteCommand implements Command{
	
	private ArrayList<CustomShape> components = new ArrayList<CustomShape>();
	

	public void action() {
		if (Main.selected != null) {
			ArrayList<CustomShape> newComponents = new ArrayList<CustomShape>();
			for (CustomShape shape : Main.selected) {
				newComponents.add(shape.cloneShape());
			}
			for (CustomShape shape : newComponents) {
				shape.updateLocation(50,50);
			}
			for (CustomShape shape : newComponents) {
				Main.shapeTotal.add(shape);
				components.add(shape);
			}
			Main.redrawCanvas();
			CommandHistory.add(this);
		}
	}
	
	@Override
	public void undo() {
		for (CustomShape shape : components) {
			if (Main.shapeTotal.contains(shape)) {
				Main.shapeTotal.remove(shape);
			}
		}
		Main.redrawCanvas();
	}

	@Override
	public void redo() {
		for (CustomShape shape : components) {
			Main.shapeTotal.add(shape);
		}
		Main.redrawCanvas();
	}

}

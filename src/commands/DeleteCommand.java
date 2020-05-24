package commands;

import java.util.ArrayList;

import main.CustomShape;
import main.Main;

public class DeleteCommand implements Command {

	private ArrayList<CustomShape> components = new ArrayList<CustomShape>();
	
	@Override
	public void undo() {
		for (CustomShape shape : components) {
			if (!Main.shapeTotal.contains(shape)) {
				Main.shapeTotal.add(shape);
			}
		}
		Main.redrawCanvas();
	}

	@Override
	public void redo() {
		for (CustomShape shape : components) {
			if (Main.shapeTotal.contains(shape)) {
				Main.shapeTotal.remove(shape);
			}
		}
		Main.redrawCanvas();
	}

	@Override
	public void action() {
		if (Main.selected != null) {
			for (CustomShape shape : Main.selected) {
				if (Main.shapeTotal.contains(shape)) {
					Main.shapeTotal.remove(shape);
					components.add(shape);
				}
			}
		}
		Main.redrawCanvas();
	}

}

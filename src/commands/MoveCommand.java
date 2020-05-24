package commands;

import java.util.ArrayList;

import main.CustomShape;
import main.Main;

public class MoveCommand implements Command {
	
	
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int dx;
	private int dy;
	private ArrayList<CustomShape> components = new ArrayList<CustomShape>();
	
	
	public MoveCommand(int x, int y) {
		this.startX = x;
		this.startY = y;
		components = Main.selected;
	}
	
	public void setEnd(int x, int y) {
		endX = x;
		endY = y;
	}
	
	public void setDxDy(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void undo() {
		for (CustomShape shape : components) {
			shape.updateLocation(-1*(endX-startX),-1*(endY-startY));
		}
		Main.redrawCanvas();
		
	}

	@Override
	public void redo() {
		for (CustomShape shape : components) {
			shape.updateLocation((endX-startX),(endY-startY));
		}
		Main.redrawCanvas();
	}

	@Override
	public void action() {
		if (Main.selected != null) {
			for (CustomShape shape : Main.selected) {
				shape.updateLocation(dx,dy);
			}
			Main.redrawCanvas();
		}
		
	}

}

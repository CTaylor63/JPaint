package commands;

import java.awt.event.MouseEvent;

import main.CustomShape;
import main.Main;

public class DrawCommand implements Command{
	
	
	private CustomShape shape;
	private MouseEvent e;
	private int dx;
	private int dy;
	
	public DrawCommand(CustomShape shape) {
		this.shape = shape;
		Main.shapeTotal.add(shape);
	}
	
	public void setEvent(MouseEvent e) {
		this.e = e;
	}
	
	public void setDxDy(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void undo() {
		if (Main.shapeTotal.contains(shape)) {
			Main.shapeTotal.remove(shape);
		}
		Main.redrawCanvas();
		
	}

	@Override
	public void redo() {
		Main.shapeTotal.add(shape);
		shape.draw(e, dx, dy);
	}

	@Override
	public void action() {
		shape.draw(e, dx, dy);
	}

}

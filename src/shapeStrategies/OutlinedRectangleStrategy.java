package shapeStrategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class OutlinedRectangleStrategy implements DrawStrategy {
	
	public OutlinedRectangleStrategy() {
	}
	
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy) {
		Graphics2D graphic = canvas.getGraphics2D();
		graphic.setColor(primary);
		graphic.setStroke(new BasicStroke(5));
		graphic.drawRect(origin.getX(), origin.getY(), dx, dy);
	}
	
}

package shapeStrategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class OutlinedFilledRectangleStrategy implements DrawStrategy {


	
	public OutlinedFilledRectangleStrategy() {
	}
	
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy) {
		Graphics2D graphic = canvas.getGraphics2D();
		graphic.setColor(secondary);
		graphic.fillRect(origin.getX(), origin.getY(), dx, dy);
		graphic.setColor(primary);
		graphic.setStroke(new BasicStroke(5));
		graphic.drawRect(origin.getX(), origin.getY(), dx, dy);
	}
	
}

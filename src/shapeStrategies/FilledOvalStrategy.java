package shapeStrategies;

import java.awt.Color;
import java.awt.Graphics2D;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class FilledOvalStrategy implements DrawStrategy {
	
	
	public FilledOvalStrategy() {
	}
	
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy) {
		Graphics2D graphic = canvas.getGraphics2D();
		graphic.setColor(primary);
		graphic.fillOval(origin.getX(), origin.getY(), dx, dy);
		
	}
	
}

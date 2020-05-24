package shapeStrategies;

import java.awt.Color;
import java.awt.Graphics2D;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class FilledTriangleStrategy implements DrawStrategy {
	
	public FilledTriangleStrategy() {
	}
	
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy) {
		
		
		int[] xs = {origin.getX(), origin.getX(), e.getX()};
		int[] ys = {origin.getY(), e.getY(), e.getY()};
		

		Graphics2D graphic = canvas.getGraphics2D();
		graphic.setColor(primary);
		graphic.fillPolygon(xs, ys, 3);
		
	}
	

}

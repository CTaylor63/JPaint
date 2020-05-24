package shapeStrategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class OutlinedFilledTriangleStrategy implements DrawStrategy{

	
	public OutlinedFilledTriangleStrategy() {
	}
	
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy) {	

		int[] xs = {origin.getX(), origin.getX(), e.getX()};
		int[] ys = {origin.getY(), e.getY(), e.getY()};

		Graphics2D graphic = canvas.getGraphics2D();
		graphic.setColor(secondary);
		graphic.fillPolygon(xs, ys, 3);
		graphic.setColor(primary);
		graphic.setStroke(new BasicStroke(5));
		graphic.drawPolygon(xs, ys, 3);
	}
	
	
}

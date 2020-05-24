package selectStrategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.Strategy;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class SelectedTriangleStrategy  implements SelectedStrategy{
	
	private Strategy strategy;
	private int offset = 5;
	

	public SelectedTriangleStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint endpoint, int dx, int dy) {
		
		strategy.draw(canvas, primary, secondary, origin, endpoint, dx, dy);
		
		Graphics2D graphic = canvas.getGraphics2D();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
		

		int[] xs = {origin.getX(), origin.getX(), endpoint.getX()};
		int[] ys = {origin.getY(), endpoint.getY(), endpoint.getY()};
		
		
		int[] newX = new int[3];
		int[] newY = new int[3];
		
		if (endpoint.getX() > origin.getX() && endpoint.getY() > origin.getY()) {
			newX[0] = xs[0] - offset;
			newX[1] = xs[1] - offset;
			newX[2] = xs[2] + 2*offset;
			newY[0] = ys[0] - 2*offset;
			newY[1] = ys[1] + offset;
			newY[2] = ys[2] + offset;
		}
		else if(endpoint.getX() < origin.getX() && endpoint.getY() > origin.getY()) {
			newX[0] = xs[0] + offset;
			newX[1] = xs[1] + offset;
			newX[2] = xs[2] - 2*offset;
			newY[0] = ys[0] - 2*offset;
			newY[1] = ys[1] + offset;
			newY[2] = ys[2] + offset;
		}
	
		else if (endpoint.getX() < origin.getX() && endpoint.getY() < origin.getY()) {
			newX[0] = xs[0] + offset;
			newX[1] = xs[1] + offset;
			newX[2] = xs[2] - 2*offset;
			newY[0] = ys[0] + 2*offset;
			newY[1] = ys[1] - offset;
			newY[2] = ys[2] - offset;
		}
		else {
			newX[0] = xs[0] - offset;
			newX[1] = xs[1] - offset;
			newX[2] = xs[2] + 2*offset;
			newY[0] = ys[0] + 2*offset;
			newY[1] = ys[1] - offset;
			newY[2] = ys[2] - offset;
		}
		
        graphic.setStroke(stroke);
        graphic.setColor(Color.BLACK);
        graphic.drawPolygon(newX, newY, 3);
		
	}
	
	public Strategy getWrappedStrategy() {
		return strategy;
	}

}

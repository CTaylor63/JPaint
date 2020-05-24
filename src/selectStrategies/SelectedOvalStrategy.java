package selectStrategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.Strategy;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class SelectedOvalStrategy implements SelectedStrategy {
	
	private Strategy strategy;
	private int offset = 5;
	private int dOffset = 10;
	
	
	public SelectedOvalStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	

	public void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint endpoint, int dx, int dy) {
		strategy.draw(canvas, primary, secondary, origin, endpoint, dx, dy);
		
		Graphics2D graphic = canvas.getGraphics2D();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
	    graphic.setStroke(stroke);
	    graphic.setColor(Color.BLACK);
	    graphic.drawOval(origin.getX()-offset, origin.getY()-offset, dx+dOffset, dy+dOffset);
	}
	
	public Strategy getWrappedStrategy() {
		return strategy;
	}
	
}

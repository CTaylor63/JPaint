package main;

import java.awt.Color;

import model.ShapeType;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class CustomShapeBuilder {
	
	private PaintCanvasBase base = null;
	private Strategy drawStrat = null;
	private PaintPoint origin = null;
	private PaintPoint endpoint = null;
	private ShapeType type = null;
	private Color primary = null;
	private Color secondary = null;
	private int dx = 0;
	private int dy = 0;
	
	
	public CustomShapeBuilder() {
	}
	
	public void setDrawStrategy(Strategy strat) {
		drawStrat = strat;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setCanvas(PaintCanvasBase canvas) {
		base = canvas;
	}
	
	public void setPrimary(Color color) {
		primary = color;
	}
	
	public void setSecondary(Color color) {
		secondary = color;
	}
	
	public void setType(ShapeType type) {
		this.type = type;
	}
	
	public void setOrigin(PaintPoint origin) {
		this.origin = new PaintPoint(origin.getX(),origin.getY());
	}
	
	public void setEndpoint(PaintPoint endpoint) {
		this.endpoint = new PaintPoint(endpoint.getX(),endpoint.getY());
	}
	
	public CustomShape build() {
		return new CustomShape(base, origin, endpoint, dx, dy, primary, secondary, type, drawStrat);
	}
	
}

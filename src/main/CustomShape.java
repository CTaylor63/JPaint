package main;

import java.awt.Color;
import java.awt.event.MouseEvent;

import model.ShapeType;
import selectStrategies.SelectedOvalStrategy;
import selectStrategies.SelectedRectangleStrategy;
import selectStrategies.SelectedStrategy;
import selectStrategies.SelectedTriangleStrategy;
import shapeStrategies.DrawStrategy;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class CustomShape implements ShapeComponent{

	
	private PaintCanvasBase canvas;
	private PaintPoint origin;
	private PaintPoint endpoint;
	private Color primary;
	private Color secondary;
	private int dx;
	private int dy;
	private ShapeType type;
	private Strategy drawStrat;
	//private boolean isSelected = false;
	
	public CustomShape(PaintCanvasBase base, int startX, int startY) {
		canvas = base;
		origin.setLocation(startX, startY);
	}
	
	public CustomShape(PaintCanvasBase base) {
		canvas = base;
		origin = new PaintPoint();
	}
	
	//Constructor for cloning
	public CustomShape(PaintCanvasBase base, PaintPoint origin, PaintPoint endpoint, int dx, int dy,
						Color primary, Color secondary, ShapeType type,
							Strategy strategy) {
		canvas = base;
		this.origin = new PaintPoint(origin.getX(),origin.getY());
		this.dx = dx;
		this.dy = dy;
		this.primary = primary;
		this.secondary = secondary;
		this.drawStrat = strategy;
		this.endpoint = endpoint;
		this.type = type;
	}
	
	public void setDrawStrategy(DrawStrategy strat) {
		drawStrat = strat;
	}
	

	public void draw(MouseEvent e, int dx, int dy) {
		
		this.dx = Math.abs(dx);
		this.dy = Math.abs(dy);		
		
		
		if (type != ShapeType.TRIANGLE) {
			//release point is below and to left of origin
			if(e.getX() < origin.getX() && e.getY() > origin.getY()) {
				origin.setX(e.getX());
				//System.out.println("origin set: " + origin.getX());
			}
			
			//release point is below and to right of origin
			else if (e.getX() > origin.getX() && e.getY() > origin.getY()) {
				
			}
			
			//release point is above and to left of origin
			else if (e.getX() < origin.getX() && e.getY() < origin.getY()) {
				origin.setX(e.getX());
				origin.setY(e.getY());
				//System.out.println("origin set");
			}
			
			//release point is above and to right of origin
			else {
				origin.setY(e.getY());
				//System.out.println("origin set");
			}
		}
		
		endpoint = new PaintPoint(e.getX(),e.getY());
		drawStrat.draw(canvas, primary, secondary, origin, endpoint, this.dx, this.dy);
		
	}
	
	public void setSelected(boolean flag) {
		if (flag) {
			switch(type) {
			case ELLIPSE:
				drawStrat = new SelectedOvalStrategy(drawStrat);
				break;
			case RECTANGLE:
				drawStrat = new SelectedRectangleStrategy(drawStrat);
				break;
			case TRIANGLE:
				drawStrat = new SelectedTriangleStrategy(drawStrat);
			}
		}
		else {
			if (drawStrat instanceof SelectedStrategy) {
				drawStrat = ((SelectedStrategy) drawStrat).getWrappedStrategy();
			}
		}
	}
	
	public void reDraw() {
		drawStrat.draw(canvas, primary, secondary, origin, endpoint, this.dx, this.dy);
	}

	//update the object's origin point
	public void updateLocation(int dx, int dy) {
		origin.setX(origin.getX()+dx);
		origin.setY(origin.getY()+dy);
		endpoint.setX(endpoint.getX()+dx);
		endpoint.setY(endpoint.getY()+dy);
	}
	
	//Determines if the point exists within the shape
	public boolean containsPoint(int x, int y) {
		if (dx < 0 ) {
			if (dy < 0) {
				if (x >= origin.getX() + dx && x <= origin.getX() && y >= origin.getY() + dy && y <= origin.getY()) {
					return true;
				}	
			}
			else {
				if (x >= origin.getX() + dx && x <= origin.getX() && y <= origin.getY() + dy && y >= origin.getY()) {
					return true;
				}
			}
		}
		else {
			if (dy < 0) {
				if (x <= origin.getX() + dx && x >= origin.getX() && y >= origin.getY() + dy && y <= origin.getY()) {
					return true;
				}	
			}
			else {
				if (x <= origin.getX() + dx && x >= origin.getX() && y <= origin.getY() + dy && y >= origin.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Determines if the shape exists within the bounding box
	public boolean within(int x, int y, int dx, int dy) {
		if (origin.getX() < x + dx && origin.getX() + this.dx > x && origin.getY() < y + dy && origin.getY() + this.dy > dy) {
				    return true;
		}
		return false;
	}

	
	public CustomShape cloneShape() {
		CustomShapeBuilder builder = new CustomShapeBuilder();
		if (drawStrat instanceof SelectedStrategy) {
			builder.setDrawStrategy(((SelectedStrategy)drawStrat).getWrappedStrategy());
		}
		else {
			builder.setDrawStrategy(drawStrat);
		}
		builder.setCanvas(canvas);
		builder.setDx(dx);
		builder.setDy(dy);
		builder.setEndpoint(endpoint);
		builder.setOrigin(origin);
		builder.setPrimary(primary);
		builder.setSecondary(secondary);
		builder.setType(type);
		return builder.build();
	}
	
	
}

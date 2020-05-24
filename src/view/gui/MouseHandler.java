package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import commands.DrawCommand;
import commands.MoveCommand;
import main.CommandHistory;
import main.CustomShape;
import main.CustomShapeBuilder;
import main.Main;
import main.ShapeComponent;
import main.ShapeGroup;
import model.ColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import shapeStrategies.DrawStrategy;
import shapeStrategies.DrawStrategyFactory;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {
	
	private PaintCanvasBase canvas;
	private PaintPoint origin;
	private CustomShape custom;
	private ColorMap map;
	private IApplicationState state;
	private StartAndEndPointMode mode;
	private MoveCommand move;
	private DrawCommand draw;
	
	public MouseHandler(PaintCanvasBase base, IApplicationState appState) {
		canvas = base;
		map = new ColorMap();
		state = appState;;
	}
	
	public void mousePressed(MouseEvent e) {
		mode = state.getActiveStartAndEndPointMode();
		if (mode == StartAndEndPointMode.DRAW) {
		
			CustomShapeBuilder builder = new CustomShapeBuilder();
			
			builder.setCanvas(canvas);
			
			origin = new PaintPoint(e.getX(),e.getY());

			builder.setOrigin(origin);
			builder.setEndpoint(new PaintPoint(origin.getX(),origin.getY()));
			builder.setDrawStrategy(getStrategy());
			builder.setPrimary(map.shapeToColor(state.getActivePrimaryColor()));
			builder.setSecondary(map.shapeToColor(state.getActiveSecondaryColor()));
			builder.setType(state.getActiveShapeType());
			custom = builder.build();
			
			draw = new DrawCommand(custom);
			
		}
		else if (mode == StartAndEndPointMode.SELECT) {
			if (Main.selected != null) {
				for (CustomShape component : Main.selected) {
					component.setSelected(false);
				}
			}
			Main.selected = new ArrayList<CustomShape>();
			origin = new PaintPoint(e.getX(),e.getY());
		}
		else if (mode == StartAndEndPointMode.MOVE) {
			move = new MoveCommand(e.getX(),e.getY());
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (mode == StartAndEndPointMode.DRAW) {
			Main.redrawCanvas();
			int dx = e.getX() - origin.getX();
			int dy = e.getY() - origin.getY();
			
			//drawShape(e, dx, dy);
			draw.setEvent(e);
			draw.setDxDy(dx, dy);
			draw.action();
			CommandHistory.add(draw);
		}
		else if (mode == StartAndEndPointMode.SELECT) {
			int dx = e.getX() - origin.getX();
			int dy = e.getY() - origin.getY();
			
			
			for (CustomShape shape : Main.shapeTotal) {
				if (shape.containsPoint(e.getX(), e.getY()) || shape.within(e.getX(), e.getY(), dx, dy)) {
					if (!Main.selected.contains(shape)) {
						for(ShapeGroup group : Main.groups) {
							if (group.containsShape(shape)) {
								Main.selected.addAll(group.getShapes());
							}
						}
						if (!Main.selected.contains(shape)) {
							Main.selected.add(shape);
						}
					}
				}
			}
			
			for (ShapeComponent component : Main.selected) {
				component.setSelected(true);
			}
			Main.redrawCanvas();
		}
		else if (mode == StartAndEndPointMode.MOVE) {
			
			move.setEnd(e.getX(), e.getY());
			move.action();
			CommandHistory.add(move);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (mode == StartAndEndPointMode.DRAW) {
			Main.redrawCanvas();
			int dx = e.getX() - origin.getX();
			int dy = e.getY() - origin.getY();
			
			//drawShape(e, dx, dy);
			draw.setEvent(e);
			draw.setDxDy(dx, dy);
			draw.action();
		}
		else if (mode == StartAndEndPointMode.SELECT) {
		}
		else if (mode == StartAndEndPointMode.MOVE) {
			int dx = e.getX() - origin.getX();
			int dy = e.getY() - origin.getY();
			origin.setLocation(e.getX(), e.getY());
			
			move.setDxDy(dx,dy);
			move.action();
		}
	}
	
	private DrawStrategy getStrategy() {
		
		ShapeType type = state.getActiveShapeType();
		ShapeShadingType fill = state.getActiveShapeShadingType();
		
		DrawStrategyFactory factory = new DrawStrategyFactory();
		
		if (type == ShapeType.ELLIPSE) {
			if (fill == ShapeShadingType.FILLED_IN) {
				return factory.createFilledOvalStrategy();
			}
			else if (fill == ShapeShadingType.OUTLINE) {
				return factory.createOutlinedOvalStrategy();
			}
			else {
				return factory.createOutlinedFilledOvalStrategy();
			}
		}
		else if (type == ShapeType.RECTANGLE) {
			if (fill == ShapeShadingType.FILLED_IN) {
				return factory.createFilledRectanlgeStrategy();
			}
			else if (fill == ShapeShadingType.OUTLINE) {
				return factory.createOutlinedRectanlgeStrategy();
			}
			else {
				return factory.createOutlinedFilledRectanlgeStrategy();
			}
		}
		else {
			if (fill == ShapeShadingType.FILLED_IN) {
				return factory.createFilledTriangleStrategy();
			}
			else if (fill == ShapeShadingType.OUTLINE) {
				return factory.createOutlinedTriangleStrategy();
			}
			else {
				return factory.createOutlinedFilledTriangleStrategy();
			}
		}
	}

	
}

package shapeStrategies;

import java.awt.Color;

import main.Strategy;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public interface DrawStrategy extends Strategy {

	public void draw(PaintCanvasBase base, Color primary, Color secondary, PaintPoint origin, PaintPoint e, int dx, int dy);
	
}

package selectStrategies;

import java.awt.Color;

import main.Strategy;
import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public interface SelectedStrategy extends Strategy {
	
	public void draw(PaintCanvasBase base, Color primary, Color secondary, PaintPoint origin, PaintPoint endpoint, int dx, int dy);
	
	public Strategy getWrappedStrategy();

}

package main;

import java.awt.Color;

import utility.PaintPoint;
import view.interfaces.PaintCanvasBase;

public interface Strategy {

	public abstract void draw(PaintCanvasBase canvas, Color primary, Color secondary, PaintPoint origin, PaintPoint endpoint, int dx, int dy);
	
}

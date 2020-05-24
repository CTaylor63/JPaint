package model;

import java.awt.Color;
import java.util.EnumMap;


//Make this a singleton
public class ColorMap {

	EnumMap<ShapeColor,Color> map = new EnumMap<ShapeColor,Color>(ShapeColor.class);
	
	public ColorMap() {
		map.put(ShapeColor.BLACK,Color.BLACK);
		map.put(ShapeColor.BLUE,Color.BLUE);
		map.put(ShapeColor.CYAN,Color.CYAN);
		map.put(ShapeColor.DARK_GRAY,Color.DARK_GRAY);
		map.put(ShapeColor.GRAY,Color.GRAY);
		map.put(ShapeColor.GREEN,Color.GREEN);
		map.put(ShapeColor.LIGHT_GRAY,Color.LIGHT_GRAY);
		map.put(ShapeColor.MAGENTA,Color.MAGENTA);
		map.put(ShapeColor.ORANGE,Color.ORANGE);
		map.put(ShapeColor.PINK,Color.PINK);
		map.put(ShapeColor.RED,Color.RED);
		map.put(ShapeColor.WHITE,Color.WHITE);
		map.put(ShapeColor.YELLOW,Color.YELLOW);
	}
	
	public Color shapeToColor(ShapeColor color) {
		return map.get(color);
	}
	
}

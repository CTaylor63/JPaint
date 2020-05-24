package main;

import java.util.ArrayList;

public class ShapeGroup implements ShapeComponent {
	
	private ArrayList<ShapeComponent> components = new ArrayList<ShapeComponent>();

	@Override
	public void reDraw() {
		for (ShapeComponent component: components) {
			component.reDraw();
		}

	}

	@Override
	public void updateLocation(int dx, int dy) {
		for (ShapeComponent component : components) {
			component.updateLocation(dx, dy);
		}

	}
	
	public void addChild(ShapeComponent component) {
		if (components.contains(component)) {
			return;
		}
		components.add(component);
	}
	
	public void removeChild(ShapeComponent component) {
		if (components.contains(component)) {
			components.remove(component);
		}
	}
	
	public boolean containsShape(ShapeComponent component) {
		for (ShapeComponent object : components) {
			if (component instanceof CustomShape) {
				return component == (CustomShape)object;
			}
			else {
				((ShapeGroup)component).containsShape(component);
			}
		}
		return false;
	}
	
	public ArrayList<CustomShape> getShapes(){
		ArrayList<CustomShape> shapes = new ArrayList<CustomShape>();
		for (ShapeComponent component : components) {
			if (component instanceof ShapeGroup) {
				shapes.addAll(((ShapeGroup)component).getShapes());
			}
			else {
				shapes.add((CustomShape)component);
			}
		}
		return shapes;
	}
	
	public void setSelected(boolean flag) {
		for (ShapeComponent component : components) {
			component.setSelected(flag);
		}
	}

	public ArrayList<ShapeComponent> getChildren(){
		return components;
	}

}

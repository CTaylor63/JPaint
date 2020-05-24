package commands;

import java.util.ArrayList;

import main.Main;
import main.ShapeComponent;
import main.ShapeGroup;

public class UngroupCommand implements Command {
	
	private ArrayList<ShapeComponent> addedChildren = new ArrayList<ShapeComponent>();
	private ShapeGroup removedGroup;

	@Override
	public void undo() {
		
		for (ShapeComponent component : addedChildren) {
			if (component instanceof ShapeGroup && Main.groups.contains((ShapeGroup)component)) {
				Main.groups.remove((ShapeGroup)component);
			}
		}
		Main.groups.add(removedGroup);
	}
	

	@Override
	public void redo() {
		Main.groups.remove(removedGroup);
		for (ShapeComponent component : addedChildren) {
			if (component instanceof ShapeGroup) {
				Main.groups.add((ShapeGroup)component);
			}
		}
	}

	@Override
	public void action() {
		if (Main.selected != null) {
			if(Main.groups != null) {
				for (ShapeGroup group : Main.groups) {
					if(group.containsShape(Main.selected.get(0))) {
						addedChildren = group.getChildren();
						Main.groups.remove(group);
						removedGroup = group;
						break;
					}
				}
				for (ShapeComponent component : addedChildren) {
					if (component instanceof ShapeGroup) {
						Main.groups.add((ShapeGroup)component);
					}
				}
			}
		}
		
	}

}

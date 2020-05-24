package commands;

import java.util.ArrayList;

import main.CustomShape;
import main.Main;
import main.ShapeGroup;

public class GroupCommand implements Command {

	private ShapeGroup collection = new ShapeGroup();
	private ArrayList<ShapeGroup> removedGroups = new ArrayList<ShapeGroup>();
	
	@Override
	public void undo() {
		Main.groups.remove(collection);
		Main.groups.addAll(removedGroups);
		
	}

	@Override
	public void redo() {
		Main.groups.add(collection);
		Main.groups.removeAll(removedGroups);
		
	}

	@Override
	public void action() {
		if (Main.selected != null) {
			for (CustomShape shape : Main.selected) {
				/*
				if (!Main.groups.isEmpty()) {
					for (ShapeGroup group : Main.groups) {
						if (group.containsShape(shape) && !collection.containsShape(shape)) {
							collection.addChild(group);
							removedGroups.add(group);
							Main.groups.remove(group);
						}
					}
				}*/
				if (!collection.containsShape(shape)) {
					collection.addChild(shape);
				}
			}
			Main.groups.add(collection);
		}
	}

}

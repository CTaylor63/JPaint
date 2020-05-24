package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.MouseHandler;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

import commands.DeleteCommand;
import commands.GroupCommand;
import commands.PasteCommand;
import commands.UngroupCommand;

public class Main {
	
	public static ArrayList<CustomShape> selected = null;
	public static ArrayList<CustomShape> shapeTotal = null;
	public static ArrayList<CustomShape> clipboard = null;
	public static ArrayList<ShapeGroup> groups = new ArrayList<ShapeGroup>();
	
	public static PaintCanvasBase canvas;
	
	public static void redrawCanvas() {
		canvas.paintImmediately(0,0,canvas.getWidth(),canvas.getHeight());
		Iterator<CustomShape> iterator = shapeTotal.iterator();
		while (iterator.hasNext()) {
			ShapeComponent CustomShape = iterator.next();
			CustomShape.reDraw();
		}
	}
	
	
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        canvas = paintCanvas;
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        
        JButton deleteBtn = guiWindow.getButton(EventName.DELETE);
        deleteBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		DeleteCommand delete = new DeleteCommand();
        		delete.action();
        		CommandHistory.add(delete);
        	}
        });
        
        
        JButton copyBtn = guiWindow.getButton(EventName.COPY);
        copyBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		clipboard = selected;
        	}
        	
        });
        
        
        JButton pasteBtn = guiWindow.getButton(EventName.PASTE);
        pasteBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		PasteCommand paste = new PasteCommand();
        		paste.action();
        		CommandHistory.add(paste);
        	}
        	
        });
        
        
        JButton groupBtn = guiWindow.getButton(EventName.GROUP);
        groupBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		GroupCommand group = new GroupCommand();
        		group.action();
        		CommandHistory.add(group);
        	}
        });
        
        JButton ungroupBtn = guiWindow.getButton(EventName.UNGROUP);
        ungroupBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		UngroupCommand ungroup = new UngroupCommand();
        		ungroup.action();
        		CommandHistory.add(ungroup);        		
        	}
        });
        
        
        JButton undoBtn = guiWindow.getButton(EventName.UNDO);
        undoBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		CommandHistory.undo();
        	}
        });
        
        JButton redoBtn = guiWindow.getButton(EventName.REDO);
        redoBtn.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		CommandHistory.redo();
        	}
        });
        
        
        shapeTotal = new ArrayList<CustomShape>();
        
        MouseHandler handler = new MouseHandler(paintCanvas,appState);
        
        paintCanvas.addMouseListener(handler);
        paintCanvas.addMouseMotionListener(handler);
    }
}

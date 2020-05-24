package commands;

public interface Command {

	public void action();
	
	public void undo();
	
	public void redo();
	
}

package main;

public interface ShapeComponent {

	public void reDraw();
	
	public void updateLocation(int dx, int dy);
	
	public void setSelected(boolean flag);
}

package shapeStrategies;


public class DrawStrategyFactory {
	
	public DrawStrategy createFilledOvalStrategy() {
		return new FilledOvalStrategy();
	}
	
	public DrawStrategy createFilledRectanlgeStrategy() {
		return new FilledRectangleStrategy();
	}
	
	public DrawStrategy createFilledTriangleStrategy() {
		return new FilledTriangleStrategy();
	}
	
	public DrawStrategy createOutlinedOvalStrategy() {
		return new OutlinedOvalStrategy();
	}
	
	public DrawStrategy createOutlinedRectanlgeStrategy() {
		return new OutlinedRectangleStrategy();
	}
	
	public DrawStrategy createOutlinedTriangleStrategy() {
		return new OutlinedTriangleStrategy();
	}
	
	public DrawStrategy createOutlinedFilledOvalStrategy() {
		return new OutlinedFilledOvalStrategy();
	}
	
	public DrawStrategy createOutlinedFilledRectanlgeStrategy() {
		return new OutlinedFilledRectangleStrategy();
	}
	
	public DrawStrategy createOutlinedFilledTriangleStrategy() {
		return new OutlinedFilledTriangleStrategy();
	}

	
}

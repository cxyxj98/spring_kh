package test04;

public class Wheel {
	 private int radius;
	 
	 public Wheel() {
		// TODO Auto-generated constructor stub
	}
	public Wheel(int radius) {
		super();
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	@Override
	public String toString() {
		return "Wheel [radius=" + radius + "]";
	}
	
}

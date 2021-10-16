package test04;

public class Car {
	private Wheel wheel;

	public Car() {
		// TODO Auto-generated constructor stub
	}
	public Car(Wheel wheel) {
		super();
		this.wheel = wheel;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}
	@Override
	public String toString() {
		return "Car [wheel=" + wheel + "]";
	}
	
	 
}

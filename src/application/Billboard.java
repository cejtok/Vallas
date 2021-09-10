package application;
import java.io.Serializable;

public class Billboard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1039904103717338562L;
	private double width;
	private double height;
	private boolean inUse;
	private String brand;
	
	public Billboard(double w, double h, boolean iu, String b) {
		width = w;
		height = h; 
		inUse = iu;
		brand = b;		
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public boolean isInUse() {
		return inUse;
	}

	public String getBrand() {
		return brand;
	}	
	
	public double CalculateArea() {
		return width*height;
	}
	

}

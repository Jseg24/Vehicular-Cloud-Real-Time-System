package package1;

public class Car {
	private int carID;
	private int ownerID;
	private String make;
	private String model;
	private int year;
	private int res;
	
	public Car(int carID, int ownerID, String make, String model, int year, int res) {
		this.carID = carID;
		this.ownerID = ownerID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.res = res;
	}
	
	
	public String toString() {
	    return carID + ", " + ownerID + ", " + make + ", " + model + ", " + year + ", " + res;
	}
	
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}

}

package quickhospital.pojos;

import java.util.ArrayList;

public class City {
	private ArrayList<Hospital> hospitals;

	public City(ArrayList<Hospital> hospitals) {
		super();
		this.hospitals = hospitals;
	}
	public City() {
		super();
		this.hospitals = new ArrayList<>();
	}
}

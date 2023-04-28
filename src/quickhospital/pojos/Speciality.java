package quickhospital.pojos;

import java.util.ArrayList;

public class Speciality {

	// Atributes
	private Integer id;
	private String name;
	private ArrayList<String> symptoms;
	private ArrayList<Doctor> doctors;
	private WaitingList waitingList;

	// Empty constructor
	public Speciality() {
		super();
		this.symptoms = new ArrayList<>();
		this.doctors = new ArrayList<>();
	}

	// Constructor using Fields

	public Speciality(Integer id, String name, ArrayList<String> symptoms, ArrayList<Doctor> doctors,
			WaitingList waitingList) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
		this.doctors = doctors;
		this.waitingList = waitingList;
	}

	public Speciality(Integer id, String name, WaitingList waitingList) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = new ArrayList<>();
		this.doctors = new ArrayList<>();
		this.waitingList = waitingList;
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public WaitingList getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(WaitingList waitingList) {
		this.waitingList = waitingList;
	}

}

package quickhospital.pojos;

import java.util.ArrayList;

public class Patient {
	private static final long serialVersionUID = -434234724257282729L;
	//Atributes
	private Integer id;
	private String name;
	private Integer speciality_id;
	private ArrayList<Symptom> symptoms;
	
	//Empty constructor
	public Patient() {
		super();
		this.symptoms=new ArrayList<>();
	}
	public Patient(Integer id,String name) {
		this.id= id;
		this.name= name; 
		this.symptoms=new ArrayList<>();
	}

	//Constructors using Fields
	public Patient(Integer id, String name, Integer speciality_id, ArrayList<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.speciality_id = speciality_id;
		this.symptoms = symptoms;
	}


	public Patient(Integer id, String name, Integer speciality_id) {
		super();
		this.id = id;
		this.name = name;
		this.speciality_id = speciality_id;
		this.symptoms=new ArrayList<>();
	}
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
	public Integer getSpeciality_id() {
		return speciality_id;
	}
	public void setSpeciality_id(Integer speciality_id) {
		this.speciality_id = speciality_id;
	}
	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(ArrayList<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

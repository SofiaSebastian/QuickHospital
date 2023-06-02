package quickhospital.pojos;

import java.util.ArrayList;

public class Patient {
	private static final long serialVersionUID = -434234724257282729L;
	//Atributes
	private Integer id;
	private String name;
	private Integer speciality_id;
	private ArrayList<Symptom> symptoms;
	private String email;
	
	//Empty constructor
	public Patient() {
		super();
		this.symptoms=new ArrayList<>();
	}
	public Patient(Integer id,String name, String email) {
		this.id= id;
		this.name= name; 
		this.symptoms=new ArrayList<>();
		this.email = email;
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
	
	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
	
	public void setSymptoms(ArrayList<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
	
	
	public Integer getSpeciality_id() {
		return speciality_id;
	}
	public void setSpeciality_id(Integer speciality_id) {
		this.speciality_id = speciality_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}
	

}

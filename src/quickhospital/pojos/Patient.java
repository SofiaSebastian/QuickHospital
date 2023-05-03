package quickhospital.pojos;

import java.util.ArrayList;

public class Patient {
	private static final long serialVersionUID = -434234724257282729L;
	//Atributes
	private Integer id;
	private String name;
	private Integer speciality_id;
	private ArrayList<Symptoms> symptoms;
	
	//Empty constructor
	public Patient() {
		super();
		this.symptoms=new ArrayList<>();
	}

	//Constructors using Fields
	public Patient(Integer id, String name, Integer speciality_id, ArrayList<Symptoms> symptoms) {
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
	
	

}

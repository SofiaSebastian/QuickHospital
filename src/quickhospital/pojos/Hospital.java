package quickhospital.pojos;

import java.util.ArrayList;

public class Hospital {
	private static final long serialVersionUID = -1156840724257282729L;
	
	//Atributes
	private Integer id;
	private String name;
	private Integer capacity;
	private String location;
	private ArrayList<Speciality> specialities;
	
	
	//Empty constructor
	
	public Hospital() {
		super();
		this.specialities = new ArrayList<>();
	}

	//Constructors using Fields
	
	public Hospital(Integer id, String name, Integer capacity, String location) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.location = location;
		this.specialities = new ArrayList<>();
	}


	public Hospital(Integer id, String name, Integer capacity, String location, ArrayList<Speciality> specialities) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.location = location;
		this.specialities = specialities;	
	}
	
	//Getters and Setters
	
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


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public ArrayList<Speciality> getSpecialities() {
		return specialities;
	}


	public void setSpecialities(ArrayList<Speciality> specialities) {
		this.specialities = specialities;
	}
	
	public int specialityIdtoPosition(int id){
        for(int i = 0; i < specialities.size(); i++){
            if(specialities.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
	
	public void addSpeciality(Speciality sp) {
		this.specialities.add(sp);
	}

	@Override
	public String toString() {
		return  id + ". " + name + "( Capacity: " + capacity + ", Location: " + location + ")";
	}
	
		
}

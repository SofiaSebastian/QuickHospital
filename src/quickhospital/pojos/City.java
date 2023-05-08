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
	
	public ArrayList<Hospital> getHospitals() {
		return hospitals;
	}
	
	public void setHospitals(ArrayList<Hospital> hospitals) {
		this.hospitals = hospitals;
	}
	
	public void addHospital(Integer id, String name, Integer capacity, String location, Integer administrator) {
        Hospital h = new Hospital(id, name, capacity, location, administrator);           
        hospitals.add(h);
    } 
	
	public void deleteHospital(int id){
        hospitals.remove(id-1);
    }
	
	public void showHospitals() {
		for(int i = 0; i < hospitals.size(); i++){
            System.out.println((i + 1) + " " +hospitals.get(i));
        }
	}
	
	public void addSpecialitytoHospital(Speciality sp, int id){          
        hospitals.get(id-1).addSpeciality(sp);
	}
	
	public void showSpecialityfromHospital(int id) {
		hospitals.get(id-1).showSpeciality();
	}
	
	public void deleteSpecialityfromHospital(int id, int id2){
		hospitals.get(id-1).deleteSpeciality(id2-1);
    }
}

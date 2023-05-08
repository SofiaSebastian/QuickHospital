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
	
	public void addSpeciality(Speciality sp, int id){          
        hospitals.get(id-1).getSpecialities().add(sp);
	}
	
	public void showSpecialities(int id) {
		for(int i = 0; i < hospitals.get(id-1).getSpecialities().size(); i++){
			System.out.println((i + 1) + " " + hospitals.get(id-1).getSpecialities().get(i));
		}
	}
	
	public void deleteSpeciality(int id, int id2){
		hospitals.get(id-1).getSpecialities().remove(id2-2);
    }
	
	public void addSymptom(int id, int id2, Symptom sym){
		hospitals.get(id-1).getSpecialities().get(id2-1).getSymptoms().add(sym);
    }
	
	public void deleteSymptom(int id, int id2, int id3){
		hospitals.get(id-1).getSpecialities().get(id2-1).getSymptoms().remove(id3-1);
    }
	
	public void showSymptoms(int id, int id2) {
		for(int i = 0; i < hospitals.get(id-1).getSpecialities().get(id2-1).getSymptoms().size(); i++){
			System.out.println((i + 1) + " " + hospitals.get(id-1).getSpecialities().get(id2-1).getSymptoms().get(i));
		}
	}
}

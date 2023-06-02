package quickhospital.ifaces;

import java.util.ArrayList;

import quickhospital.pojos.Patient;

public interface DBPatientManager {
	ArrayList<Patient> readPatients();
	void addPatient(String name, String email);
	int getId(String name);
	Integer emailToId(String email);
}

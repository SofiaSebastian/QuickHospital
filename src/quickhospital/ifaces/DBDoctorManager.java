package quickhospital.ifaces;

import java.util.ArrayList;

import quickhospital.pojos.Doctor;

public interface DBDoctorManager {
	ArrayList<Doctor> readDoctors();
	void newDoctor(String name, int hospitalid, int specialityid);
	int getId(String name);
	
}

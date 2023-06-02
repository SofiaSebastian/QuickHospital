package quickhospital.ifaces;

import java.util.ArrayList;

import quickhospital.pojos.Hospital;

public interface DBHospitalManager {
	ArrayList<Hospital> readHospitalDB();
	void readHospSpec();
	int getId(String name);
	void addHospital(Hospital h);
	void deleteHospital(int i);
	void addSpecialityToHospital(int h, int sp);
	void deleteSpeciality(int h, int sp);
}

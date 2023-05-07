package quickhospital.ifaces;


import java.util.*;

import quickhospital.pojos.*;


public interface DBManager {
	ArrayList<Symptom> getSymptoms(); 
	void showSymptoms(ArrayList<Symptom> symp);
	void showHospitals(String sp);
	String compareSymptoms(ArrayList<Integer> nums);
}


package quickhospital.ifaces;

import java.util.ArrayList;

import quickhospital.pojos.Symptom;

public interface DBSymptomsManager {
	ArrayList<Symptom> readSymptoms();
	int getId(String name);
}

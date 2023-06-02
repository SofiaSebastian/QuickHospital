package quickhospital.ifaces;
import java.util.*;
import quickhospital.pojos.*;
public interface DBSpecialityManager {
	ArrayList<Speciality> readSpecialities();
	void readSpecSymp();
	int getId(String name);
	void addSpeciality(Speciality sp);
}

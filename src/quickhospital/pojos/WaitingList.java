package quickhospital.pojos;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WaitingList {
	//Atributes
	private Integer id;
	private ArrayList<LocalDateTime>  appointment;
	private ArrayList<Patient> patients; 
	private boolean status;
	
	//Constructors
	public WaitingList() {
		super();
		this.appointment= new ArrayList<>();
		this.patients= new ArrayList<>();
	}

	public WaitingList(Integer id, ArrayList<LocalDateTime> appointment, ArrayList<Patient> patients, boolean status) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.patients = patients;
		this.status = status;
	}

	public WaitingList(Integer id, boolean status) {
		super();
		this.id = id;
		this.status = status;
		this.appointment= new ArrayList<>();
		this.patients= new ArrayList<>();
	}
	//Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<LocalDateTime> getAppointment() {
		return appointment;
	}

	public void setAppointment(ArrayList<LocalDateTime> appointment) {
		this.appointment = appointment;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}

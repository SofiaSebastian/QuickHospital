package quickhospital.pojos;
import java.time.*;
import java.util.*;
import java.sql.*;


public class WaitingList {
	private static final long serialVersionUID = -980840724257282729L;
	//Atributes
	private LocalDate date;
	private ArrayList<LocalTime> time;
	private ArrayList<Patient> patients; 
	private Integer hosp_Id;
	private Integer sp_Id;
	
	
	
	public Integer getHosp_Id() {
		return hosp_Id;
	}

	public void setHosp_Id(Integer hosp_Id) {
		this.hosp_Id = hosp_Id;
	}

	public Integer getSp_Id() {
		return sp_Id;
	}

	public void setSp_Id(Integer sp_Id) {
		this.sp_Id = sp_Id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getDate() {
		return date;
	}

	//Constructors
	public WaitingList() {
		super();
		this.time= new ArrayList<>();
		this.patients= new ArrayList<>();
	}

	public WaitingList(ArrayList<LocalTime> time, ArrayList<Patient> patients) {
		super();
		this.time = time;
		this.patients = patients;
	}
	public WaitingList(LocalDate date, Integer hosp_id, Integer sp_id) {
		super();
		this.date=date;
		this.hosp_Id=hosp_id;
		this.sp_Id=sp_id;
		this.time= new ArrayList<>(); //poner time.get(0) = 8:00
		this.patients= new ArrayList<>();
	}

	
	public WaitingList(LocalDate date,ArrayList<LocalTime> time, ArrayList<Patient> patients, Integer hosp_Id, Integer sp_Id) {
		super();
		this.date=date;
		this.time = time; 
		this.patients = patients;
		this.hosp_Id = hosp_Id;
		this.sp_Id = sp_Id;
	}
	public void addPatient(Patient p) {
		this.patients.add(p);
	}
	
	public void addTime(LocalTime t) {
		this.time.add(t);
	}

	//Getters and Setters

	public ArrayList<LocalTime> getTime() {
		return this.time;
	}

	public void setTime(ArrayList<LocalTime> time) {
		this.time = time;
	}

	public ArrayList<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	} 
	
}

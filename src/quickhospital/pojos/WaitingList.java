package quickhospital.pojos;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;


public class WaitingList {
	private static final long serialVersionUID = -980840724257282729L;
	//Atributes
	private LocalDate date;
	private LocalTime time;
	private Patient patient; 
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
		this.patient = null;
		this.date = LocalDate.now().plusDays(1);
		this.time = null;
	}

	public WaitingList(LocalTime time, Patient patient) {
		super();
		this.time = time;
		this.patient = patient;
		this.date = LocalDate.now().plusDays(1);
	}
	
	public WaitingList(Integer hosp_id, Integer sp_id) {
		super();
		this.date = LocalDate.now().plusDays(1);
		this.hosp_Id=hosp_id;
		this.sp_Id=sp_id;
		this.time= null; //poner time.get(0) = 8:00
		this.patient = null;
	}

	
	public WaitingList(LocalDate date, LocalTime time, Patient patient, Integer hosp_Id, Integer sp_Id) {
		super();
		this.date = LocalDate.now().plusDays(1);
		this.time = time; 
		this.patient = patient;
		this.hosp_Id = hosp_Id;
		this.sp_Id = sp_Id;
	}
	

	//Getters and Setters

	public LocalTime getTime() {
		return this.time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Patient getPatients() {
		return this.patient;
	}

	public void setPatients(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "WaitingList [date=" + date + ", time=" + time + ", patient=" + patient + ", hosp_Id=" + hosp_Id
				+ ", sp_Id=" + sp_Id + "]";
	} 
	
	
}

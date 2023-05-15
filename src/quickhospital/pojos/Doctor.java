package quickhospital.pojos;

import java.sql.Time;
import java.time.LocalDateTime;

 

public class Doctor {
	private static final long serialVersionUID = -115684524257282729L;
	//Atributes
	private Integer id;
	private String name;
	private Time arrivalTime;
	private Time departureTime;
	
	
	//Constructors
	public Doctor() {
		super();
	}
	
	public Doctor(Integer id, String name, Time arrivalTime, Time departureTime) {
		super();
		this.id = id;
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}
	
	//Getters and Setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

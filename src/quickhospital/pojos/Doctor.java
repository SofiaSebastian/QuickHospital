package quickhospital.pojos;

import java.time.LocalDateTime;

 

public class Doctor {
	private static final long serialVersionUID = -115684524257282729L;
	//Atributes
	private Integer id;
	private String name;
	private LocalDateTime arrivalTime;
	private LocalDateTime departureTime;
	
	
	//Constructors
	public Doctor() {
		super();
	}
	public Doctor(Integer id, String name, LocalDateTime arrivalTime, LocalDateTime departureTime) {
		super();
		this.id = id;
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}
	
	public Doctor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

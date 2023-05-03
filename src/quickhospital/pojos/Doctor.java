package quickhospital.pojos;

import java.time.LocalDateTime;

 

public class Doctor {
	private static final long serialVersionUID = -115684524257282729L;
	//Atributes
	private Integer id;
	private String name;
	private LocalDateTime arrivalTime;
	private LocalDateTime departureTime;
	
	
	//
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
	
	
	
	

}

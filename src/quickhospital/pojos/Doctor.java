package quickhospital.pojos;

import java.sql.Time;
import java.time.LocalDateTime;

 

public class Doctor {
	private static final long serialVersionUID = -115684524257282729L;
	//Atributes
	private Integer id;
	private String name;
	private Integer speciality_id;
	private Integer hosp_id;
	

	
	//Constructors
	public Doctor() {
		super();
	}
	public Doctor(Integer id, String name, Integer sp, Integer hosp) {
		super();
		this.id = id;
		this.name = name;
		this.hosp = hosp;
		this.sp = sp;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

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
	private String email;

	
	//Constructors
	public Doctor() {
		super();
	}
	public Doctor(Integer id, String name, Integer sp, Integer hosp, String email) {
		super();
		this.id = id;
		this.name = name;
		this.hosp_id = hosp;
		this.speciality_id = sp;
		this.email = email;
	}
		
	public Doctor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Doctor(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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
	
	public Integer getSpeciality_id() {
		return speciality_id;
	}
	public void setSpeciality_id(Integer speciality_id) {
		this.speciality_id = speciality_id;
	}
	public Integer getHosp_id() {
		return hosp_id;
	}
	public void setHosp_id(Integer hosp_id) {
		this.hosp_id = hosp_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

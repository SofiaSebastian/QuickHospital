package quickhospital.pojos;

public class Symptoms {
	private static final long serialVersionUID = -76493724257282729L;
	//Atributes
	private Integer id;
	private String name;
	
	//Empty constructor
	
	public Symptoms() {
		super();
	}
	
	//Constructor using Fields
	public Symptoms(Integer id, String name) {
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
	
	
}

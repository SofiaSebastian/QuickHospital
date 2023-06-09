package quickhospital.pojos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "roles")
public class Role implements Serializable{

	
	private static final long serialVersionUID = 7254010412114428251L;
	/**
	 * 
	 */
	
	
	
	@Id
	@GeneratedValue(generator = "roles")
	@TableGenerator(name = "roles", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "roles")
	private Integer id;
	private String name;
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;
	
	
	
	
	
	public Role() {
		super();
		this.users= new ArrayList<User>();
	}
	
	
	
	public Role( String name) {
		super();
		this.users= new ArrayList<User>();
		this.name = name;
	}


	public void addUSer(User u) {
		if(!users.contains(u)) {
			users.add(u);
		}
	}
	
	public void removeUser(User u) {
		
		if(users.contains(u))
		{
			users.remove(u);
		}
		
	}

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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	
}

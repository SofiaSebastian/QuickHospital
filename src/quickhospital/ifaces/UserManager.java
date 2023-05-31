package quickhospital.ifaces;

import java.util.List;

import quickhospital.pojos.Role;
import quickhospital.pojos.User;

public interface UserManager {

	
	public void disconnect();
	public void newUser(User u);
	public void newRole(Role r);
	
	public Role getRole(Integer id);
	
	public List<Role> getRoles();
	
	public User checkPassword(String email, String passwd);
}

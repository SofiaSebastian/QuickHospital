package quickhospital.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import quickhospital.ifaces.UserManager;
import quickhospital.pojos.*;


public class JPAUserManager implements UserManager {

	private EntityManager em;
	
	
	public JPAUserManager() {
		this.connect();
	}
	
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("quickhospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		if( this.getRoles().isEmpty()) {
			Role patient = new Role("patient");
			Role doctor = new Role("doctor");
			this.newRole(patient);
			this.newRole(doctor);
		}
		
	}
	
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		
		return roles;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("Select * from users where email =? AND password = ?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2,digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}
		
		return u;
	}

}
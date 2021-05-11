package com.insurance.quote.dao;


import javax.persistence.EntityManager;
import com.insurance.quote.entities.UserCreation;
public class UserDAOImpl implements UserDao {
	private EntityManager entityManager;

	public UserDAOImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	
	@Override
	public UserCreation findName(UserCreation user) {  //Retrieves data based on name from Userrole table
		String name = user.getUserName();
		UserCreation uc1 = entityManager.find(UserCreation.class, name);
		return uc1;
		}

	public String profileCreation(UserCreation user) {	//Persists the data of user creation in to the user table
		String message = null;
		entityManager.persist(user);
		if(entityManager.contains(user)) {
			message="User Created Sucessfully ";}
		return message;
			}
							
		@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	@Override
	public void commitTransaction(){
		entityManager.getTransaction().commit();
	}
	
	@Override
	public void closeResources() {
		entityManager.close();
		}
}
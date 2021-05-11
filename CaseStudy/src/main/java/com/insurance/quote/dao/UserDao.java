package com.insurance.quote.dao;

import com.insurance.quote.entities.UserCreation;

public interface UserDao {

	public void beginTransaction();
	public void commitTransaction();
	public UserCreation findName(UserCreation user);
	public void closeResources();
}

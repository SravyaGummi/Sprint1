package com.insurance.quote.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userrole")
public class UserCreation implements Serializable {
		
		private static final long serialVersionUID = 1L;
		@Id
		private String userName;
		private String password;
		private String roleCode;
		
		public UserCreation() {}
		public UserCreation(String userName, String password) {
		this.userName = userName;
		this.password = password;
		}
		public UserCreation(String result) {
			this.roleCode=result;
		}
		public UserCreation(String userName, String password, String rolecode) {
			// TODO Auto-generated constructor stub
			this.userName=userName;
			this.password=password;
			this.roleCode=rolecode;
		}
	
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRoleCode() {
			return roleCode;
		}
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}
		@Override
		public String toString() {
			return "[userName=" + userName + ", password=" + password + ", roleCode=" + roleCode + "]";
		}
}

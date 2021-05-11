package com.insurance.quote.exception;


public class UserNameException extends Exception{
	public UserNameException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}

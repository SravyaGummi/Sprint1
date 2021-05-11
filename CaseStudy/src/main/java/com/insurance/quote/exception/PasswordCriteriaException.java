package com.insurance.quote.exception;

public class PasswordCriteriaException extends Exception{
	public PasswordCriteriaException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}
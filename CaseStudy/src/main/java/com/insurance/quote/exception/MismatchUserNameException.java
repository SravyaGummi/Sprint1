package com.insurance.quote.exception;

public class MismatchUserNameException extends Exception{
	public MismatchUserNameException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}
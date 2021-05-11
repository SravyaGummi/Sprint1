package com.insurance.quote.exception;

public class NameException extends Exception{
	public NameException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}
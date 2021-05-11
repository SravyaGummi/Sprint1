package com.insurance.quote.exception;


public class NoUserFoundException extends Exception{
	public NoUserFoundException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}
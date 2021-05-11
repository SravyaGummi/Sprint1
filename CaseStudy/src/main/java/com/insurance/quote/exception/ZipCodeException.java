package com.insurance.quote.exception;

public class ZipCodeException extends Exception{
	public ZipCodeException(String msg){
		   super(msg);
		   System.out.println(msg);
		}
	}

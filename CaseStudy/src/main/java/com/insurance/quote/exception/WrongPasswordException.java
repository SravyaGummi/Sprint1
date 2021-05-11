package com.insurance.quote.exception;

public class WrongPasswordException extends Exception{
		public WrongPasswordException(String msg){
			   super(msg);
			   System.out.println(msg);
			}
		}

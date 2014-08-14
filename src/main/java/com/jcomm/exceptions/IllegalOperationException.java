/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.exceptions;

/**
 *
 * @author jova
 */
public class IllegalOperationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalOperationException() {
    }
    
    public IllegalOperationException(String message) {
    	super(message);
    }
}

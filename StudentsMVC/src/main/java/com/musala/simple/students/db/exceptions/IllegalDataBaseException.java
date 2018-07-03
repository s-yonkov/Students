package com.musala.simple.students.db.exceptions;

public class IllegalDataBaseException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7264067652167224762L;

    public IllegalDataBaseException() {
    }

    public IllegalDataBaseException(String message) {
        super(message);
    }

    public IllegalDataBaseException(String message, Exception ex) {
        super(message, ex);
    }
}

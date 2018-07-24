package com.musala.simple.students.db.exceptions;

public class MySQLConnectionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -7924411416486681724L;

    public MySQLConnectionException() {
    }

    public MySQLConnectionException(String message) {
        super(message);
    }

    public MySQLConnectionException(String message, Exception ex) {
        super(message, ex);
    }

}

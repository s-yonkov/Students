package com.musala.simple.students.db.exceptions;

public class MongoConnectionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5364041460126831938L;

    public MongoConnectionException() {
    }

    public MongoConnectionException(String message) {
        super(message);
    }

    public MongoConnectionException(String message, Exception ex) {
        super(message, ex);
    }

}

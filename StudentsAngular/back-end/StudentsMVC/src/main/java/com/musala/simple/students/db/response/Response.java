package com.musala.simple.students.db.response;

import java.util.ArrayList;

/**
 * 
 * Class for wrapping all responses from the databases
 *
 */
public class Response {

    private ArrayList<DatabaseResponse> dbResponses = new ArrayList<>();

    public ArrayList<DatabaseResponse> getDbResponses() {
        return dbResponses;
    }

    public void setDbResponses(ArrayList<DatabaseResponse> dbResponses) {
        this.dbResponses = dbResponses;
    }

    public void addDbResponse(DatabaseResponse dbResponse) {
        this.dbResponses.add(dbResponse);
    }
}

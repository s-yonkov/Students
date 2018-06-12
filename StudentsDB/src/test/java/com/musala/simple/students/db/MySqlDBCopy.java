package com.musala.simple.students.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.impl.MySqlDB;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

public class MySqlDBCopy extends MySqlDB {

	public MySqlDBCopy() throws SQLConnectionException {
		super();
	}

	public ResultSet getResultset() {
		return this.rs;
	}

	@Override
	protected Connection createConnection() throws SQLConnectionException {

		String dbName = "mariaDB4jTest";
		DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
		config.setPort(0);
		DB db;

		try {
			db = DB.newEmbeddedDB(config.build());
			db.start();
			db.createDB(dbName);

			this.connection = DriverManager.getConnection(config.getURL(dbName), "root", "");
		} catch (ManagedProcessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
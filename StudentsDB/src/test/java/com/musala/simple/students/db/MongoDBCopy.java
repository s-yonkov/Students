package com.musala.simple.students.db;

import java.net.InetSocketAddress;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.musala.simple.students.db.impl.MongoDB;
import com.musala.simple.students.db.mappers.MongoObjectMapper;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class MongoDBCopy extends MongoDB {

	private MongoServer server;

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public void setDatabase(DB database) {
		this.database = database;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}

	public DBCollection getCollection() {
		return this.collection;
	}

	@Override
	protected void CreateDB() {
		this.server = new MongoServer(new MemoryBackend());
		InetSocketAddress serverAddress = server.bind();
		this.setMongoClient(new MongoClient(new ServerAddress(serverAddress)));
		this.setDatabase(mongoClient.getDB("testdb"));
		this.setCollection(database.getCollection("testcollection"));
		mapper = new MongoObjectMapper();
	}

}

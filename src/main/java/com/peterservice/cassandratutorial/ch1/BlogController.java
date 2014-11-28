package com.peterservice.cassandratutorial.ch1;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

public class BlogController {

	private Cluster cluster;
	private Session session;
	private SchemaDAO schema;

	public BlogController(String connectString) {
		
		cluster = Cluster.builder().addContactPoint(connectString).build();		
		session = cluster.connect();

		schema = new SchemaDAO(session);
		schema.createSchema();
	}

	public void disconnect() {
		session.close();
		cluster.close();
		System.out.println("Cassandra: disconnected");
	}

	public static void main(String[] args) {
		
		BlogController controller;

		if (args.length == 0) {
			controller = new BlogController("srv2-itsd03");
		} else {
			controller = new BlogController(args[0]);
		}
		
		controller.disconnect();
	}
}

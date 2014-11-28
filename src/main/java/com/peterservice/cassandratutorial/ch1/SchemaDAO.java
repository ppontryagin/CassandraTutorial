package com.peterservice.cassandratutorial.ch1;

import com.datastax.driver.core.Session;

public class SchemaDAO {

	private Session session;

	public SchemaDAO(Session session) {
		this.session = session;
	}	
	
	public void createSchema() {
	    session.execute("CREATE KEYSPACE IF NOT EXISTS DATA3 WITH replication "
	            + "= {'class':'SimpleStrategy', 'replication_factor':1};");	    
	    	    
		session.execute("CREATE TABLE IF NOT EXISTS DATA3.songs (id uuid PRIMARY KEY,"
				+ "title text, album text, artist text,"
				+ "tags set<text>, data blob);");
		
		session.execute("CREATE TABLE IF NOT EXISTS DATA3.playlists (" + "id uuid,"
				+ "title text," + "album text, " + "artist text,"
				+ "song_id uuid," + "PRIMARY KEY (id, title, album, artist)"
				+ ");");
		
		session.execute("CREATE TABLE IF NOT EXISTS DATA3.docs (" + "id uuid,"
				+ "name text," + "value text," + "PRIMARY KEY (id));");	    
	}
	
}

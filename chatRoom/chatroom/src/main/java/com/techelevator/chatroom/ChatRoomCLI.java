package com.techelevator.chatroom;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class ChatRoomCLI {

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/chatroom");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
	}
	
	public ChatRoomCLI(DataSource datasource) {
		
	}
	
	public void run() {
		
	}
	

}

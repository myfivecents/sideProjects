package com.techelevator.chatroom;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCChatRoomDAO implements ChatRoomDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCChatRoomDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<ChatRoom> getAllChatRooms() {
		List<ChatRoom> chatList = new ArrayList<ChatRoom>();
		String sqlQuery = "SELECT * " + "FROM chat_room";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery);
		while (rowSet.next()) {
			ChatRoom chatRoom = makeChatRoomFromRowSet(rowSet);
			chatList.add(chatRoom);

		}
		return null;
	}
	
	public ChatRoom createChatRoom(String chatName) {
		String sqlChatRoomId = "SELECT nextval(pg_get_serial_sequence('chat_room', 'id')";
		SqlRowSet nextChatRoomId = jdbcTemplate.queryForRowSet(sqlChatRoomId);
		nextChatRoomId.next();
		int chatRoomId = nextChatRoomId.getInt(1);

		String sqlUpdate = "INSERT INTO chat_room (id, chat_name) VALUES (?, ?)";
		jdbcTemplate.update(sqlUpdate, chatRoomId, chatName);

		ChatRoom returnChatRoom = new ChatRoom();
		returnChatRoom.setChat_id(chatRoomId);
		returnChatRoom.setChatName(chatName);
		return returnChatRoom;
	}

	public ChatRoom getChatRoomById(int id) {
		String sqlQuery = "SELECT * " + "FROM chat_room " + "WHERE id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, id);
		
		rowSet.next();
		ChatRoom chatRoom = makeChatRoomFromRowSet(rowSet);
		return chatRoom;
	}
	
	public boolean deleteChatRoomById(int id) {
		String sqlDeleteChatRoom = "DELETE FROM chat_room " +
								   "WHERE id = ?";
		jdbcTemplate.update(sqlDeleteChatRoom, id);
		return false;
	}

	private ChatRoom makeChatRoomFromRowSet(SqlRowSet rowSet) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setChat_id(rowSet.getInt("id"));
		chatRoom.setChatName(rowSet.getString("chat_name"));
		return null;
	}

	

}

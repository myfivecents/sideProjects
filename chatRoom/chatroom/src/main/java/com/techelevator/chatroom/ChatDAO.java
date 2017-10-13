package com.techelevator.chatroom;

import java.util.List;

public interface ChatDAO {
	public List<Chat> getAllChatRooms();
	public void createChatRoom();
	public String getChatNameById();
}

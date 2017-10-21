package com.techelevator.chatroom;

import java.util.List;

public interface ChatRoomDAO {
	public List<ChatRoom> getAllChatRooms();
	public ChatRoom createChatRoom(String chatName);
	public ChatRoom getChatRoomById(int id);
	public boolean deleteChatRoomById(int id);
}

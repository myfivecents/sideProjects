package com.techelevator.chatroom;

import java.util.List;

public interface ChatViewDAO {
	public List<ChatView> getAllChats();
	public boolean addAccountToChat();
	public void removeAccountFromChat();
	
}

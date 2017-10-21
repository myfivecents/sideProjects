package com.techelevator.chatroom;

import java.util.List;

public interface ChatViewDAO {
	public List<ChatView> getAllChats();
	public ChatView addAccountToChat(int accountId, int chatId);
	public boolean removeAccountFromChat(int Accountid);
	
}

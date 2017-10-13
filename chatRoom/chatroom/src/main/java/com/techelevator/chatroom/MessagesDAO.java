package com.techelevator.chatroom;

import java.util.List;

public interface MessagesDAO {
	public List<Messages> getAllMessages();
	public List<Messages> getAllMessagesByUser();
	public List<Messages> getAllMessagesInChat();
	public List<Messages> getMessagesByDate();
	public void newMessage();
	public void removeMessage();
}

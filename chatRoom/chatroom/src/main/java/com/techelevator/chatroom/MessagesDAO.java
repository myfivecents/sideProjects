package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.List;

public interface MessagesDAO {
	public List<Messages> getAllMessages();
	public List<Messages> getAllMessagesByUser(int userId);
	public List<Messages> getAllMessagesInChat(int chatId);
	public List<Messages> messagesBetweenDates(LocalDate fromDate, LocalDate toDate);
	public List<Messages> messagesSinceDate(LocalDate sinceDate);
	public Messages newMessage(LocalDate dateTime, int chatId, int accountId, String messageBody);
	public Messages removeMessageByUserid();
}

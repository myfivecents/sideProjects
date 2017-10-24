package com.techelevator.chatroom;

import java.time.LocalDate;

public class Messages {
	private int messageId;
	private LocalDate dateTime;
	private int chatId;
	private int accountId;
	private String messageBody;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public LocalDate getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chat_id) {
		this.chatId = chat_id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int account_id) {
		this.accountId = account_id;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
}

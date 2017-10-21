package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.List;

public interface BlogPostDAO {
	public BlogPost createNewBlogPost(int accountId, String title, String body, LocalDate date, String notes);
	public List<BlogPost> allBlogPosts();
	public List<BlogPost> blogPostsBetweenDates(LocalDate fromDate, LocalDate toDate);
	public List<BlogPost> blogPostSinceDate(LocalDate sinceDate);
	public boolean deleteBlogPostById(int id);
}

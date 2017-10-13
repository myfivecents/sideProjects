package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.List;

public interface BlogPostDAO {
	public void createNewBlogPost();
	public List<BlogPost> allBlogPosts();
	public List<BlogPost> blogPostsBetweenDates(LocalDate fromDate, LocalDate toDate);
	public List<BlogPost> blogPostSinceDAte(LocalDate sinceDate);
	public void deleteBlogPost();
}

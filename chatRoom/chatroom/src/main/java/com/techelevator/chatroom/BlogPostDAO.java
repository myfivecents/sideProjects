package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.List;

public interface BlogPostDAO {
	public BlogPost createNewBlogPost();
	public List<BlogPost> allBlogPosts();
	public List<BlogPost> blogPostsBetweenDates(LocalDate fromDate, LocalDate toDate);
	public List<BlogPost> blogPostSinceDAte(LocalDate sinceDate);
	public boolean deleteBlogPost();
}

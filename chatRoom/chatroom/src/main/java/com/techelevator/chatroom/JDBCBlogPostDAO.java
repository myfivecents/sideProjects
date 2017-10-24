package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JDBCBlogPostDAO implements BlogPostDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBlogPostDAO(DataSource dataSource) {
		jdbcTemplate = new  JdbcTemplate(dataSource);
	}

	public BlogPost createNewBlogPost(int accountId, String title, String body, LocalDate date, String notes) {
		String sqlBlogId = "SELECT nextval(pg_get_serial_sequence('blog_post', 'id'))";
		
		SqlRowSet nextBlogId = jdbcTemplate.queryForRowSet(sqlBlogId);
		nextBlogId.next();
		int blogId = nextBlogId.getInt(1);
		
		String sqlUpdate = "INSERT INTO blog_post (id, account_id, title, body, date_time, notes) VALUES (?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlUpdate, blogId, accountId, title, body, date, notes);
		
		BlogPost post = new BlogPost();
		post.setPostId(blogId);
		post.setAccountId(accountId);
		post.setPostTitle(title);
		post.setPostBody(body);
		post.setDateTime(date);
		post.setNotes(notes);
		return post;
	}

	public List<BlogPost> allBlogPosts() {
		List<BlogPost> blogList = new ArrayList<BlogPost>();
		String sqlQuery = "SELECT * " +
						  "FROM blog_post";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery);
		while (rowSet.next()) {
			BlogPost post = makeBlogPostFromRowSet(rowSet);
			blogList.add(post);
		}
		return blogList;
	}

	public List<BlogPost> blogPostsBetweenDates(LocalDate fromDate, LocalDate toDate) {
		List<BlogPost> blogList = new ArrayList<BlogPost>();
		String sqlSites = "SELECT * " +
						  "FROM blog_post " +
						  "WHERE date_time >= ? AND date_time <= ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSites, fromDate, toDate);
		while(rowSet.next()) {
			BlogPost post = makeBlogPostFromRowSet(rowSet);
			blogList.add(post);
		}
		return blogList;
	}

	public List<BlogPost> blogPostSinceDate(LocalDate sinceDate) {
		List<BlogPost> blogList = new ArrayList<BlogPost>();
		String sqlSites = "SELECT * " +
						  "FROM blog_post " +
						  "WHERE date_time >= ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSites, sinceDate);
		while(rowSet.next()) {
			BlogPost post = makeBlogPostFromRowSet(rowSet);
			blogList.add(post);
		}
		return blogList;
	}

	public boolean deleteBlogPostById(int id) {
		String sqlRemovePost = "REMOVE FROM blog_post " +
							   "WHERE id = ?";
		int updatedRows = jdbcTemplate.update(sqlRemovePost, id);
		if (updatedRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	private BlogPost makeBlogPostFromRowSet(SqlRowSet results) {
		BlogPost post = new BlogPost();
		post.setAccountId(results.getInt("account_id"));
		post.setPostId(results.getInt("id"));
		post.setPostTitle(results.getString("title"));
		post.setPostBody(results.getString("body"));
		post.setDateTime(results.getDate("date_time").toLocalDate());
		post.setNotes(results.getString("notes"));
		return post;
	}

}

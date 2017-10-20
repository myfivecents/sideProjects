package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCBlogPostDAO implements BlogPostDAO {
	private JdbcTemplate jdbcTemplate;
	
	public JDBCBlogPostDAO(DataSource dataSource) {
		jdbcTemplate = new  JdbcTemplate(dataSource);
	}

	public BlogPost createNewBlogPost() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<BlogPost> blogPostSinceDAte(LocalDate sinceDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteBlogPost() {
		// TODO Auto-generated method stub
		return false;
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

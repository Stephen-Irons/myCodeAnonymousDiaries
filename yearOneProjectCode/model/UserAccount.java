package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;private String userName;
	private String password;
	private boolean isLoggedIn;
	private ArrayList<String> likedStories = new ArrayList<>();
	private ArrayList<String> uploadedStoryIDs = new ArrayList<>();
	private ArrayList<String> commentStoryIDs = new ArrayList<>();

	public UserAccount(String name, String userName, String password, boolean isLoggedIn,
			ArrayList<String> likedStories, ArrayList<String> uploadedStoryIDs, ArrayList<String> commentStoryIDs) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
		this.likedStories = likedStories;
		this.uploadedStoryIDs = uploadedStoryIDs;
		this.commentStoryIDs = commentStoryIDs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<String> getLikedStories() {
		return likedStories;
	}

	public void setLikedStories(ArrayList<String> likedStories) {
		this.likedStories = likedStories;
	}

	public ArrayList<String> getUploadedStoryIDs() {
		return uploadedStoryIDs;
	}

	public void setUploadedStoryIDs(ArrayList<String> uploadedStoryIDs) {
		this.uploadedStoryIDs = uploadedStoryIDs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<String> getCommentStoryIDs() {
		return commentStoryIDs;
	}

	public void setCommentStoryIDs(ArrayList<String> commentStoryIDs) {
		this.commentStoryIDs = commentStoryIDs;
	}
}
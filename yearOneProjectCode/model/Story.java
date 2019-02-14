package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Story extends SmartSerializable {
	private static final long serialVersionUID = 1L;
	private String storyID;
	private String Title;
	private java.time.LocalDate datePublished;
	private String story;
	private int likes;
	ArrayList<String> commentIDs = new ArrayList<String>();
	
	//Constructor for creating a new story
	public Story(String storyID, String title, LocalDate datePublished, String story, int likes,
			ArrayList<String> commentIDs) {
		super();
		this.storyID = storyID;
		Title = title;
		this.datePublished = datePublished;
		this.story = story;
		this.likes = likes;
		this.commentIDs = commentIDs;
	}

	//getters and setters for retrieving and changing values of a story
	public String getStoryID() {
		return storyID;
	}

	public void setStoryID(String storyID) {
		this.storyID = storyID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public LocalDate getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public ArrayList<String> getCommentIDs() {
		return commentIDs;
	}

	public void setCommentIDs(ArrayList<String> commentIDs) {
		this.commentIDs = commentIDs;
	}
}

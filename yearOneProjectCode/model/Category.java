package model;

import java.util.ArrayList;

public class Category extends SmartSerializable
{
	private static final long serialVersionUID = 1L;
	private String categoryName;
	ArrayList<String> storyIDs = new ArrayList<String>();
	
	//constructor for a category
	public Category(String categoryName, ArrayList<String> storyIDs) {
		super();
		this.categoryName = categoryName;
		this.storyIDs = storyIDs;
	}

	//getters and setters to set and retrieve information about categories
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<String> getStoryIDs() {
		return storyIDs;
	}

	public void setStoryIDs(ArrayList<String> storyIDs) {
		this.storyIDs = storyIDs;
	}
}
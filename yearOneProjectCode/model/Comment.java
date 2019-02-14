package model;

public class Comment extends SmartSerializable {
	private static final long serialVersionUID = 1L;
	//variables that will contain data stored about the comment
	private String commentID;
	private String comment;

	//constructor for a comment
	public Comment(String commentID, String comment) {
		super();
		this.commentID = commentID;
		this.comment = comment;
	}

	//getters and setters to retrieve and set values for a comment
	public String getCommentID() {
		return commentID;
	}

	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.Document;

import org.h2.mvstore.MVMap;

import model.Category;
import model.Comment;
import model.Story;
import model.UserAccount;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class UploadStory extends DynamicWebPage {

	public UploadStory(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}

	public boolean process(WebRequest toProcess) {

		if (toProcess.path.equalsIgnoreCase("UploadStory.html")) {
			String userID = toProcess.params.get("userID");
			System.out.println("User ID at beginning of upload story form: " + userID);
			String stringToSendToWebBrowser = "";
			stringToSendToWebBrowser += "<!DOCTYPE html>\n";
			stringToSendToWebBrowser += "<html>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <head>\n";
			stringToSendToWebBrowser += "    <title>UploadStory</title>\n";
			stringToSendToWebBrowser += "    <meta charset=\"utf-8\">\n";
			stringToSendToWebBrowser += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <link href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "    <link href=\"http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "  </head>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <body>\n";
			stringToSendToWebBrowser += "    <div class=\"navbar navbar-default navbar-static-top\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"navbar-header\">\n";
			stringToSendToWebBrowser += "          <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navbar-ex-collapse\">\n";
			stringToSendToWebBrowser += "            <span class=\"sr-only\">Toggle navigation</span>\n";
			stringToSendToWebBrowser += "            <span class=\"icon-bar\"></span>\n";
			stringToSendToWebBrowser += "            <span class=\"icon-bar\"></span>\n";
			stringToSendToWebBrowser += "            <span class=\"icon-bar\"></span>\n";
			stringToSendToWebBrowser += "          </button>\n";
			stringToSendToWebBrowser += "          <a class=\"navbar-brand\" href=\"HomePage.html\"><span>Anonymous Diaries</span></a>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "        <div class=\"collapse navbar-collapse\" id=\"navbar-ex-collapse\">\n";
			stringToSendToWebBrowser += "          <ul class=\"nav navbar-nav navbar-right\">\n";
			stringToSendToWebBrowser += "            <li class=\"active\">\n";
			stringToSendToWebBrowser += "              <a href=\"HomePage.html?userID=" + userID + "\">Home</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
			stringToSendToWebBrowser += "            <li>\n";
			stringToSendToWebBrowser += "              <a href=\"UploadStory.html?userID=" + userID
					+ "\">Upload Story</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
			stringToSendToWebBrowser += "            <li>\n";
			stringToSendToWebBrowser += "              <a href=\"ContactUs.html?userID=" + userID
					+ "\">Contact us</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
			//changing the text in the log out button to suit the user that is signed in
			String logOutText = "";
			if (userID.equals("guest")) {
				logOutText = "Log in";
			} else {
				logOutText = "Log out";
			}
			stringToSendToWebBrowser += "            <li>\n";
			stringToSendToWebBrowser += "              <a href=\"LoginPage.html\">" + logOutText + "</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
			stringToSendToWebBrowser += "          </ul>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";

			// form for uploading a story
			{
				stringToSendToWebBrowser += "    <div class=\"section\">\n";
				stringToSendToWebBrowser += "      <div class=\"container\">\n";
				stringToSendToWebBrowser += "        <div class=\"row\">\n";
				stringToSendToWebBrowser += "          <div class=\"col-md-12\">\n";
				stringToSendToWebBrowser += "            <form name=\"myForm\" class=\"form-horizontal\" role=\"form\" \" action=\"/UploadStory.html/submit/UserStory\" method=\"GET\">\n";
				stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-2\">\n";
				stringToSendToWebBrowser += "                  <label for=\"inputTitle3\" class=\"control-label\">Story Category</label>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-10\">\n";
				// taking text input for category
				stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" id=\"categoryID\" placeholder=\"Category\" name=\"category\" required>\n";
				stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"userID\" value=\""
						+ userID + "\">\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "              </div>\n";
				stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-2\">\n";
				stringToSendToWebBrowser += "                  <label for=\"inputTitle3\" class=\"control-label\">Story Title</label>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-10\">\n";
				// taking text input for story title
				stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" id=\"inputTitle3\" placeholder=\"Title\" name=\"title\" required>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "              </div>\n";
				stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-2\">\n";
				stringToSendToWebBrowser += "                  <label for=\"inputStory3\" class=\"control-label\">Story</label>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-10\">\n";
				// taking text input for story
				stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" id=\"inputStory3\" placeholder=\"Story\" name=\"story\" required>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "              </div>\n";

				stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
				stringToSendToWebBrowser += "                <div class=\"col-sm-offset-2 col-sm-10\">\n";

				stringToSendToWebBrowser += "                  <button type=\"submit\" class=\"btn btn-default\">Upload Story</button>\n";
				stringToSendToWebBrowser += "                </div>\n";
				stringToSendToWebBrowser += "              </div>\n";

				stringToSendToWebBrowser += "            </form>\n";

				stringToSendToWebBrowser += "          </div>\n";
				stringToSendToWebBrowser += "        </div>\n";
				stringToSendToWebBrowser += "      </div>\n";
				stringToSendToWebBrowser += "    </div>\n";
			}

			stringToSendToWebBrowser += "    <div class=\"section\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += "          <div class=\"col-md-12\">\n";
			stringToSendToWebBrowser += "            <h1>My Stories ("+ userID +")</h1>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
			{
				stringToSendToWebBrowser += "    <div class=\"section\">\n";
				stringToSendToWebBrowser += "      <div class=\"container\">\n";
				stringToSendToWebBrowser += "        <div class=\"row\">\n";
				stringToSendToWebBrowser += "          <div class=\"col-md-9\">\n";
				//displaying the stories associated with the user logged in
				MVMap<String, Story> stories = db.s.openMap("Stories");
				List<String> storiesKeys = stories.keyList();

				MVMap<String, Comment> comments = db.s.openMap("Comments");
				List<String> commentsKeys = comments.keyList();
				if (!userID.equals("guest")) {

					MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
					UserAccount user = userAccounts.get(userID);

					ArrayList<String> displayStoryIDs = new ArrayList<>();

					displayStoryIDs = user.getUploadedStoryIDs();
					if (displayStoryIDs.size() == 0) {
						stringToSendToWebBrowser += "              <h1>You have no uploaded stories yet</h1>\n";
					} else {
						for (int j = 0; j < displayStoryIDs.size(); j++) {
							// displaying stories to the page
							for (int i = 0; i < storiesKeys.size(); i++) {
								String storyID = storiesKeys.get(i);
								Story story = stories.get(storyID);
								if (story.getStoryID().equals(displayStoryIDs.get(j))) {
									stringToSendToWebBrowser += "            <div class=\"col-md-12\" style=\"border:thin\">\n";
									stringToSendToWebBrowser += "              <h1>" + story.getTitle() + "</h1>\n";
									stringToSendToWebBrowser += "              <br>" + story.getDatePublished() + "\n";
									stringToSendToWebBrowser += "              <p>" + story.getStory() + "</p>\n";
									stringToSendToWebBrowser += "<form action=\"UploadStory.html/Submit/Like\" method=\"GET\">";
									stringToSendToWebBrowser += "              <br>Likes: " + story.getLikes() + "\n";
									String likeStoryID = story.getStoryID();
									stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"userID\" name=\"userID\" value=\""
											+ userID + "\">\n";

									stringToSendToWebBrowser += "<button name=\"storyID\" value= " + story.getStoryID()
											+ ">Like</button>  </div>";
									stringToSendToWebBrowser += " </form>";

									stringToSendToWebBrowser += "              <br>\n";
									stringToSendToWebBrowser += "              <br>\n";
									{
										stringToSendToWebBrowser += "            <form class=\"form-horizontal\" role=\"form\" action=\"/UploadStory.html/Submit/UserComment\" method=\"GET\">\n";

										stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
										stringToSendToWebBrowser += "                <div class=\"input-group\">\n";
										stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" name=\"comment\" placeholder=\"Type comment here\"\n";
										stringToSendToWebBrowser += "                  name=\"comment\" required>\n";
										// passing the storyID
										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"userID\" value=\""
												+ userID + "\">\n";
										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"storyID\" value=\""
												+ story.getStoryID() + "\">\n";
										stringToSendToWebBrowser += "                  <span class=\"input-group-btn\">\n";
										stringToSendToWebBrowser += "                  <button type=\"submit\" class=\"btn btn-default\">Add Comment</button>\n";
										stringToSendToWebBrowser += "                  </span>\n";
										stringToSendToWebBrowser += "                </div>\n";
										stringToSendToWebBrowser += "              </form>\n";
										stringToSendToWebBrowser += "              </div>\n";
									}
									// Displaying comments associated with the story
									{
										commentsKeys = comments.keyList();
										for (int x = 0; x < commentsKeys.size(); x++) {
											String commentID = commentsKeys.get(x);
											Comment comment = comments.get(commentID);
											if (story.getCommentIDs() != null) {
												for (int y = 0; y < story.getCommentIDs().size(); y++) {
													if (comment.getCommentID().equals(story.getCommentIDs().get(y)))
														stringToSendToWebBrowser += "              <p>"
																+ comment.getComment() + "</p>\n";
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					stringToSendToWebBrowser += "              <h1>Please log in to view your stories</h1>\n";
				}
				stringToSendToWebBrowser += "          </div>\n";
				stringToSendToWebBrowser += "        </div>\n";
				stringToSendToWebBrowser += "      </div>\n";
				stringToSendToWebBrowser += "    </div>\n";
			}
			stringToSendToWebBrowser += "    <footer class=\"section section-primary\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += "          <div class=\"col-sm-6\">\n";
			stringToSendToWebBrowser += "            <h1>Anonymous Diaries</h1>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "          <div class=\"col-sm-6\">\n";
			stringToSendToWebBrowser += "            <p class=\"text-info text-right\">\n";
			stringToSendToWebBrowser += "              <br>\n";
			stringToSendToWebBrowser += "              <br>\n";
			stringToSendToWebBrowser += "            </p>\n";
			stringToSendToWebBrowser += "            <div class=\"row\">\n";
			stringToSendToWebBrowser += "              <div class=\"col-md-12 hidden-lg hidden-md hidden-sm text-left\">\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-instagram text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-twitter text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-facebook text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-github text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "            </div>\n";
			stringToSendToWebBrowser += "            <div class=\"row\">\n";
			stringToSendToWebBrowser += "              <div class=\"col-md-12 hidden-xs text-right\">\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-instagram text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-twitter text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-facebook text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "                <a href=\"#\"><i class=\"fa fa-3x fa-fw fa-github text-inverse\"></i></a>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "            </div>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </footer>\n";
			stringToSendToWebBrowser += "  </body>\n";
			stringToSendToWebBrowser += "\n";
			stringToSendToWebBrowser += "</html>\n";
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
			return true;
		} else if (toProcess.path.equalsIgnoreCase("UploadStory.html/Submit/UserStory")) {
			String userID = toProcess.params.get("userID");

			if (!userID.equals("guest")) {
				System.out.println("shouldnt be here");
				ArrayList<String> commentIDs = new ArrayList<String>();
				commentIDs.add(null);

				// creating the new story
				String storyID = "story" + Long.toString(System.currentTimeMillis());
				Story tempStory = new Story(storyID, toProcess.params.get("title"), LocalDate.now(),
						toProcess.params.get("story"), 0, commentIDs);

				MVMap<String, Story> stories = db.s.openMap("Stories");

				stories.put(storyID, tempStory);
				db.commit();

				List<String> storiesKeys = stories.keyList();

				String category = toProcess.params.get("category").toLowerCase().trim();
				MVMap<String, Category> categories = db.s.openMap("Categories");
				List<String> categoriesKeys = categories.keyList();

				boolean categoryExists = false;
				Category searchCategory = null;
				for (int i = 0; i < categoriesKeys.size(); i++) {
					String categoryID = categoriesKeys.get(i);
					searchCategory = categories.get(categoryID);
					if (searchCategory.getCategoryName().toLowerCase().equals(category.toLowerCase())) {
						categoryExists = true;
						break;
					}
				}

				for (int j = 0; j < categoriesKeys.size(); j++) {
					String categoryID = categoriesKeys.get(j);
					Category allCategory = categories.get(categoryID);
					if (allCategory.getCategoryName().equals("All")) {
						ArrayList<String> newIDList = new ArrayList<>();
						newIDList = allCategory.getStoryIDs();
						newIDList.add(storyID);
						allCategory.setStoryIDs(newIDList);
						categories.put(allCategory.getCategoryName(), allCategory);
					}
				}

				if (category.toLowerCase().equals("all")) {
				} else {
					if (categoryExists) {
						ArrayList<String> newIDList = new ArrayList<>();
						newIDList = searchCategory.getStoryIDs();
						newIDList.add(storyID);
						searchCategory.setStoryIDs(newIDList);
						categories.put(searchCategory.getCategoryName(), searchCategory);
					} else {
						ArrayList<String> newList = new ArrayList<>();
						newList.add(storyID);
						Category newCategory = new Category(category.toLowerCase(), newList);
						categories.put(category, newCategory);
						db.commit();
					}
				}

				MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
				List<String> userAccountsKeys = userAccounts.keyList();
				UserAccount user = userAccounts.get(userID);
				ArrayList<String> userStoriesIDs = new ArrayList<>();
				userStoriesIDs = user.getUploadedStoryIDs();

				userStoriesIDs.add(storyID);
				for (int r = 0; r < userStoriesIDs.size(); r++) {
					if (userStoriesIDs.get(r) == null) {
						userStoriesIDs.remove(r);
					}
				}

				user.setUploadedStoryIDs(userStoriesIDs);
				userAccounts.put(userID, user);
				db.commit();
			}

			String url = "/UploadStory.html?userID=" + userID;
			toProcess.r = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
					"<html><body>Redirected: <a href=\"" + url + "\">" + url + "</a></body></html>");
			toProcess.r.addHeader("Location", url);
			return true;

		} else if (toProcess.path.equalsIgnoreCase("UploadStory.html/Submit/UserComment")) {
			// getting the parameters that are passed and saving them as variables
			String userID = toProcess.params.get("userID");

			if (!userID.equals("guest")) {
				String comment = toProcess.params.get("comment");
				String storyID = toProcess.params.get("storyID");

				String newCommentID = "comment" + Long.toString(System.currentTimeMillis());
				Comment tempComment = new Comment(newCommentID, comment);
				MVMap<String, Comment> comments = db.s.openMap("Comments");
				comments.put(newCommentID, tempComment);
				db.commit();

				MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
				List<String> userAccountsKeys = userAccounts.keyList();
				UserAccount user = userAccounts.get(userID);
				ArrayList<String> userCommentsIDs = new ArrayList<>();
				userCommentsIDs = user.getCommentStoryIDs();
				userCommentsIDs.add(newCommentID);
				user.setCommentStoryIDs(userCommentsIDs);
				userAccounts.put(userID, user);
				db.commit();

				MVMap<String, Story> stories = db.s.openMap("Stories");
				Story updateStory = stories.get(toProcess.params.get("storyID"));

				ArrayList<String> commentids = updateStory.getCommentIDs();
				if (commentids == null) {
					commentids.clear();
					commentids.add(newCommentID);
					updateStory.setCommentIDs(commentids);
				} else {
					commentids.add(newCommentID);
					updateStory.setCommentIDs(commentids);
				}
				stories.put(updateStory.getStoryID(), updateStory);

				db.commit();
			}
			String url = "/UploadStory.html?userID=" + userID;
			toProcess.r = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
					"<html><body>Redirected: <a href=\"" + url + "\">" + url + "</a></body></html>");
			toProcess.r.addHeader("Location", url);
			return true;
		} else if (toProcess.path.equalsIgnoreCase("UploadStory.html/Submit/Like")) {

			String likeUserID = toProcess.params.get("userID");
			String storyID = toProcess.params.get("storyID");

			if (likeUserID.equals("guest")) {

			} else {

				MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
				List<String> userAccountsKeys = userAccounts.keyList();
				UserAccount user = userAccounts.get(likeUserID);

				ArrayList<String> userLikedStories = new ArrayList<>();
				userLikedStories.addAll(user.getLikedStories());

				int k = 0;
				boolean storyExists = false;
				int position = 0;

				while (k < userLikedStories.size()) {
					if (userLikedStories.get(k) != null) {
						if (userLikedStories.get(k).equals(storyID)) {
							storyExists = true;
							position = k;
							break;
						}
					}
					k++;
				}

				MVMap<String, Story> stories = db.s.openMap("Stories");
				List<String> storiesKeys = stories.keyList();

				for (int j = 0; j < storiesKeys.size(); j++) {
					String searchStoryID = storiesKeys.get(j);
					Story updateStory = stories.get(searchStoryID);
					if (updateStory.getStoryID().equals(storyID)) {
						if (storyExists) {

							int updateStoryLikes = updateStory.getLikes();
							updateStoryLikes -= 1;
							updateStory.setLikes(updateStoryLikes);
							stories.put(updateStory.getStoryID(), updateStory);
							db.commit();

							userLikedStories.remove(position);
							user.setLikedStories(userLikedStories);
							userAccounts.put(likeUserID, user);
							db.commit();

						} else {
							int updateStoryLikes = updateStory.getLikes();
							updateStoryLikes += 1;
							updateStory.setLikes(updateStoryLikes);
							stories.put(updateStory.getStoryID(), updateStory);
							db.commit();

							userLikedStories.add(updateStory.getStoryID());
							user.setLikedStories(userLikedStories);
							userAccounts.put(likeUserID, user);
							db.commit();

							break;
						}
					}

				}
			}

			String url = "/UploadStory.html?userID=" + likeUserID;
			toProcess.r = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
					"<html><body>Redirected: <a href=\"" + url + "\">" + url + "</a></body></html>");
			toProcess.r.addHeader("Location", url);
			return true;

		}

		return false;
	}

}
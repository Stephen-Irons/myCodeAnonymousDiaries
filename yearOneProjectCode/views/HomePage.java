package views;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.h2.mvstore.MVMap;

import model.Category;
import model.Comment;
import model.Story;
import model.UserAccount;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class HomePage extends DynamicWebPage {

	public static String userID = "";

	public HomePage(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}

	public boolean process(WebRequest toProcess) {
		if (toProcess.path.equalsIgnoreCase("HomePage.html")) {

			// getting the userID passed through
			if (toProcess.params.get("userID") == null) {
				userID = "guest";
			} else {
				userID = toProcess.params.get("userID");
			}

			// getting the Search Category thats passed
			String searchCategory = "";
			if (toProcess.params.get("searchCategory") == null) {
				searchCategory = "All";
			} else {
				searchCategory = toProcess.params.get("searchCategory");
			}

			String orderBy = "";
			if (toProcess.params.get("orderBy") == null || !toProcess.params.get("orderBy").equals("Popularity")) {
				orderBy = "Upload Date";
			} else {
				orderBy = "Popularity";
			}
			// creating data to put in the database
			MVMap<String, Story> stories = db.s.openMap("Stories");
			List<String> storiesKeys = stories.keyList();

			MVMap<String, Comment> comments = db.s.openMap("Comments");
			List<String> commentsKeys = comments.keyList();

			MVMap<String, Category> categories = db.s.openMap("Categories");
			List<String> categoriesKeys = categories.keyList();

			if (storiesKeys.size() == 0) {

				String commentID = null;

				if (commentsKeys.size() == 0) {
					commentID = "comment" + Long.toString(System.currentTimeMillis());
					Comment commentOne = new Comment(commentID, "First database comment");
					comments.put(commentID, commentOne);
					db.commit();

					commentsKeys = comments.keyList();
				}

				// creating stories
				ArrayList<String> commentIDs = new ArrayList<String>();
				commentIDs.add(commentID);
				String storyID = "story" + Long.toString(System.currentTimeMillis());
				Story storyOne = new Story(storyID, "First database story", LocalDate.now(), "First test", 10,
						commentIDs);
				stories.put(storyID, storyOne);
				db.commit();
				stories.put(storyID, storyOne);
				String storyID1 = "story1" + Long.toString(System.currentTimeMillis());
				Story storyTwo = new Story(storyID1, "Second database story", LocalDate.now(), "Second story text", 0,
						commentIDs);
				stories.put(storyID1, storyTwo);
				db.commit();

				storiesKeys = stories.keyList();

				ArrayList<String> storyIDs = new ArrayList<>();
				storyIDs.add(storyID);
				storyIDs.add(storyID1);
				Category newCategory = new Category("All", storyIDs);
				categories.put("All", newCategory);

				categoriesKeys = categories.keyList();

				// creating test user account if one is not already created
				MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
				List<String> userAccountsKeys = userAccounts.keyList();
				/*
				 * if (userAccountsKeys.size() == 0) { // creating comments to add to
				 * commentArray String userID = "Stephen"; ArrayList<String> userStoryIDs = new
				 * ArrayList<>(); ArrayList<String> commentStoryIDs = new ArrayList<>();
				 * ArrayList<String> userLikeStoryIDs = new ArrayList<>();
				 * userLikeStoryIDs.add(null); UserAccount userAccountOne = new
				 * UserAccount("Stephen", "Stephen", "Password1", false, userLikeStoryIDs,
				 * userStoryIDs, commentStoryIDs);
				 * 
				 * userAccounts.put(userID, userAccountOne); db.commit();
				 * 
				 * userAccountsKeys = userAccounts.keyList(); }
				 */

			}
			String stringToSendToWebBrowser = "";
			stringToSendToWebBrowser += "<!DOCTYPE html>\n";
			stringToSendToWebBrowser += "<html>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <head>\n";
			stringToSendToWebBrowser += "    <title>HomePage</title>\n";
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
			stringToSendToWebBrowser += "          <a class=\"navbar-brand\" href=\"HomePage.html?userID=" + userID
					+ "\"><span>Anonymous Diaries</span></a>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "        <div class=\"collapse navbar-collapse\" id=\"navbar-ex-collapse\">\n";
			stringToSendToWebBrowser += "          <ul class=\"nav navbar-nav navbar-right\">\n";
			stringToSendToWebBrowser += "            <li class=\"active\">\n";
			stringToSendToWebBrowser += "              <a href=\"HomePage.html?userID=" + userID + "\">Home</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
			stringToSendToWebBrowser += "            <li>\n";
			stringToSendToWebBrowser += "              <a href=\"UploadStory.html?userID=" + userID
					+ "\">Upload Story</a>\n";
			// changing the text in the log out button to suit the user that is signed in
			stringToSendToWebBrowser += "            </li>\n";
			stringToSendToWebBrowser += "            <li>\n";
			stringToSendToWebBrowser += "              <a href=\"ContactUs.html?userID=" + userID
					+ "\">Contact us</a>\n";
			stringToSendToWebBrowser += "            </li>\n";
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
			stringToSendToWebBrowser += " <div class=\"container text-center\">\n";
			stringToSendToWebBrowser += " <p>Displaying stories in \"" + searchCategory + "\" ordered by \"" + orderBy
					+ "\"</p>\n";
			stringToSendToWebBrowser += "  <ul class=\"pager\">\n";
			stringToSendToWebBrowser += " 	<li>\n";
			stringToSendToWebBrowser += "       <a href=\"HomePage.html?orderBy=UploadDate&userID=" + userID
					+ "&searchCategory=" + searchCategory + "\">Upload Date</a>\n";
			stringToSendToWebBrowser += "     </li>\n";
			stringToSendToWebBrowser += "    <li>\n";
			stringToSendToWebBrowser += "      <a href=\"HomePage.html?orderBy=Popularity&userID=" + userID
					+ "&searchCategory=" + searchCategory + "\">Popularity</a>\n";
			stringToSendToWebBrowser += "    </li>\n";
			stringToSendToWebBrowser += " </ul>\n";
			stringToSendToWebBrowser += " </div>\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			// Displaying the category list
			stringToSendToWebBrowser += "          <div class=\"col-md-2\">\n";
			stringToSendToWebBrowser += "            <h3>Categories</h3>\n";
			categoriesKeys = categories.keyList();
			for (int i = 0; i < categoriesKeys.size(); i++) {
				String categoryID = categoriesKeys.get(i);
				Category category = categories.get(categoryID);
				stringToSendToWebBrowser += "            <h5>\n";
				stringToSendToWebBrowser += "              <a href=\"HomePage.html?searchCategory="
						+ category.getCategoryName() + "&userID=" + userID + "\">" + category.getCategoryName() + " ("
						+ category.getStoryIDs().size() + ")</a>\n";

				stringToSendToWebBrowser += "</form>";

				stringToSendToWebBrowser += "            </h5>\n";

			}
			stringToSendToWebBrowser += "  </div>\n";
			{
				stringToSendToWebBrowser += "      <div class=\"container\">\n";
				stringToSendToWebBrowser += "        <div class=\"row\">\n";
				stringToSendToWebBrowser += "          <div class=\"col-md-9\">\n";

				// displaying stories to the page
				categoriesKeys = categories.keyList();
				for (int j = 0; j < categoriesKeys.size(); j++) {

					String categoryID = categoriesKeys.get(j);
					Category category = categories.get(categoryID);
					if (category.getCategoryName().equals(searchCategory)) {
						ArrayList<String> storyIDs = new ArrayList<>();
						storyIDs.addAll(category.getStoryIDs());
						if (orderBy.equals("Upload Date")) {
							for (int i = storiesKeys.size() - 1; i >= 0; i--) {

								for (int k = 0; k < storyIDs.size(); k++) {

									String storyID = storiesKeys.get(i);
									Story story = stories.get(storyID);

									if (story.getStoryID().equals(storyIDs.get(k))) {
										stringToSendToWebBrowser += "            <div class=\"col-md-12\" style=\"border:thin\">\n";
										stringToSendToWebBrowser += "              <h1>" + story.getTitle() + "</h1>\n";
										stringToSendToWebBrowser += "              <br>" + story.getDatePublished()
												+ "\n";
										stringToSendToWebBrowser += "              <p>" + story.getStory() + "</p>\n";
										// passing the storyID through for the story being liked
										stringToSendToWebBrowser += "<form action=\"HomePage.html/Submit/Like\" method=\"GET\">";
										stringToSendToWebBrowser += "              <br>Likes: " + story.getLikes()
												+ "\n";
										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"userID\" name=\"userID\" value=\""
												+ userID + "\">\n";

										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"searchCategory\" name=\"searchCategory\" value=\""
												+ searchCategory + "\">\n";
										// like button code
										stringToSendToWebBrowser += "<button name=\"storyID\" value= "
												+ story.getStoryID() + ">Like</button>  </div>";
										stringToSendToWebBrowser += " </form>";

										stringToSendToWebBrowser += "              <br>\n";
										stringToSendToWebBrowser += "              <br>\n";

										// add/display comment section
										{
											stringToSendToWebBrowser += "            <form class=\"form-horizontal\" role=\"form\" action=\"/HomePage.html/Submit/UserComment\" method=\"GET\">\n";

											stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
											stringToSendToWebBrowser += "                <div class=\"input-group\">\n";
											stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" name=\"comment\" placeholder=\"Type comment here\"\n";
											stringToSendToWebBrowser += "                  name=\"comment\" required>\n";
											// passing the storyID
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"storyID\" value=\""
													+ story.getStoryID() + "\">\n";
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"userID\" value=\""
													+ userID + "\">\n";
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"searchCategory\" name=\"searchCategory\" value=\""
													+ searchCategory + "\">\n";
											stringToSendToWebBrowser += "                  <span class=\"input-group-btn\">\n";
											stringToSendToWebBrowser += "                  <button type=\"submit\" class=\"btn btn-default\">Add Comment</button>\n";
											stringToSendToWebBrowser += "                  </span>\n";
											stringToSendToWebBrowser += "                </div>\n";
											stringToSendToWebBrowser += "              </form>\n";
											stringToSendToWebBrowser += "              </div>\n";
										}
										// Displaying comments associated with the story

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

						} else if (orderBy.equals("Popularity")) {

							boolean swapped = true;
							while (swapped) {
								swapped = false;
								for (int i = 1; i < storyIDs.size(); i++) {
									Story storyb = null;
									Story storya = null;
									for (int d = 0; d < storiesKeys.size(); d++) {
										if (storiesKeys.get(d) == storyIDs.get(i)) {
											String storyIDa = storiesKeys.get(d);
											storya = stories.get(storyIDa);
										}
									}
									for (int e = 0; e < storiesKeys.size(); e++) {
										if (storiesKeys.get(e) == storyIDs.get(i - 1)) {
											String storyIDb = storiesKeys.get(e);
											storyb = stories.get(storyIDb);
										}
									}

									String temp = "";

									if (storyb.getLikes() < storya.getLikes()) {
										temp = storyIDs.get(i - 1);

										storyIDs.set(i - 1, storyIDs.get(i));
										storyIDs.set(i, temp);
										swapped = true;
									}
								}
							}

							for (int k = 0; k < storyIDs.size(); k++) {

								for (int i = 0; i < storiesKeys.size(); i++) {

									String storyID = storiesKeys.get(i);
									Story story = stories.get(storyID);

									if (story.getStoryID().equals(storyIDs.get(k))) {
										stringToSendToWebBrowser += "            <div class=\"col-md-12\" style=\"border:thin\">\n";
										stringToSendToWebBrowser += "              <h1>" + story.getTitle() + "</h1>\n";
										stringToSendToWebBrowser += "              <br>" + story.getDatePublished()
												+ "\n";
										stringToSendToWebBrowser += "              <p>" + story.getStory() + "</p>\n";
										// passing the storyID through for the story being liked
										stringToSendToWebBrowser += "<form action=\"HomePage.html/Submit/Like\" method=\"GET\">";
										stringToSendToWebBrowser += "              <br>Likes: " + story.getLikes()
												+ "\n";
										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"userID\" name=\"userID\" value=\""
												+ userID + "\">\n";

										stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"searchCategory\" name=\"searchCategory\" value=\""
												+ searchCategory + "\">\n";
										// like button code
										stringToSendToWebBrowser += "<button name=\"storyID\" value= "
												+ story.getStoryID() + ">Like</button>  </div>";
										stringToSendToWebBrowser += " </form>";

										stringToSendToWebBrowser += "              <br>\n";
										stringToSendToWebBrowser += "              <br>\n";

										// add/display comment section
										{
											stringToSendToWebBrowser += "            <form class=\"form-horizontal\" role=\"form\" action=\"/HomePage.html/Submit/UserComment\" method=\"GET\">\n";

											stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
											stringToSendToWebBrowser += "                <div class=\"input-group\">\n";
											stringToSendToWebBrowser += "                  <input type=\"text\" class=\"form-control\" name=\"comment\" placeholder=\"Type comment here\"\n";
											stringToSendToWebBrowser += "                  name=\"comment\" required>\n";
											// passing the storyID
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"storyID\" value=\""
													+ story.getStoryID() + "\">\n";
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"hiddeninput\" name=\"userID\" value=\""
													+ userID + "\">\n";
											stringToSendToWebBrowser += "                      <input type=\"hidden\" id=\"searchCategory\" name=\"searchCategory\" value=\""
													+ searchCategory + "\">\n";
											stringToSendToWebBrowser += "                  <span class=\"input-group-btn\">\n";
											stringToSendToWebBrowser += "                  <button type=\"submit\" class=\"btn btn-default\">Add Comment</button>\n";
											stringToSendToWebBrowser += "                  </span>\n";
											stringToSendToWebBrowser += "                </div>\n";
											stringToSendToWebBrowser += "              </form>\n";
											stringToSendToWebBrowser += "              </div>\n";
										}
										// Displaying comments associated with the story

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
				}

				stringToSendToWebBrowser += "          </div>\n";
				stringToSendToWebBrowser += "        </div>\n";
				stringToSendToWebBrowser += "      </div>\n";
				stringToSendToWebBrowser += "    </div>\n";
			}

			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
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
		} else if (toProcess.path.equalsIgnoreCase("HomePage.html/Submit/UserComment")) {
			// getting the parameters that are passed and saving them as variables
			String searchCategory = toProcess.params.get("searchCategory");
			String userID = toProcess.params.get("userID");
			if (!userID.equals("guest")) {
				String comment = toProcess.params.get("comment");
				String storyID = toProcess.params.get("storyID");
				// creating ID's
				String timeStamp = Long.toString(System.currentTimeMillis());
				String newCommentID = "comment" + timeStamp;

				// creating a new comment object
				Comment tempComment = new Comment(newCommentID, comment);

				// loading information about comments and adding a new comment
				MVMap<String, Comment> comments = db.s.openMap("Comments");
				comments.put(newCommentID, tempComment);
				// loading information about users and adding the new comment id to the correct
				// user
				MVMap<String, UserAccount> userAccounts = db.s.openMap("UserAccounts");
				List<String> userAccountsKeys = userAccounts.keyList();
				UserAccount user = userAccounts.get(userID);
				ArrayList<String> userCommentsIDs = new ArrayList<>();
				userCommentsIDs = user.getCommentStoryIDs();
				userCommentsIDs.add(newCommentID);
				user.setCommentStoryIDs(userCommentsIDs);
				userAccounts.put(userID, user);
				db.commit();

				// adding the new comment id to the correct story
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

			String url = "/HomePage.html?searchCategory=" + searchCategory + "&userID=" + userID;
			toProcess.r = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
					"<html><body>Redirected: <a href=\"" + url + "\">" + url + "</a></body></html>");
			toProcess.r.addHeader("Location", url);
			return true;

		} else if (toProcess.path.equalsIgnoreCase("HomePage.html/Submit/Like")) {
			// getting the search category
			String searchCategory = toProcess.params.get("searchCategory");
			// creating IDs
			String likeUserID = toProcess.params.get("userID");
			String storyID = toProcess.params.get("storyID");

			// checking if the person submitting a like is a valid user
			if (likeUserID.equals("guest")) {

			} else {
				// code to check if the user is adding a like or unliking a post and updating
				// the information in the database
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
							if (userLikedStories.isEmpty()) {
								userLikedStories.add(null);
							}
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
			String url = "/HomePage.html?searchCategory=" + searchCategory + "&userID=" + likeUserID;
			toProcess.r = new WebResponse(WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
					"<html><body>Redirected: <a href=\"" + url + "\">" + url + "</a></body></html>");
			toProcess.r.addHeader("Location", url);
			return true;
		}

		return false;
	}

}

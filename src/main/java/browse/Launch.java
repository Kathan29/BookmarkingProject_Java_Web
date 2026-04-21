package browse;

import java.util.List;

import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;
import managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		
		System.out.println("Loading data...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUser();
		bookmarks = BookmarkManager.getInstance().getBookmark();
		
		System.out.println("Printing data....");
		printUser();
		printBookmark();
		
	}
	private static void printBookmark() {
		for(var bookmarkList : bookmarks) {
			for(Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
		
	}
	private static void printUser() {
		for(User user:users) {
			System.out.println(user);
		}
		
	}
	
	private static void start() {
		
		
		for(User user : users) {
			View.browse(user, bookmarks);
		}
		
	}
	
	public static void main(String[] args) {
		loadData();
		start();
	}
	

}

package browse;

import java.util.List;

import constants.KidFriendlyStatus;
import constants.UserType;
import controllers.BookmarkController;
import entities.Bookmark;
import entities.User;
import partners.Sharable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\nUser : " + user.getFirstName() + " is browsing and User type is : " + user.getUserType());

		
		for (var bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {

				// Check if user want to bookmark
				
					boolean toBookmark = (Math.random() < 0.5) ? true : false;
					if (toBookmark) {
						//BookmarkController.getInstance().storeBookmarking(user, bookmark);
						System.out.println("Item bookmarked : " + bookmark);
	
					}
				
					

				// Do editor or chief editor task
				if (user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {

					// Approve or rejecting kid friendly status
					if (bookmark.isKidFriendly() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus takeDecision = getKidFriendlyStatusDecision();
						if (!takeDecision.equals(KidFriendlyStatus.UNKNOWN)) {

						//	BookmarkController.getInstance().setKidFriendlyStatus(user,takeDecision, bookmark);
						}
					}
					
					
					//Sharing the book or weblink to partners
					if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Sharable) {
						boolean shareDecision = (Math.random() < 0.5) ? true : false;
						if(shareDecision) {
						//	BookmarkController.getInstance().share(user,bookmark);
						}
					}
				}
				
			}
		}

	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision() {
		double val = Math.random();
		return (val < 0.4) ? KidFriendlyStatus.APPROVED
				: (val < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
	}

}

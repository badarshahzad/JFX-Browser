package userInterface;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;

<<<<<<< HEAD
=======
/**
 * @author SEGP-Group3
 *
 */
>>>>>>> phase5
public class HistoryTree 
{

	// This method creates an ArrayList of TreeItems (Links)
<<<<<<< HEAD
	public static ArrayList<TreeItem> getStoreItems()
=======
	/**
	 * @return arrayList having javaFX treeItems objects added to store details of visited URL's 
	 */
	public ArrayList<TreeItem> getStoreItems()
>>>>>>> phase5
	{
		ArrayList<TreeItem> storeItems = new ArrayList<TreeItem>();
		
		TreeItem hour = new TreeItem("Past hour");
		TreeItem today = new TreeItem("Today");
		TreeItem yesterday = new TreeItem("Yesterday");
		TreeItem month = new TreeItem("One Month");
		TreeItem six_Month = new TreeItem("Past Six Month");
		
		storeItems.add(hour);
		storeItems.add(today);
		storeItems.add(yesterday);
		storeItems.add(month);
		storeItems.add(six_Month);	
		return storeItems;		
	}
	

	
}
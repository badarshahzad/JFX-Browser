package userInterface;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;

public class HistoryTree 
{

	// This method creates an ArrayList of TreeItems (Links)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<TreeItem> getStoreItems()
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
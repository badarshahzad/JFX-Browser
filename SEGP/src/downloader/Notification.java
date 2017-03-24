package downloader;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.geometry.Pos;

public class Notification {
	public Notification(){
		Platform.runLater(new Runnable() {
			public void run() {
				Notifications notify = Notifications.create().title("Download")
						.text("Download File")
						.hideAfter(javafx.util.Duration.seconds(2))
						.position(Pos.BOTTOM_RIGHT);
				notify.darkStyle();
				notify.showInformation();
				notify.show();
			}
		}); 
		
		
	}

}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HtmlParser extends Application {

    public static void main(String[] args) 
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
	        primaryStage.setTitle("Palu Browser");
	        primaryStage.setScene(new Scene(root, 800,630));
	        primaryStage.show();
	}
}
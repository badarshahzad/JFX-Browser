package controllers;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProxyController implements Initializable {

	@FXML
	private AnchorPane proxyPane;

	@FXML
	private JFXRadioButton noProxy;

	@FXML
	private JFXRadioButton useSystemProxy;

	@FXML
	private JFXRadioButton manualProxy;

	@FXML
	private TextField httpProxyField;

	@FXML
	private TextField portField;

	@FXML
	private JFXButton okBt;

	@FXML
	private JFXButton cancelBt;

	public AnchorPane getProxyPane() {
		return proxyPane;
	}

	public JFXButton getOkBt() {
		return okBt;
	}

	public void setOkBt(JFXButton okBt) {
		this.okBt = okBt;
	}

	public JFXButton getCancelBt() {
		return cancelBt;
	}

	public void setCancelBt(JFXButton cancelBt) {
		this.cancelBt = cancelBt;
	}

	Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ToggleGroup group = new ToggleGroup();

		noProxy.setToggleGroup(group);
		
		useSystemProxy.setSelected(true);
		useSystemProxy.setToggleGroup(group);
		manualProxy.setToggleGroup(group);

		// Alert alert = new Alert(AlertType.CONFIRMATION);
		// alert.setGraphic(root);
		// alert.setTitle("Proxy Setting");
		// alert.setHeaderText("Configure Proxy to Access Internet");

		// Optional<ButtonType> result = alert.showAndWait();
		Properties systemProperties = System.getProperties();
		okBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			if (group.getSelectedToggle().equals(noProxy)) {

				// Remove Proxy for Http
				systemProperties.setProperty("http.proxyHost", "");
				systemProperties.setProperty("http.proxyPort", "");

				// Remove Proxy for Https
				systemProperties.setProperty("https.proxyHost", "");
				systemProperties.setProperty("https.proxyPort", "");

			}

			if (group.getSelectedToggle().equals(useSystemProxy)) {
				// Set Proxy for Http
				systemProperties.setProperty("http.proxyHost", "172.16.0.2");
				systemProperties.setProperty("http.proxyPort", "8080");

				// Set Proxy for Https
				systemProperties.setProperty("https.proxyHost", "172.16.0.2");
				systemProperties.setProperty("https.proxyPort", "8080");
			}

			if (group.getSelectedToggle().equals(manualProxy)) {

				// Set Proxy for Http
				systemProperties.setProperty("http.proxyHost", httpProxyField.getText());
				systemProperties.setProperty("http.proxyPort", portField.getText());

				// Set Proxy for Https
				systemProperties.setProperty("https.proxyHost", httpProxyField.getText());
				systemProperties.setProperty("https.proxyPort", portField.getText());
			}

		});
		cancelBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

		});

	}

	/*
	 * @Override public void start(Stage primaryStage) throws Exception { //
	 * TODO Auto-generated method stub
	 * 
	 * Parent root =
	 * FXMLLoader.load(getClass().getResource("/ui/ProxySet.fxml"));
	 * 
	 * Scene scene = new Scene(root); primaryStage.setTitle("Connection Setting"
	 * ); primaryStage.setScene(scene); primaryStage.setResizable(false);
	 * primaryStage.setMaximized(false); primaryStage.setAlwaysOnTop(true);
	 * primaryStage.setOpacity(.9);
	 * 
	 * primaryStage.setFullScreenExitHint("Close here"); // primaryStage.show();
	 * setStage(primaryStage);
	 * 
	 * }
	 */

}

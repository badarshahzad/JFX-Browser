package controllers;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.application.Application;
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
import javafx.util.Duration;

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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ToggleGroup group = new ToggleGroup();

		noProxy.setToggleGroup(group);

		//current proxy setting
		useSystemProxy.setSelected(true);

		useSystemProxy.setToggleGroup(group);
		manualProxy.setToggleGroup(group);

		httpProxyField.setDisable(true);
		portField.setDisable(true);

		// disabled other field while that one selected
		// httpProxyField.setDisable(true);
		// portField.setDisable(true);

		Properties systemProperties = System.getProperties();

		noProxy.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			httpProxyField.setDisable(true);
			portField.setDisable(true);

		});

		useSystemProxy.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			httpProxyField.setDisable(true);
			portField.setDisable(true);


		});

		manualProxy.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			httpProxyField.setDisable(false);
			portField.setDisable(false);

		});

		okBt.setId("button");
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

			String message = null;

			if (group.getSelectedToggle().equals(manualProxy)) {

				if (httpProxyField.getText().equals("") || portField.getText().equals("")) {

					if (httpProxyField.getText().equals("") && portField.getText().equals("")) {

						message = "Your both field is empty. Please fill both \n fields to set manual proxy.";

					} else {

						message = "Your one field is empty. Please fill one empty \n field to set manual proxy.";

					}

					Notifications.create().title("Proxy Setting").text(message).hideAfter(Duration.seconds(3))
					.showError();;

				} else {
					/*
					Pattern ipAddress = Pattern.compile("[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{3}");
					Matcher m1 = ipAddress.matcher(httpProxyField.getText());
					boolean b1 = m1.matches();

					Pattern port = Pattern.compile("[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{1}[.]{1}[0-9]{1}");
					Matcher m2 = port.matcher(portField.getText());
					boolean b2 = m2.matches();

					if (b1 == true && b2 == false) {

						Notifications.create()
						.title("Proxy Setting")
						.text("Your port is not correct try again")
						.hideAfter(Duration.seconds(3))
						.showError();

					} else if (b1 == false && b2 == true) {

						Notifications.create()
						.title("Proxy Setting")
						.text("Your IP is not correct try again")
						.hideAfter(Duration.seconds(3))
						.showError();

					}
					 */

					systemProperties.setProperty("http.proxyHost", httpProxyField.getText());
					systemProperties.setProperty("http.proxyPort", portField.getText());

					// Set Proxy for Https
					systemProperties.setProperty("https.proxyHost", httpProxyField.getText());
					systemProperties.setProperty("https.proxyPort", portField.getText());

				}


			}
		});

		cancelBt.setId("button");
		cancelBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			getStage().close();
		});

	}

	/*
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource(Main.FXMLS+"ProxySet.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("Connection Setting");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOpacity(.9);

		primaryStage.setFullScreenExitHint("Close here"); // primaryStage.show();
		primaryStage.show();
		setStage(primaryStage);

	}

	public static void main(String args[]) {
		launch(args);
	}*/

	Stage stage = new Stage();


	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

}

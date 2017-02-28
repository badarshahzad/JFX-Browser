package testing;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DetectProxy extends Application {

    private Pane root;

    @Override
    public void start(final Stage stage) throws URISyntaxException {
        root = new VBox();

        List<Proxy> proxies = ProxySelector.getDefault().select(new URI("http://google.com"));
        final Proxy proxy = proxies.get(0); // ignoring multiple proxies to simplify code snippet
        if (proxy.type() != Proxy.Type.DIRECT) {
            // you can change that to dialog using separate Stage
            final TextField login = new TextField("login");
            final PasswordField pwd = new PasswordField();
            Button btn = new Button("Submit");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.setProperty("http.proxyUser", login.getText());
                    System.setProperty("http.proxyPassword", pwd.getText());
                    showWebView();
                }
            });
            root.getChildren().addAll(login, pwd, btn);
        } else {
            showWebView();
        }

        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    private void showWebView() {
        root.getChildren().clear();
        WebView webView = new WebView();

        final WebEngine webEngine = webView.getEngine();
        root.getChildren().addAll(webView);
        webEngine.load("http://google.com");

    }
    
    public static void main(String[] args ){
		Application.launch(args);
	}
        }
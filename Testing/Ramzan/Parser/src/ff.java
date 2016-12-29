
public class ff {

	public static void main(String[] args) {
		JFXPanel jfxPanel = new JFXPanel(); // Scrollable JCompenent
		Platform.runLater( () -> { // FX components need to be managed by JavaFX
		   WebView webView = new WebView();
		   webView.getEngine().loadContent( "<html> Hello World!" );
		   webView.getEngine().load( "http://www.stackoverflow.com/" );
		   jfxPanel.setScene( new Scene( webView ) );
		});
	}

}

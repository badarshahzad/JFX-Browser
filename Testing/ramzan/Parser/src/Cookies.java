/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.common.Utils;
import chrriis.common.WebServer;
import chrriis.common.WebServer.HTTPRequest;
import chrriis.common.WebServer.WebServerContent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
public class Cookies extends JPanel {

  public Cookies() {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.setBarsVisible(false);
    webBrowser.setStatusBarVisible(true);
    final String url = WebServer.getDefaultWebServer().getDynamicContentURL(getClass().getName(), "index.html");
    webBrowser.navigate(url);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    add(webBrowserPanel, BorderLayout.CENTER);
    JPanel cookiesPanel = new JPanel(new BorderLayout());
    cookiesPanel.setBorder(BorderFactory.createTitledBorder("Get/Set Cookies"));
    JPanel getSetNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
    getSetNorthPanel.add(new JLabel("Cookie Value:"));
    final JTextField setCookieValueField = new JTextField("Some other value", 14);
    getSetNorthPanel.add(setCookieValueField);
    JButton setButton = new JButton("Set");
    setButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JWebBrowser.setCookie(url, "SomeNSCookie=" + setCookieValueField.getText());
      }
    });
    getSetNorthPanel.add(setButton);
    JButton getButton = new JButton("Get");
    getSetNorthPanel.add(getButton);
    JButton clearAllButton = new JButton("Clear all session cookies");
    clearAllButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JWebBrowser.clearSessionCookies();
      }
    });
    getSetNorthPanel.add(clearAllButton);
    cookiesPanel.add(getSetNorthPanel, BorderLayout.NORTH);
    JPanel getSetSouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
    getSetSouthPanel.add(new JLabel("Last acquired cookie value:"));
    final JLabel getLabel = new JLabel("-");
    getButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String cookieValue = JWebBrowser.getCookie(url, "SomeNSCookie");
        getLabel.setText(cookieValue == null? "null": cookieValue);
      }
    });
    getSetSouthPanel.add(getLabel);
    cookiesPanel.add(getSetSouthPanel, BorderLayout.SOUTH);
    add(cookiesPanel, BorderLayout.SOUTH);
  }

  private static final String LS = Utils.LINE_SEPARATOR;

  protected static WebServerContent getWebServerContent(HTTPRequest httpRequest) {
    return new WebServerContent() {
      @Override
      public InputStream getInputStream() {
        String content =
          "<html>" + LS +
          "  <head>" + LS +
          "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" + LS +
          "    <script language=\"JavaScript\" type=\"text/javascript\">" + LS +
          "      <!--" + LS +
          "      function saveCookie(name) {" + LS +
          "        var value = document.forms['cookieForm'].cookieValue.value;" + LS +
          "        if (!value) {" + LS +
          "          alert('Please enter a value in the text field.');" + LS +
          "        } else {" + LS +
          "          document.cookie = name + '=' + value;" + LS +
          "          alert('Cookie set');" + LS +
          "        }" + LS +
          "      }" + LS +
          "      function readCookie(name) {" + LS +
          "        var nameEQ = name + '=';" + LS +
          "        var ca = document.cookie.split(';');" + LS +
          "        var value = null;" + LS +
          "        for(var i=0; i < ca.length; i++) {" + LS +
          "          var c = ca[i];" + LS +
          "          while (c.charAt(0) == ' ') c = c.substring(1, c.length);" + LS +
          "          if (c.indexOf(nameEQ) == 0) {" + LS +
          "            value = c.substring(nameEQ.length, c.length);" + LS +
          "            break;" + LS +
          "          }" + LS +
          "        }" + LS +
          "        alert('The value of the cookie is: ' + value);" + LS +
          "      }" + LS +
          "      function deleteCookie(name) {" + LS +
          "        document.cookie = name + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT';" + LS +
          "        alert('Cookie deleted');" + LS +
          "      }" + LS +
          "      //-->" + LS +
          "    </script>" + LS +
          "  </head>" + LS +
          "  <body>" + LS +
          "    <form name=\"cookieForm\" action=\"#\"><input name=\"cookieValue\" value=\"Some value\"> <a href=\"javascript:saveCookie('SomeNSCookie')\" class=\"page\">Set cookie</a></form><br>" + LS +
          "    <a href=\"javascript:readCookie('SomeNSCookie')\" class=\"page\">Read cookie</a>, <a href=\"javascript:deleteCookie('SomeNSCookie')\" class=\"page\">Delete cookie</a>" + LS +
          "  </body>" + LS +
          "</html>" + LS;
        return getInputStream(content);
      }
    };
  }

  /* Standard main method to try that test as a standalone application. */
  public static void main(String[] args) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Cookies(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }

}
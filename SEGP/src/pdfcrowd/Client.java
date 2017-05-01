// Copyright (C) 2010-2013 pdfcrowd.com
// 
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.

package pdfcrowd;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

//
// Pdfcrowd API client.
// 
public class Client {

	private HashMap<String,String> fields;
	private boolean m_useSSL = false;

	//
	// Client constructor.
	// 
	// username - your username at Pdfcrowd
	// apikey   - your API key
	// 
	public Client(String username, String apikey) {
		init(username, apikey, API_HOSTNAME);
	}

	//
	// Converts a web page.
	//
	// uri       - a web page URL
	// outStream - an output stream (java.io.OutputStream)
	// 
	public void convertURI(String uri, OutputStream outStream) {
		byte[] data = encodePostData(uri);
		callAPI(data, "pdf/convert/uri/", outStream, URLENCODED);
	}

	//
	// Converts an in-memory html document.
	//
	// html      - a string containing a html document
	// outStream - an output stream (java.io.OutputStream)
	// 
	public void convertHtml(String html, OutputStream outStream) {
		byte[] data = encodePostData(html);
		callAPI(data, "pdf/convert/html/", outStream, URLENCODED);
	}

	//
	// Converts an html file.
	//
	// path      - a path to an html file
	// outStream - an output stream (java.io.OutputStream)
	// 
	public void convertFile(String path, OutputStream outStream) {
		ByteArrayOutputStream data = encodeMultipartPostData(path);
		callAPI(data, "pdf/convert/html/", outStream, MULTIPART);
	}

	//
	// Returns the number of available conversion tokens.
	// 
	public int numTokens() {
		byte[] data = encodePostData(null);
		ByteArrayOutputStream tokens = new ByteArrayOutputStream();
		callAPI(data, "user/" + fields.get("username") + "/tokens/", tokens, URLENCODED);
		return Integer.parseInt(tokens.toString());
	}

	public void useSSL(boolean val) {
		m_useSSL = val;
	}

	public void setUsername(String username) {
		fields.put("username", username);
	}

	public void setApiKey(String key) {
		fields.put("key", key);
	}

	public void setPageWidth(double value) {
		fields.put("width", doubleToString(value));
	}

	public void setPageWidth(String value) {
		fields.put("width", value);
	}


	public void setPageHeight(double value) {
		fields.put("height", doubleToString(value));
	}

	public void setPageHeight(String value) {
		fields.put("height", value);
	}

	public void setHorizontalMargin(double value) {
		fields.put("margin_right", doubleToString(value));
		fields.put("margin_left", doubleToString(value));
	}

	public void setHorizontalMargin(String value) {
		fields.put("margin_right", value);
		fields.put("margin_left", value);
	}

	public void setVerticalMargin(double value) {
		fields.put("margin_top", doubleToString(value));
		fields.put("margin_bottom", doubleToString(value));
	}

	public void setVerticalMargin(String value) {
		fields.put("margin_top", value);
		fields.put("margin_bottom", value);
	}

	public void setPageMargins(String top, String right, String bottom, String left) {
		fields.put("margin_top", top);
		fields.put("margin_right", right);
		fields.put("margin_bottom", bottom);
		fields.put("margin_left", left);
	}


	public void setEncrypted() {
		setEncrypted(true);
	}

	public void setEncrypted(boolean value) {
		fields.put("encrypted", value ? "true" : null);
	}

	public void setUserPassword(String pwd) {
		fields.put("user_pwd", pwd);
	}

	public void setOwnerPassword(String pwd) {
		fields.put("owner_pwd", pwd);
	}

	public void setNoPrint() {
		setNoPrint(true);
	}

	public void setNoPrint(boolean val) {
		fields.put("no_print", val ? "true" : null);
	}

	public void setNoModify() {
		setNoModify(true);
	}

	public void setNoModify(boolean val) {
		fields.put("no_modify", val ? "true" : null);
	}

	public void setNoCopy() {
		setNoCopy(true);
	}

	public void setNoCopy(boolean val) {
		fields.put("no_copy", val ? "true" : null);
	}

	// constants for setPageLayout()
	public static int SINGLE_PAGE = 1;
	public static int CONTINUOUS = 2;
	public static int CONTINUOUS_FACING = 3;

	public void setPageLayout(int value) {
		assert value > 0 && value <= 3;
		fields.put("page_layout", Integer.toString(value));
	}

	// constants for setPageMode()
	public static int NONE_VISIBLE = 1;
	public static int THUMBNAILS_VISIBLE = 2;
	public static int FULLSCREEN = 3;

	public void setPageMode(int value) {
		assert value > 0 && value <= 3;
		fields.put("page_mode", Integer.toString(value));
	}

	public void setFooterText(String value) {
		fields.put("footer_text", value);
	}

	public void enableImages() {
		enableImages(true);
	}

	public void enableImages(boolean value) {
		fields.put("no_images", value ? null : "true");
	}

	public void enableBackgrounds() {
		enableBackgrounds(true);
	}

	public void enableBackgrounds(boolean value) {
		fields.put("no_backgrounds", value ? null : "true");
	}

	public void setHtmlZoom(double value) {
		fields.put("html_zoom", Double.toString(value));
	}

	public void enableJavaScript() {
		enableJavaScript(true);
	}

	public void enableJavaScript(boolean value) {
		fields.put("no_javascript", value ? null : "true");
	}

	public void enableHyperlinks() {
		enableHyperlinks(true);
	}

	public void enableHyperlinks(boolean value) {
		fields.put("no_hyperlinks", value ? null : "true");
	}

	public void setDefaultTextEncoding(String value) {
		fields.put("text_encoding", value);
	}

	public void usePrintMedia() {
		usePrintMedia(true);
	}

	public void usePrintMedia(boolean value) {
		fields.put("use_print_media", value ? "true" : null);
	}

	public void setMaxPages(int value) {
		fields.put("max_pages", Integer.toString(value));
	}

	public void enablePdfcrowdLogo() {
		enablePdfcrowdLogo(true);
	}

	public void enablePdfcrowdLogo(boolean value) {
		fields.put("pdfcrowd_logo", value ? "true" : null);
	}

	// constants for setInitialPdfZoomType()
	public static int FIT_WIDTH = 1;
	public static int FIT_HEIGHT = 2;
	public static int FIT_PAGE = 3;

	public void setInitialPdfZoomType(int value) {
		assert value>0 && value<=3;
		fields.put("initial_pdf_zoom_type", Integer.toString(value));
	}

	public void setInitialPdfExactZoom(double value) {
		fields.put("initial_pdf_zoom_type", "4");
		fields.put("initial_pdf_zoom", Double.toString(value));
	}

	public Client(String username, String apikey, String api_hostname) {
		init(username, apikey, api_hostname);
	}

	public void setAuthor(String value) {
		fields.put("author", value);
	}

	public void setFailOnNon200(boolean value) {
		fields.put("fail_on_non200", value ? "true" : null);
	}

	public void setPdfScalingFactor(double value) {
		fields.put("pdf_scaling_factor", doubleToString(value));
	}

	public void setFooterHtml(String value) {
		fields.put("footer_html", value);
	}

	public void setFooterUrl(String value) {
		fields.put("footer_url", value);
	}

	public void setHeaderHtml(String value) {
		fields.put("header_html", value);
	}

	public void setHeaderUrl(String value) {
		fields.put("header_url", value);
	}

	public void setPageBackgroundColor(String value) {
		fields.put("page_background_color", value);
	}

	public void setTransparentBackground() {
		setTransparentBackground(true);
	}

	public void setTransparentBackground(boolean val) {
		fields.put("transparent_background", val ? "true" : null);
	}

	public void setPageNumberingOffset(int value) {
		fields.put("page_numbering_offset", Integer.toString(value));
	}

	public void setHeaderFooterPageExcludeList(String value) {
		fields.put("header_footer_page_exclude_list", value);
	}

	public void setWatermark(String url, double offset_x, double offset_y) {
		fields.put("watermark_url", url);
		fields.put("watermark_offset_x", Double.toString(offset_x));
		fields.put("watermark_offset_y", Double.toString(offset_y));
	}

	public void setWatermark(String url, String offset_x, String offset_y) {
		fields.put("watermark_url", url);
		fields.put("watermark_offset_x", offset_x);
		fields.put("watermark_offset_y", offset_y);
	}

	public void setWatermarkRotation(double angle) {
		fields.put("watermark_rotation", Double.toString(angle));
	}

	public void setWatermarkInBackground() {
		setWatermarkInBackground(true);
	}

	public void setWatermarkInBackground(boolean val) {
		fields.put("watermark_in_background", val ? "true" : null);
	}




	// ----------------------------------------------------------------------
	//                     Private stuff
	//                     

	private static String URLENCODED = new String("application/x-www-form-urlencoded");
	private static String MULTIPART_BOUNDARY = new String("----------ThIs_Is_tHe_bOUnDary_$");
	private static String MULTIPART = new String("multipart/form-data; boundary=" + MULTIPART_BOUNDARY);

	public static String API_HOSTNAME = new String("pdfcrowd.com");
	public static int API_HTTP_PORT = 80;
	public static int API_HTTPS_PORT = 443;
	private String api_hostname;


	private final static HostnameVerifier HOSTNAME_VERIFIER = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
			//return "pdfcrowd.com".equals(hostname);
		}
	};

	private void init(String username, String apikey, String apihost) {
		fields = new HashMap<String,String>();
		fields.put("username", username);
		fields.put("key", apikey);
		fields.put("pdf_scaling_factor", "1.0");
		fields.put("html_zoom", "200");
		api_hostname = apihost;
	}


	private String doubleToString(double val) {
		return String.format(Locale.US, "%.5f", val);
	}

	private static void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[8192];
		while (true) {
			int bytesRead = in.read(buffer, 0, 8192);
			if (bytesRead == -1) break;
			out.write(buffer, 0, bytesRead);
		}
	}

	private static String join(AbstractCollection<String> col, String delimiter) {
		if (col.isEmpty()) return "";
		Iterator<String> iter = col.iterator();
		StringBuffer buffer = new StringBuffer(iter.next());
		while(iter.hasNext()) buffer.append(delimiter).append(iter.next());
		return buffer.toString();
	}

	private String getBaseUri() {
		if (m_useSSL)
		{
			return String.format("https://%s:%d/api/", api_hostname, API_HTTPS_PORT);
		}
		else
		{
			return String.format("http://%s:%d/api/", api_hostname, API_HTTP_PORT);
		}
	}

	private HttpURLConnection getConnection(String uriSuffix, String contentType) throws IOException {
		try
		{
			URL url;
			HttpURLConnection conn = (HttpURLConnection)new URL(
					getBaseUri() + uriSuffix).openConnection();

			if (m_useSSL)
			{
				// BUG: sun-java6-bin: missing cacerts the trustAnchors parameter must be non-empty
				// http://bugs.debian.org/cgi-bin/bugreport.cgi?bug=564903
				HttpsURLConnection ssl_conn = (HttpsURLConnection)conn;
				ssl_conn.setHostnameVerifier(HOSTNAME_VERIFIER);
			}
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", contentType);
			conn.setConnectTimeout(120000);
			return conn;
		}
		catch(MalformedURLException e)
		{
			throw new PdfcrowdError(e);
		}
	}

	private void callAPI(Object data, String uriSuffix,
			OutputStream outStream, String contentType) {
		try
		{
			HttpURLConnection conn = getConnection(uriSuffix, contentType);
			OutputStream wr = conn.getOutputStream();
			if (data instanceof byte[]) {
				//System.out.println(new String((byte[])data));
				wr.write((byte[])data);
			}
			else {
				//System.out.println(((ByteArrayOutputStream)data).toString());
				((ByteArrayOutputStream)data).writeTo(wr);
			}
			wr.flush();

			if (conn.getResponseCode() == 200) {
				InputStream rd = conn.getInputStream();
				copyStream(rd, outStream);
				rd.close();             
			}
			else {
				String errMsg;
				if (conn.getErrorStream() != null) {
					ByteArrayOutputStream errOut = new ByteArrayOutputStream();
					copyStream(conn.getErrorStream(), errOut);
					errMsg = errOut.toString();
				}
				else {
					errMsg = conn.getResponseMessage();
				}
				throw new PdfcrowdError(errMsg, conn.getResponseCode());
			}
			wr.close();
		}
		catch(IOException e)
		{
			throw new PdfcrowdError(e);
		}
	}

	private ByteArrayOutputStream encodeMultipartPostData(String filename) {
		try
		{
			Vector<String> body = new Vector<String>();
			Iterator<String> it = fields.keySet().iterator();
			ByteArrayOutputStream retval = new ByteArrayOutputStream();
			while(it.hasNext()) {
				String key = it.next();
				String val = fields.get(key);
				if (val != null)
				{
					body.add("--" + MULTIPART_BOUNDARY);
					body.add(String.format("Content-Disposition: form-data; name=\"%s\"", key));
					body.add("");
					body.add(val);
				}
			}
			// filename
			body.add("--" + MULTIPART_BOUNDARY);
			body.add(String.format("Content-Disposition: form-data; name=\"src\"; filename=\"%s\"", filename));
			body.add("Content-Type: application/octet-stream");
			body.add("");
			body.add("");
			retval.write(join(body, "\r\n").getBytes("UTF-8"));
			body.clear();

			// read file
			copyStream(new FileInputStream(filename), retval);

			body.add("");
			body.add("--" + MULTIPART_BOUNDARY);
			body.add("");
			retval.write(join(body, "\r\n").getBytes("UTF-8"));

			return retval;
		}
		catch(UnsupportedEncodingException e) {
			throw new PdfcrowdError(e);
		}
		catch(IOException e) {
			throw new PdfcrowdError(e);
		}
	}


	private byte[] encodePostData(String src) {

		Vector<String> body = new Vector<String>();
		Iterator it = fields.keySet().iterator();
		try
		{
			if (src != null)
				body.add(URLEncoder.encode("src", "UTF-8") + "=" +
						URLEncoder.encode(src, "UTF-8"));

			while(it.hasNext()) {
				Object key = it.next();
				Object val = fields.get(key);

				if (val != null)
				{
					body.add(URLEncoder.encode(key.toString(), "UTF-8") + "=" +
							URLEncoder.encode(val.toString(), "UTF-8"));
				}
			}
			return join(body, "&").getBytes("UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			throw new PdfcrowdError(e);
		}
	}
}

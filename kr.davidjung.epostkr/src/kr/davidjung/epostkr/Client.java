package kr.davidjung.epostkr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
	private String url;
	private int status = 0;
	private String body;
	
	/**
	 * »ý¼ºÀÚ
	 * @param url
	 */
	public Client(String url) {
		this.url = url;
	}
	
	public String actionPost (String[] keys, String[] values) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
		StringBuffer postParams = new StringBuffer();
		
		for(int i=0; i<keys.length; i++) {
			postParams.append(keys[i]).append("=").append(values[i]);
		}
		
		// Request
        OutputStreamWriter wStream = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        PrintWriter wPrint = new PrintWriter(wStream);
        wPrint.write(postParams.toString());
        wPrint.flush();
        wPrint.close();
        
        // Get Response
        InputStreamReader rStream = new InputStreamReader(con.getInputStream(), "UTF-8");
        BufferedReader reader = new BufferedReader(rStream);
        StringBuilder response = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
        	response.append(str + "\n");
        }
        return response.toString();
	}
	
	public void actionGet() {
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con;
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0");
			this.status = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream())
				);
			String inputLine;
			StringBuffer response = new StringBuffer();
	        
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			this.body = response.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBody() {
		return this.body;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getUrl() {
		return this.url;
	}
}

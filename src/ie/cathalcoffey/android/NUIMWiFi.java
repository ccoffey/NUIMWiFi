package ie.cathalcoffey.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class NUIMWiFi {

	public static void login(String username, String password){
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("https://webportal1.nuim.ie:8001/");
	    try 
	    {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("auth_user", username));
	        nameValuePairs.add(new BasicNameValuePair("auth_pass", password));
	        nameValuePairs.add(new BasicNameValuePair("redirurl", "http://www.nuim.ie"));
	        nameValuePairs.add(new BasicNameValuePair("accept", ":: Secure Network Logon ::"));
	        		
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	    } 
	    
	    catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } 
	    
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	}
}

package ie.cathalcoffey.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;

public class NUIMWiFi extends AsyncTask<String, Void, Void>{

	@Override
	protected Void doInBackground(String... params) 
	{
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter("http.protocol.expect-continue", false);
		
	    HttpPost httppost = new HttpPost("https://webportal1.nuim.ie:8001/");
	    try 
	    {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("auth_user", params[0]));
	        nameValuePairs.add(new BasicNameValuePair("auth_pass", params[1]));
	        nameValuePairs.add(new BasicNameValuePair("redirurl", "http://www.nuim.ie"));
	        nameValuePairs.add(new BasicNameValuePair("accept", ":: Secure Network Logon ::"));
	        		
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        httpclient.execute(httppost);
	    } 
	    
	    catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } 
	    
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		return null;
	}
}

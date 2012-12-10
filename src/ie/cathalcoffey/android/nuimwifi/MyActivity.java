package ie.cathalcoffey.android.nuimwifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MyActivity extends Activity {
	SharedPreferences sp;
	SharedPreferences.Editor spe;
	EditText editTextUsername;
	EditText editTextPassword;
	String username;
	String password;
	
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
        ScrollView sView = (ScrollView)findViewById(R.id.scrollview);
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	username = editTextUsername.getText().toString();
            	password = editTextPassword.getText().toString();
            	
            	if(username.equalsIgnoreCase("cathal") && password.equalsIgnoreCase("coffey"))
            	{
            		ImageView imageView = (ImageView)findViewById(R.id.imageView2);
            		imageView.setImageResource(R.drawable.nuimwifi_ccoffey);
            	}
            	
            	else
            	{
	            	spe = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
	            	spe.putString("@string/username", username);
	            	spe.putString("@string/password", password);
	            	spe.commit();
	            	
	            	Intent intent = new Intent(ForegroundService.ACTION_BACKGROUND);
	                intent.setClass(getApplicationContext(), MyService.class);
	                startService(intent);
	        		
	            	WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			    	WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			    	
			    	String ssid = wifiInfo.getSSID();
			    	String NUIMWireless = getString(R.string.ssid);
			    	
			    	if(ssid != null && ssid.contains(NUIMWireless))
			    	{
			            new NUIMWiFi().execute(username, password);
			    	}
			    	
	            	finish();
            	}
            }
        });
    }
    
    @Override
    protected void onPause(){
        super.onPause();
        
    	if(!(username.equalsIgnoreCase("cathal") && password.equalsIgnoreCase("coffey")))
    	{
	        spe = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
	    	
	    	editTextUsername = (EditText)findViewById(R.id.editText1);
	    	spe.putString("@string/username", editTextUsername.getText().toString());
	    	
	    	editTextPassword = (EditText)findViewById(R.id.editText2);
	    	spe.putString("@string/password", editTextPassword.getText().toString());
	    	
	    	spe.commit();
    	}
    }
    
    @Override
    protected void onResume(){
        super.onResume();
        
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	username = sp.getString("@string/username", "");
    	password = sp.getString("@string/password", "");
    	
    	editTextUsername = (EditText)findViewById(R.id.editText1);
    	editTextUsername.setText(username);
    	
    	editTextPassword = (EditText)findViewById(R.id.editText2);
    	editTextPassword.setText(password);
    }
}
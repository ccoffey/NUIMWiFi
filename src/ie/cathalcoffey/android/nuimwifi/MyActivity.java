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
import android.widget.Button;
import android.widget.EditText;

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
        setContentView(R.layout.main);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	username = editTextUsername.getText().toString();
            	password = editTextPassword.getText().toString();
            	
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
		    	
		    	if(ssid != null && ssid.equalsIgnoreCase(getText(R.string.ssid).toString())){
		    		new NUIMWiFi().execute(username, password);
		    	}
		    	
            	finish();
            }
        });
    }
    
    @Override
    protected void onPause(){
        super.onPause();
        
        spe = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
    	
    	editTextUsername = (EditText)findViewById(R.id.editText1);
    	spe.putString("@string/username", editTextUsername.getText().toString());
    	
    	editTextPassword = (EditText)findViewById(R.id.editText2);
    	spe.putString("@string/password", editTextPassword.getText().toString());
    	
    	spe.commit();
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
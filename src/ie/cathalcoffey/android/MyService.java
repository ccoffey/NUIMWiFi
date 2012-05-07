package ie.cathalcoffey.android;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

public class MyService extends ForegroundService {

	MyBroadcastReceiver bc;
	
	@Override
	public IBinder onBind(Intent intent) {
		return super.onBind(intent);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		this.bc = new MyBroadcastReceiver();
		registerReceiver(bc, new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if(bc != null)
			unregisterReceiver(bc);
	}

	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
    }
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}}

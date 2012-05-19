package ie.cathalcoffey.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getDataString().contains("ie.cathalcoffey.android"))
		{
			Intent serviceIntent = new Intent(ForegroundService.ACTION_BACKGROUND);
			serviceIntent.setClass(context, MyService.class);
			context.startService(serviceIntent);
		}
	}

}

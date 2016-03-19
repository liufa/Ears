package gcm.play.android.samples.com.gcmquickstart;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by Роман on 19.03.2016.
 */
public class ChangeLocationService extends Service {

    private static String TAG = ChangeLocationService.class.getSimpleName();

    private LocationHelper mLocationHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mLocationHelper = LocationHelper.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationHelper.requestLocationUpdate();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

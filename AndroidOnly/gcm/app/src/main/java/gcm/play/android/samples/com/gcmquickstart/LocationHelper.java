package gcm.play.android.samples.com.gcmquickstart;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;

import gcm.play.android.samples.com.gcmquickstart.api.RestClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Роман on 19.03.2016.
 */
public class LocationHelper implements LocationListener {

    private static String TAG = LocationHelper.class.getSimpleName();
    private static LocationHelper mInstance;

    private Context mContext;
    private Location mLocation;
    public LocationManager mLocationManager;

    public LocationHelper(Context context) {
        mContext = context;
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public static LocationHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LocationHelper.class) {
                if (mInstance == null) {
                    mInstance = new LocationHelper(context);
                }
            }
        }
        return mInstance;
    }

    public void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    public Location getLocation() {
        return mLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        Log.d(TAG, "provider = " + location.getProvider());
        Log.d(TAG, "latitude = " + location.getLatitude());
        Log.d(TAG, "longitude = " + location.getLongitude());
        mLocation = location;
        if (mLocation != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mLocationManager.removeUpdates(this);

            String token = PreferencesManager.getInstance(mContext).getUserToken();
            String coordinates = mLocation.getLongitude() + "," + mLocation.getLatitude();

            Call<ResponseBody> call = RestClient.getInstance().checkForEmergency(token, coordinates);
            call.enqueue(callback);


        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private Callback<ResponseBody> callback = new Callback<ResponseBody>() {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                String body = response.body().string();
                Log.d(TAG, body);
                if (body.equals("True")) {
                    new NotificationManager(mContext).sendNotification("Emergency Vehicle Approaching");
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d(TAG, t.getMessage());
        }
    };
}

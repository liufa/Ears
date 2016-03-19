package gcm.play.android.samples.com.gcmquickstart;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Роман on 19.03.2016.
 */
public class PreferencesManager {
    private static final String PREFS_NAME = "shared preferences";

    private static final String KEY_USER_TOKEN = "user token";

    private Context mContext;
    private SharedPreferences mPrefs;
    private static PreferencesManager mInstance;

    private PreferencesManager(Context context) {
        mContext = context;
        mPrefs = mContext.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreferencesManager(context);
        }

        return mInstance;
    }

    public void saveUserToken(String token) {
        mPrefs.edit().putString(KEY_USER_TOKEN, token).commit();
    }

    public String getUserToken() {
        return mPrefs.getString(KEY_USER_TOKEN, "");
    }


}

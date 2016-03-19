package gcm.play.android.samples.com.gcmquickstart.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Роман on 19.03.2016.
 */
public interface Api {

    @GET(UrlBuilder.CHECK_FOR_EMERGENCY)
    Call<ResponseBody> checkForEmergency(@Query("token") String token, @Query("coordinates") String coordinates);
}

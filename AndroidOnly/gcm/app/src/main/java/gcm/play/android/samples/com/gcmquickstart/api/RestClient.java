package gcm.play.android.samples.com.gcmquickstart.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Роман on 19.03.2016.
 */
public class RestClient {

    private Api mApi;

    private static RestClient mInstance;

    private RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlBuilder.HOST)
                .build();

        mApi = retrofit.create(Api.class);
    }

    public static RestClient getInstance() {
        if (mInstance == null) {
            mInstance = new RestClient();
        }
        return mInstance;
    }

    public Call<ResponseBody> checkForEmergency(String token, String coordinates) {
        return mApi.checkForEmergency(token, coordinates);
    }
}

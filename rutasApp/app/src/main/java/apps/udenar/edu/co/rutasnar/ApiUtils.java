package apps.udenar.edu.co.rutasnar;

import android.content.Context;

import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    //public static final String BASE_URL = "181.55.121.253:8880/";
    //public static final String IMAGES_URL = "http://181.55.121.253:8880/public/uploads/";
    public static final String BASE_URL = "http://192.168.0.14:8880/api/";
    public static final String IMAGES_URL = "http://192.168.0.14:8880/public/uploads/";

    public ApiUtils() {}

    public static RutasNarAPI getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(RutasNarAPI.class);
    }

    public static User getCurrentUser(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        return db.getCurrentUser();
    }
}
